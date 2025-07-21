from scapy.all import sniff, IP, TCP, UDP
import joblib
import pandas as pd

model = joblib.load("nids_model.pkl")

def extract_features(pkt):
    features = {}
    if IP in pkt:
        features["ip_len"] = pkt[IP].len
        features["proto"] = pkt[IP].proto
    else:
        features["ip_len"] = 0
        features["proto"] = 0

    if TCP in pkt:
        features["sport"] = pkt[TCP].sport
        features["dport"] = pkt[TCP].dport
        features["flags"] = pkt[TCP].flags
    elif UDP in pkt:
        features["sport"] = pkt[UDP].sport
        features["dport"] = pkt[UDP].dport
        features["flags"] = 0
    else:
        features["sport"] = 0
        features["dport"] = 0
        features["flags"] = 0

    return pd.DataFrame([features])

def detect_packet(pkt):
    try:
        features = extract_features(pkt)
        prediction = model.predict(features)[0]
        if prediction == 1:
            print("[ALERT] 🚨 Malicious packet detected!")
        else:
            print("[OK] Normal packet")
    except Exception as e:
        print("Error processing packet:", e)

print("🚀 Starting real-time packet monitoring...")
sniff(prn=detect_packet, store=0)
