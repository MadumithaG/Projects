# 📦 Unit Testing Suite for Custom Python Library

This project showcases a **custom Python utility library** backed by a **comprehensive unit testing suite** written using **PyTest**, with a focus on robust input validation and high code coverage.

---

## 📁 Project Structure

📦 utils-testing/
├── utils.py # Python utility functions
├── test_utils.py # PyTest unit tests for utils.py
└── README.md # Project documentation

yaml
Copy
Edit

---

## 🧠 Features

- Custom utility functions (e.g., palindrome checker, factorial calculator, string reverse)
- Input validation and error handling
- Unit tests using **PyTest**
- Achieves **95%+ code coverage**
- Demonstrates testing best practices

---

## 🔧 Functions in `utils.py`

```python
def is_palindrome(s): ...
def reverse_string(s): ...
def factorial(n): ...
def find_max(arr): ...
def is_even(n): ...
Each of these has proper input validation and meaningful error handling.

🧪 Unit Testing with PyTest
Test file: test_utils.py

Each function is tested for:

✅ Valid input

🧪 Edge cases

❌ Invalid input (e.g., negative numbers, wrong types)

🚀 How to Run
Install PyTest if not installed:

bash
Copy
Edit
pip install pytest
Run the tests:

bash
Copy
Edit
pytest -v test_utils.py
(Use -s to show print statements if needed.)

📈 Code Coverage
To check coverage:

bash
Copy
Edit
pip install pytest-cov
pytest --cov=utils test_utils.py
Example output:

markdown
Copy
Edit
Name         Stmts   Miss  Cover
--------------------------------
utils.py        25      1    96%
