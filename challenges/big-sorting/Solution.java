import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] unsorted = new String[n];
        for(int unsorted_i=0; unsorted_i < n; unsorted_i++){
            unsorted[unsorted_i] = in.next();
        }

        Arrays.sort(unsorted, new SortIntegers());
        
        StringBuilder sb = new StringBuilder();
        for (String v : unsorted) {
        	sb.append(v);
        	sb.append("\n");
        }
        System.out.println(sb);
    }

        
 

}
final class SortIntegers implements Comparator<String>{
	  @Override
	  public int compare(String integer1, String integer2) {
		  if (integer1.length() != integer2.length()) {
			  return integer1.length() - integer2.length();
		  } else {
			  int length = integer1.length();
			  for (int c = 0; c < length; c++) {
				  String int1 = integer1.substring(c, c+1);
				  String int2 = integer2.substring(c, c+1);
				  if (!Objects.equals(int1, int2)) {
					  return int1.compareTo(int2);
				  }
			  }
			  return 0;
		  }
	  }
	}

