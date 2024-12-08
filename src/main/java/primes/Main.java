package primes;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        long number = 2147483647L;
//        long largePrime = 10657331232548839L;

        long largePrime = 790_738_119_649_411_319L;
        long number2 = 5000;
        long count = 0;

        for (int i = 0; i < number2; i++) {
            if (PrimeNumberUtils.isPrime(i)) {
                if (!BigInteger.valueOf(i).isProbablePrime(Integer.MAX_VALUE)) {
                    System.out.printf("**********Not prime %s**********\n", i);
                    continue;
                }
                System.out.printf("%d) Number %d is prime\n", ++count, i);
            }
        }

        long startTime = System.currentTimeMillis();
        if (PrimeNumberUtils.isPrime(largePrime)) {
            System.out.printf("The number: %s is a prime number\n", largePrime);
        }

        System.out.printf("It took %s ms to check if the number %s is a prime number%n", System.currentTimeMillis() - startTime, largePrime);
    }
}
