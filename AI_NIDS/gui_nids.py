import tkinter as tk
from tkinter import scrolledtext
from scapy.all import sniff, IP, TCP, UDP
import joblib
import pandas as pd
import threading

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

root = tk.Tk()
root.title("AI-Powered NIDS")
root.geometry("600x400")
log_area = scrolledtext.ScrolledText(root, wrap=tk.WORD, font=("Courier", 10))
log_area.pack(expand=True, fill='both')

def detect_packet(pkt):
    try:
        features = extract_features(pkt)
        prediction = model.predict(features)[0]
        if prediction == 1:
            msg = "[ALERT] 🚨 Malicious packet detected!\n"
        else:
            msg = "[OK] Normal packet\n"
        log_area.insert(tk.END, msg)
        log_area.see(tk.END)
    except Exception as e:
        log_area.insert(tk.END, f"[ERROR] {e}\n")

def start_sniffing():
    sniff(prn=detect_packet, store=0)

threading.Thread(target=start_sniffing, daemon=True).start()
root.mainloop()
