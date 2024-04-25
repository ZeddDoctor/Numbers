import java.util.ArrayList;
import java.util.HashMap;
/*
Overriding all functions from interface NumberProperties.java
Individual functions:
    1. 'boolProperties': to return a hashmap of all the boolean properties
*/

public class Numbers implements NumberProperties {
    @Override
    public ArrayList<Integer> factors(int n) {
        //This function is currently not presented to the users.Its sole purpose is to be helpful for internal calculations.
        ArrayList<Integer> factorList = new ArrayList<>();
        if(n == 0) return factorList;

        factorList.add(1);
        if(n == 1) return factorList;

        //Currently factorList = {1}

        if(n == 2){
            factorList.add(2);
            return factorList;
        }
        if (n == 3){
            factorList.add(3);
            return factorList;
        }

        //Currently factorList = {1}
        for(int i = 2; i <= n/2; ++i){
            if(n % i == 0) factorList.add(i);
        }
        factorList.add(n);
        return factorList;
    }

    @Override
    public ArrayList<Integer> primeFactors(int n) {
        if(isPrime(n)) {
            System.out.print("The number itself is prime.");
            return new ArrayList<>();
        }

        ArrayList<Integer> factorList = factors(n);
        ArrayList<Integer> primef= new ArrayList<>();
        factorList.forEach(factor -> {
            if(isPrime(factor)) primef.add(factor);
        });
        return primef;
    }

    @Override
    public int nStepsCollatz(int n) {
        if(n == 0 || n==1) return 0;
        if(n == 2) return 1;

        int noOfSteps = 0;
        while(n != 1){
            if(isOdd(n)) {
                n = n * 3 + 1;
            }
            else
                n = n/2;
            ++noOfSteps;
        }
        return noOfSteps;
    }

    @Override
    public int countDigits(int n){
        //This function is currently not presented to the users.Its sole purpose is to be helpful for internal calculations.
        String num = Integer.toString(n);
        return num.length();
    }

    @Override
    public boolean isOdd(int n) {
        //This function is currently not presented to the users.Its sole purpose is to be helpful for internal calculations.
        if(n == 0) return false;
        return n % 2 != 0;
    }

    @Override
    public boolean isEven(int n) {
        //This function is currently not presented to the users.Its sole purpose is to be helpful for internal calculations.
        if(n == 0 || n == 1) return false;
        return n % 2 == 0;
    }

    @Override
    public boolean isPerfectSquare(int n) {
        return (Math.sqrt(n) - Math.floor(Math.sqrt(n))) == 0;
    }

    @Override
    public boolean isPerfectCube(int n) {
        return (Math.cbrt(n) - Math.floor(Math.cbrt(n))) == 0;
    }

    @Override
    public boolean isPalindrome(int n) {
        if (n < 10)
            return true;

        int copy = n;
        int reverse = 0;
        while (copy != 0) {
            reverse = (reverse * 10) + (copy % 10);
            copy = copy / 10;
        }
        return reverse == n;
    }
    @Override
    public boolean isPerfect(int n) {
        /*A perfect number is one whose sum of factors equals number.
        First few: 6, 28, 496, 8128
         */
        int sum = 0;
        for(int i = 1; i <= n/2; i++){
            if (n % i == 0) sum += i;
        }
        return sum == n;
    }

    @Override
    public boolean isFibonacci(int n) {
        return isPerfectSquare(5 * n * n - 4) || isPerfectSquare(5 * n * n + 4);
    }

    @Override
    public boolean isTribonacci(int n) {
        if(n == 0 || n == 1) return true;
        int[] trib = {0, 1, 1};
        for (int i = 3; i <= n; i++){
            if(trib[0] > n || trib[1] > n || trib[2] > n)
                return false;
            if(trib[0] == n || trib[1] == n || trib[2] == n)
                return true;
            trib[i % 3] = trib[0] + trib[1] + trib[2];
        }
        return false;
    }

    @Override
    public boolean isFactorialNumber(int n) {
        int[] factorialList = {1,2,6,24,120,720,5040,40320,362880,3628800,39916800,39916800,479001600};
        for(int num: factorialList){
            if(num == n) return true;
        }
        return false;
    }

    @Override
    public boolean isAbundant(int n) {
        /*An abundant number is one whose sum of its proper factors is greater than the number itself.
        Example: 12 : Sum of factors => 1+ 2 + 3+ 4 + 6 = 16, which is greater than 12.
        First few: 12, 18, 20, 24, 30, 36, 40, 42
         */
        int sum = 0;
        for(int i = 1; i <= n/2; i++) {
            if (n % i == 0) sum += i;
        }
        return sum > n;
    }

    @Override
    public boolean isTriangular(int n) {
        /*To find nth triangular number just : n(n+1)/2
        Example 5th triangular number : 5(5+1)/2 = 15
        First few: 0, 1, 3, 6, 10, 15, 21, 28, 36, 45
        n(n+1)/2 = Given no.
        n^2 + n - 2*Given no. = 0
        Turning this into a quadratic we get: a*(n^2) + b*n + c = 0, where we are given c (also a and b is equal to 1)
        We just have to solve the quadratic for n and check if n is a natural number or not.
         */
        if(n == 0 || n==1) return true;
        int c = -(2 * n);
        int a = 1, b = 1;
        int d = (b * b) - (4 * a * c);

        /*If the number is always positive, d will also be always positive
        if(d < 0) return false;
         */

        //finding roots
        double root1 = (-b + (Math.sqrt(d)))/ (2 * a);
        return root1 > 0 && (root1 - Math.floor(root1)) == 0;

        /*
        double root2 = -b - (Math.sqrt(d))/ (2 * a);
        return root2 > 0 && root2 - Math.floor(root2) == 0;

        This is always negative since we know d (and a and b) is positive
         */
    }

    @Override
    public boolean isSphenic(int n) {
        /*A sphenic number is a number which is the product of exactly three distinct primes.
        Example: 30 => 2 * 3 * 5
        First few: 30, 42, 66, 70, 78, 102, 105, 110
        To check for a sphenic number:
        1. Every sphenic number has exactly 8 factors(if we include the number itself).If it has more or less return false.
        2. We just have to check whether the three numbers after 1 in its factor list are prime or not.
         */
        ArrayList<Integer> factorList = factors(n);
        if(factorList.size()!=8) return false;

        return isPrime(factorList.get(1))  &&  isPrime(factorList.get(2))  &&  isPrime(factorList.get(3));
    }

    @Override
    public boolean isPrime(int n) {
        if(n == 0 || n == 1) return false;
        ArrayList<Integer> factorList = factors(n);
        return factorList.size() == 2;
    }

    @Override
    public boolean isMersennePrime(int n) {
        /*A Mersenne prime is a prime number that can also be written as 2^x -1 (where x is any number).
        First few: 3, 7, 31, 127, 8191, 131071, 524287
         */
        return isPrime(n) && isPerfectSquare(n + 1);
    }

    @Override
    public boolean isTwinPrime(int n) {
        /* Twin prime numbers are set of two prime numbers that have exactly a difference of 1 between them.
        First few: (3,5), (5,7), (11,13)
         */
        return isPrime(n) && (isPrime(n+2) || isPrime(n-2));
    }

    @Override
    public boolean isKaprekar(int n) {
        /*A Kaprekar number is a number whose square can be split into two parts and the sum of its parts will result in the orignal number.
        Example: 45 => 2025 => 20 + 25 = 45
                 297 => 88209 => 88 + 209 = 297
         */
        if (n == 1 || n == 9) return true; // 1 and 9 are Kaprekar numbers.
        int noOfDigits = countDigits(n*n);
        String num = Integer.toString(n*n);
        String part1 = num.substring(0, (noOfDigits/2));
        String part2 = num.substring(noOfDigits/2);
        if(Integer.parseInt(part1)+Integer.parseInt(part2) == n) return true;
        else{
            if(isOdd(noOfDigits)){
                part1 = num.substring(0, (noOfDigits/2) + 1);
                part2 = num.substring((noOfDigits/2) + 1);
                return (Integer.parseInt(part1) + Integer.parseInt(part2)) == n;
            }
        }
        return false;
    }

    @Override
    public boolean isCarol(int n) {
        /*A Carol number is defined by the formula (2^n-1)^2 - 2
        An Interesting Property :
        For n > 2, the binary representation of the n-th Carol number is n-2 consecutive one’s, a single zero in the middle, and n + 1 more consecutive one’s.
        Example, n = 4 carol number is 223 and binary of 223 is 11011111, here n-2 = 4-2 = 2 consecutive ones in starting then single 0 in middle and then n + 1 = 4 + 1 = 5 consecutive ones after it.
        We will use this property to check whether given number is Carol number or not.
        First few: -1, 7, 47, 223, 959
         */
        if(n==1) return false;
        if(n == 7) return true;

        String binaryn = Integer.toBinaryString(n);
        if(binaryn.indexOf('0') != binaryn.lastIndexOf('0'))
            return false;
        if(binaryn.endsWith("0"))
            return false;

        String firstPart, secondPart;
        firstPart = binaryn.substring(0,binaryn.indexOf("0"));
        secondPart = binaryn.substring(binaryn.indexOf("0")+1);

        if(firstPart.length() > secondPart.length()) return false;
        return secondPart.length() - firstPart.length() == 3;
    }

    @Override
    public boolean isPronic(int n) {
        /*Pronic number is a number which is the product of two consecutive integers, that is, a number n is a product of x and (x+1)
        First few: 0, 2, 6, 12, 20, 30, 42, 56
         */
        if(n == 0) return true;

        ArrayList<Integer> factorList = factors(n);
        for(int i = 2 ;i < factorList.size(); i++){
            if((factorList.get(i) - factorList.get(i-1) == 1) && factorList.get(i) * factorList.get(i-1) == n){
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Boolean> boolProperties(int n){
        HashMap <String, Boolean> properties = new HashMap<>();

        properties.put("Perfect Square",   isPerfectSquare(n));
        properties.put("Perfect Cube",     isPerfectCube(n));
        properties.put("Palindrome",       isPalindrome(n));
        properties.put("Perfect",          isPerfect(n));
        properties.put("Fibonacci",        isFibonacci(n));
        properties.put("Tribonacci",       isTribonacci(n));
        properties.put("Factorial Number", isFactorialNumber(n));
        properties.put("Abundant",         isAbundant(n));
        properties.put("Triangular",       isTriangular(n));
        properties.put("Sphenic",          isSphenic(n));
        properties.put("Prime",            isPrime(n));
        properties.put("Mersenne Prime",   isMersennePrime(n));
        properties.put("Twin Prime",       isTwinPrime(n));
        properties.put("Kaprekar",         isKaprekar(n));
        properties.put("Carol",            isCarol(n));
        properties.put("Pronic",           isPronic(n));

        return properties;
    }

}

