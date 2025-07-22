# utils.py

def is_palindrome(word):
    """Checks if a word is a palindrome (case & space insensitive)."""
    if not isinstance(word, str):
        raise ValueError("Input must be a string")
    cleaned = word.lower().replace(" ", "")
    return cleaned == cleaned[::-1]


def word_count(text):
    """Counts the number of words in a string."""
    if not isinstance(text, str):
        raise ValueError("Input must be a string")
    return len(text.strip().split())


def factorial(n):
    """Returns factorial of a non-negative integer."""
    if not isinstance(n, int) or n < 0:
        raise ValueError("Input must be a non-negative integer")
    result = 1
    for i in range(2, n+1):
        result *= i
    return result


def is_prime(n):
    """Checks if a number is prime."""
    if not isinstance(n, int) or n < 2:
        return False
    for i in range(2, int(n**0.5)+1):
        if n % i == 0:
            return False
    return True


def reverse_string(s):
    """Reverses the input string."""
    if not isinstance(s, str):
        raise ValueError("Input must be a string")
    return s[::-1]
