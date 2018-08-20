import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            if (!isPrime(n)) {
            	System.out.println("Not prime");
            } else {
            	System.out.println("Prime");
            }
        }
    }
    private static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1)  return false;
        if (n <= 3)  return true;
     
        // This is checked so that we can skip 
        // middle five numbers in below loop
        if (n%2 == 0 || n%3 == 0) return false;
     
        for (int i=5; i*i<=n; i=i+6)
            if (n%i == 0 || n%(i+2) == 0)
               return false;
     
        return true;
    }
     
}
