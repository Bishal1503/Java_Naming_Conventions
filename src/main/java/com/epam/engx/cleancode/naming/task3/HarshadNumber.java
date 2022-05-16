package com.epam.engx.cleancode.naming.task3;

public class HarshadNumber {
	private static final int BASE_NUMBER = 10;

	public String printHarshadNumbers() {
		StringBuilder result = new StringBuilder();
		long numberLimit = 200;
		for (int number = 1; number <= numberLimit; number++) {
			if (number % findHarshadNumbers(number) == 0) {
				result.append(number).append("\n");
			}
		}
		return result.toString();
	}

	private int findHarshadNumbers(int number) {
		int sum = 0;
		while (number != 0) {
            sum += number % BASE_NUMBER;
            number = number / BASE_NUMBER;
        }
		return sum;
	}

}
