package euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * You can find all the problems here: 
 * https://projecteuler.net/archives
 */
public class Euler {

	// https://projecteuler.net/problem=1
	public static void sumOfMultiplesBelow1000(int max) {

		int sum = 0;

		for (int i = 1; i < max; i++) {

			if (i % 3 == 0) {
				sum = sum + i;
			} else if (i % 5 == 0) {
				sum = sum + i;
			}

		}

		System.out.println("sumOfMultiplesBelow1000 = " + sum);

	}

	// https://projecteuler.net/problem=2
	public static void sumOfEvenValuedFibonacciNumber(int max) {

		int first = 1;
		int second = 2;
		int sum = 0;

		while (first < max) {

			int temp = first;
			first = second;
			second = first + temp;

			// If even
			if (first % 2 == 0) {
				sum = sum + first;
			}

		}

		System.out.println("sumOfEvenValuedFibonacciNumber = " + sum);

	}

	public static boolean isPrime(List<Long> primes, long isPrimeQ) {
		// The value is prime only if it is not divisible by smaller primes
		for (long prime : primes) {

			if (isPrimeQ % prime == 0) {
				return false;
			}

		}

		return true;
	}

	public static long getNextPrime(List<Long> primes, long lastPrime) {

		System.out.println("Please wait while we find the next prime greater than " + lastPrime + "...");
		long newPrime = lastPrime + 1;

		while (!isPrime(primes, newPrime)) {
			newPrime++;
		}

		// Wasn't divisible by any prime? Then its a new prime!
//		System.out.println("Find prime: " + newPrime);
		return newPrime;

		// Gets the next biggest prime number
	}

	public static void largestPrimeFactor(long val) {

		List<Long> factors = new ArrayList<>();

		// Keeps track of the primes we know of
		List<Long> primes = new ArrayList<>();
		primes.add(2l); // Starts us with the next

		long largestFactor = 1;
		long prime = 2;

		// Until we've finished factoring
		while (val > 1) {

			// While we can continue dividing, do that
			while (val % prime == 0) {
				factors.add(prime);
				largestFactor = prime;
				val = val / prime;
			}

			// No longer divisible, find the next prime
			prime = getNextPrime(primes, prime);
			primes.add(prime);
		}

		// Prints out our largest prime
		System.out.println("largestPrimeFactor = " + largestFactor);
		System.out.print("Factorization: ");
		for (long factor : factors) {
			System.out.print(factor + " * ");
		}

	}

	// Prints the numPrime-th prime number
	public static void primeNum(int numPrime) {

		List<Long> primes = new ArrayList<>();
		long currentPrime = 2;
		primes.add(currentPrime);

		for (int i = 1; i < numPrime; i++) {

			long nextPrime = getNextPrime(primes, currentPrime);
			primes.add(nextPrime);
			currentPrime = nextPrime;

		}

		System.out.println("The " + numPrime + "th prime is " + currentPrime);

	}

	public static boolean isPalidrone(long num) {

		char[] nums = String.valueOf(num).toCharArray();
		for (int i = 0; i < nums.length / 2; i++) {
			if (nums[i] != nums[nums.length - i - 1]) {
				return false;
			}
		}

		return true;
	}

	public static long getLargestPalindroneInThreeDigits() {

		long maxPalindrone = 0;
		for (int i = 999; i > 100; i--) {

			for (int j = 999; j > 100; j--) {

				long multiple = i * j;
				if (isPalidrone(multiple) && multiple > maxPalindrone) {
					maxPalindrone = multiple;
				}

			}
		}

		return maxPalindrone;

	}
	
	public static List<Long> getFactorization(long val){
		
		List<Long> factors = new ArrayList<>();
		
		// Keeps track of the primes we know of
		List<Long> primes = new ArrayList<>();
		primes.add(2l); // Starts us with the next

		long prime = 2;

		// Until we've finished factoring
		while (val > 1) {

			// While we can continue dividing, do that
			while (val % prime == 0) {
				factors.add(prime);
				val = val / prime;
			}

			// No longer divisible, find the next prime
			prime = getNextPrime(primes, prime);
			primes.add(prime);
		}

		return factors;
	}

	public static void smallestMultiple(long val) {
		
		List<Long> commonFactors = new ArrayList<>();
		
		for (long i = val; i > 0; i--) {
			
			List<Long> currentFactor = getFactorization(i);
			for(long l : commonFactors) {
				
				if(currentFactor.contains(l)) {
					currentFactor.remove(l);
				}
				
			}
			
			commonFactors.addAll(currentFactor);
			
			
		}
		
		long multiple = 1; 
		for(long l : commonFactors) {
			multiple = multiple *l;
		}

		System.out.println("The smallest multiple = " + multiple);

	}

	public static void main(String args[]) {

		Euler.smallestMultiple(20);

	}

}
