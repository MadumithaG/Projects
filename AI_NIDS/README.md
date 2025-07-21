# 🛡️ AI-Powered Network Intrusion Detection System (NIDS)

An intelligent real-time **Network Intrusion Detection System (NIDS)** built with **Python**, **Scapy**, and **Machine Learning**. This tool captures live network packets, analyzes them, and flags suspicious activity using a trained ML model. Includes both **CLI** and **GUI** interfaces.

---

## 📌 Features

- 🔍 Real-time packet sniffing using Scapy
- 🤖 ML-based anomaly detection (Random Forest)
- 📈 Packet feature extraction (IP length, protocol, ports, flags)
- 🖥️ Interactive GUI using Tkinter
- 🧠 Easy-to-train custom dataset for model flexibility
- 📦 Lightweight and beginner-friendly

---

## 🗂️ Project Structure

AI_NIDS_Project/
├── nids_train.py # ML model training script
├── live_detector.py # Command-line NIDS (real-time)
├── gui_nids.py # GUI-based NIDS using Tkinter
├── nids_dataset.csv # Sample training dataset (synthetic)
├── nids_model.pkl # Trained model (generated after training)

yaml
Copy code

---

## 🚀 Getting Started

### 🔧 Requirements
```bash
pip install scikit-learn pandas numpy scapy joblib
🧠 Step 1: Train the ML Model
bash
Copy code
python nids_train.py
This reads the nids_dataset.csv, trains a Random Forest classifier, and saves nids_model.pkl.

🖥️ Step 2: Run CLI-Based Detection
bash
Copy code
sudo python live_detector.py
Captures live packets using Scapy

Uses the trained model to predict if a packet is normal or malicious

Prints alerts in the terminal

🪟 Step 3: Run GUI-Based Detection
bash
Copy code
sudo python gui_nids.py
Launches a GUI window

Displays live detection results in a scrollable console

📊Dataset Format (nids_dataset.csv)
Each row in the dataset contains extracted packet features and a label:

csv
Copy code
ip_len,proto,sport,dport,flags,label
60,6,443,50432,18,0
60,6,80,50245,2,0
60,6,6667,12345,18,1
proto: 6 = TCP, 17 = UDP

flags: TCP flag value

label: 0 = Normal, 1 = Malicious

Use Cases
Network security awareness projects

Academic research / final year projects

Practical demo of AI in cybersecurity

Learn packet sniffing + ML hands-on

Notes
Run scripts with sudo or admin rights to sniff packets

Dataset is synthetic – feel free to replace it with CICIDS 2017 or real captures

GUI runs on Tkinter (pre-installed with Python)

 Future Improvements
Deep Learning integration (LSTM for flow-based detection)

Packet flow/session-level classification

Streamlit Web Dashboard for remote NIDS

Alerts via email/Slack
