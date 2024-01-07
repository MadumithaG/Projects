import pandas as pd
import numpy as np
from sklearn.svm import OneClassSVM
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt

# Load the training and test data from CSV files
train_data = pd.read_csv("D:\SEM 5\ML\PACKAGE\Train_data.csv")
test_data = pd.read_csv("D:\SEM 5\ML\PACKAGE\Test_data.csv")

# Separate features (X) for training data
X_train = train_data.drop(columns=['class'])  # Remove the 'class' column

# Check if 'class' column exists in the test data
if 'class' in test_data:
    X_test = test_data.drop(columns=['class'])  # Remove the 'class' column
    # Label anomalies in the test data as -1
    y_test = np.where(test_data['class'] == 'normal', 1, -1)
else:
    X_test = test_data
    y_test = None

# One-hot encode categorical columns
X_train_encoded, X_test_encoded = pd.get_dummies(X_train), pd.get_dummies(X_test)

# Align the feature columns between training and test data
X_train_encoded, X_test_encoded = X_train_encoded.align(X_test_encoded, join='outer', axis=1, fill_value=0)

# Standardize the features
scaler = StandardScaler()
X_train_encoded = scaler.fit_transform(X_train_encoded)
X_test_encoded = scaler.transform(X_test_encoded)

# Label the training data as 'normal'
# 1 represents 'normal'
y_train = np.ones(len(X_train_encoded))

# Train a OneClassSVM model
svm_model = OneClassSVM(kernel='linear', nu=0.1)
svm_model.fit(X_train_encoded)

# Predict labels for the test data if 'class' is available
if y_test is not None:
    y_pred = svm_model.predict(X_test_encoded)
    accuracy = accuracy_score(y_test, y_pred)
    error = 1 - accuracy
    print(f"Accuracy: {accuracy * 100:.2f}%")
    print(f"Error: {error * 100:.2f}%")
    y_pred = svm_model.predict(X_test_encoded)
    anomalies = X_test_encoded[y_pred == -1]
    print("Anomalies:")
    print(anomalies)
else:
    print("No anomaly detection possible.")

# Calculate the sparsity level
n_support_vectors = len(svm_model.support_)
total_samples = len(X_train_encoded)
sparsity_level = n_support_vectors / total_samples

print(f"Sparsity Level: {sparsity_level * 100:.2f}%")

# Scatter plot to visualize anomalies
if 'class' in test_data:
    plt.scatter(X_test_encoded[y_test == 1][:, 0], X_test_encoded[y_test == 1][:, 1], c='blue', label='Normal')
    plt.scatter(X_test_encoded[y_test == -1][:, 0], X_test_encoded[y_test == -1][:, 1], c='red', label='Anomalies')
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.legend()
    plt.title('Anomaly Detection')
    plt.show()
