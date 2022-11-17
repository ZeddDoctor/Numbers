import java.util.ArrayList;

public interface NumberProperties {
    //non-boolean methods
    ArrayList<Integer> factors(int n);

    ArrayList<Integer> primeFactors(int n);

    int nStepsCollatz(int n);

    int countDigits(int n);


    //boolean methods
    boolean isOdd(int n);

    boolean isEven(int n);

    boolean isPerfectSquare(int n);

    boolean isPerfectCube(int n);

    boolean isPalindrome(int n);

    boolean isPerfect(int n);

    boolean isFibonacci(int n);

    boolean isTribonacci(int n);

    boolean isFactorialNumber(int n);

    boolean isAbundant(int n);

    boolean isTriangular(int n);

    boolean isSphenic(int n);

    boolean isPrime(int n);

    boolean isMersennePrime(int n);

    boolean isTwinPrime(int n);

    boolean isKaprekar(int n);

    boolean isCarol(int n);

    boolean isPronic(int n);
}
