# test_utils.py

import pytest
from utils import is_palindrome, word_count, factorial, is_prime, reverse_string

# is_palindrome
def test_palindrome():
    assert is_palindrome("Madam") is True
    assert is_palindrome("nurses run") is True
    assert is_palindrome("Python") is False

def test_palindrome_invalid():
    with pytest.raises(ValueError):
        is_palindrome(1234)

# word_count
def test_word_count():
    assert word_count("Hello world") == 2
    assert word_count("   one two   three ") == 3

def test_word_count_invalid():
    with pytest.raises(ValueError):
        word_count(123)

# factorial
def test_factorial():
    assert factorial(0) == 1
    assert factorial(5) == 120

def test_factorial_invalid():
    with pytest.raises(ValueError):
        factorial(-1)
    with pytest.raises(ValueError):
        factorial("abc")

# is_prime
def test_is_prime():
    assert is_prime(2) is True
    assert is_prime(13) is True
    assert is_prime(4) is False
    assert is_prime(1) is False

# reverse_string
def test_reverse_string():
    assert reverse_string("hello") == "olleh"
    assert reverse_string("123") == "321"

def test_reverse_string_invalid():
    with pytest.raises(ValueError):
        reverse_string(100)
