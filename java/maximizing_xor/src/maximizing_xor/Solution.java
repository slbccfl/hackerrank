package maximizing_xor;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long l = (in.nextInt() & 0xffffffffl);
        long r = (in.nextInt() & 0xffffffffl);
        
        long maxXOR = 0;
        for (long i = l;i <= r; i++) {
            for (long j = l;j <= r; j++) {
        		if ((i ^ j) > maxXOR) maxXOR = (i ^ j);
            }
        }
        System.out.println(maxXOR);
        
    }
}
