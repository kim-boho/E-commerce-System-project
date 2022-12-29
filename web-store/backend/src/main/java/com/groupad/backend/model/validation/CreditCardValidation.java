/**
 * 
 */
package com.groupad.backend.model.validation;

/**
 * @author Boho Kim
 *
 */
public class CreditCardValidation {

	// A credit card number must have between 13 and 16 digits. It must start with:
	//
	// 4 for Visa cards
	// 5 for Master cards
	// 37 for American Express cards
	// 6 for Discover cards
	// The problem can be solved by using Luhn algorithm.
	//
	// Luhn check or the Mod 10 check, which can be described as follows
	// (for illustration, consider the card number 4388576018402626):
	//
	// Step 1. Double every second digit from right to left. If doubling of a digit
	// results in a
	// two-digit number, add up the two digits to get a single-digit number (like
	// for 12:1+2, 18=1+8).
	// Step 2. Now add all single-digit numbers from Step 1.
	// 4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37
	// Step 3. Add all digits in the odd places from right to left in the card
	// number.
	// 6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38
	// Step 4. Sum the results from Step 2 and Step 3. 37 + 38 = 75
	// Step 5. If the result from Step 4 is divisible by 10, the card number is
	// valid; otherwise, it is invalid.

	public static boolean isValid(long number) {
		return (getSize(number) >= 13 &&
				getSize(number) <= 16) &&
				(prefixMatched(number, 4) ||
						prefixMatched(number, 5) ||
						prefixMatched(number, 37) ||
						prefixMatched(number, 6))
				&&
				((sumOfDoubleEvenPlace(number) +
						sumOfOddPlace(number)) % 10 == 0);
	}

	// Get the result from Step 2
	public static int sumOfDoubleEvenPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 2; i >= 0; i -= 2)
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

		return sum;
	}

	// Return this number if it is a single digit, otherwise,
	// return the sum of the two digits
	public static int getDigit(int number) {
		if (number < 9)
			return number;
		return number / 10 + number % 10;
	}

	// Return sum of odd-place digits in number
	public static int sumOfOddPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 1; i >= 0; i -= 2)
			sum += Integer.parseInt(num.charAt(i) + "");
		return sum;
	}

	// Return true if the digit d is a prefix for number
	public static boolean prefixMatched(long number, int d) {
		return getPrefix(number, getSize(d)) == d;
	}

	// Return the number of digits in d
	public static int getSize(long d) {
		String num = d + "";
		return num.length();
	}

	// Return the first k number of digits from
	// number. If the number of digits in number
	// is less than k, return number.
	public static long getPrefix(long number, int k) {
		if (getSize(number) > k) {
			String num = number + "";
			return Long.parseLong(num.substring(0, k));
		}
		return number;
	}
}
