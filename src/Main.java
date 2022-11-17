import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Numbers number = new Numbers();
        Scanner sc = new Scanner(System.in);
        int ch = -2;//for user choice
        int n = 1;//placeholder value
        while (ch != 0) {
            switch (ch) {
                case -2:
                    System.out.print("Enter number to check for cool properties: ");
                    n = sc.nextInt();
                    while(n <= 0){
                        if(n == 0) {
                            System.out.println("0 is a number that defies most properties.The number is magic itself.\n" +
                                    "P.S.: Cool fact=> The major credit for zero being popular in the whole world today goes to Leonardo de Pisa (best known as Fibonacci)." +
                                    "In his book, he introduced the Fibonacci numbers and showed the importance of zero.");
                            System.out.print("Enter a different number to check for cool properties: ");
                        }
                        else{
                            System.out.println("Sorry,negative numbers are not allowed currently. Please enter a positive number.");
                            System.out.print("Enter a different number to check for cool properties: ");
                        }
                        n = sc.nextInt();
                    }
                    HashMap<String, Boolean> properties = number.boolProperties(n);
                    int c = 0;//Counter for properties
                    for (Map.Entry<String, Boolean> entry : properties.entrySet()) {
                        String k = entry.getKey();
                        Boolean v = entry.getValue();
                        if (v) {
                            ++c;
                            System.out.printf("\n%d is %s number", n, k);
                        }
                    }
                    System.out.printf("\n\nFound %d cool properties.What do you want to do next?", c);
                    //fallthrough
                case -1:
                    System.out.print("\n\n1. List all prime factors. \n2.Number of steps required for the number in Collatz Conjecture. \n3.Enter new number. \n0.Exit \n\n:");
                    ch = sc.nextInt();
                    break;

                case 0:
                    System.exit(0);

                case 1:
                    ArrayList<Integer> primef = number.primeFactors(n);
                    if(primef.isEmpty()) System.out.println("No prime factors of given number.");
                    for (int element : primef) {
                        System.out.print(element + ",");
                    }
                    ch=-1;
                    break;

                case 2:
                    System.out.println(number.nStepsCollatz(n)+" steps");
                    ch=-1;
                    break;

                case 3:
                    ch = -2;
                    break;


                default:
                    throw new IllegalStateException("Unexpected value: " + ch);
            }
        }
    }
}
