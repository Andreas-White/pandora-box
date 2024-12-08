package primes;

public class PrimeNumberUtils {

    public static boolean isPrimeAlt(long number) {
        if (number < 2) return false;
        if (number == 2) return true;

        int size = (int) Math.sqrt(number) + 1;

        long[] primes = new long[size];
        primes[0] = 2;
        int count = 0;

        for (long i = 3; i <= size; i += 2) {
            if (i % primes[count] == 0 && i != primes[count]) {
                continue;
            }
            count++;
            primes[count] = i;
        }

        for (long prime : primes) {
            if (prime == 0) {
                break;
            }

            if (number % prime == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }

        if (number <= 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        long sqrtN = (long) Math.sqrt(number) + 1;

        for (long i = 6; i <= sqrtN; i += 6) {
            if (number % (i - 1) == 0 || number % (i + 1) == 0)
                return false;
        }
        return true;
    }

    public static boolean isPrimeChatGPT(long n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        // Check for prime using 6k +/- 1 rule
        for (long i = 6; i * i <= n; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) {
                return false;
            }
        }

        return true;
    }
}