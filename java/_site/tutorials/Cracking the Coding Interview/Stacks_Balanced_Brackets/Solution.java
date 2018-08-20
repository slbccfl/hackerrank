import java.util.*;


public class Solution {

  public static Stack<Character> bracketStack = new Stack<Character>();

  public static boolean isBalanced(String expression) {
	bracketStack.clear();
    for (int i = 0; i < expression.length(); i++) {
      char character = expression.charAt(i);
      switch (character) {
        case '[' :
          bracketStack.push(']');
          break;
        case '{' :
          bracketStack.push('}');
          break;
        // case '<' :
        //   bracketStack.push('>');
        //   break;
        case '(' :
          bracketStack.push(')');
          break;
        default :
          if (bracketStack.isEmpty() || character != bracketStack.pop()) {
            return false;
          }
          // if (character != bracketStack.pop()) {
          //   return false;
          // }
      }
    }
    if (bracketStack.isEmpty()) {
    	return true;
	}
    else {
    	return false;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int a0 = 0; a0 < t; a0++) {
      String expression = in.next();
      System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
    }
    in.close();
  }

}
