/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    boolean checkBST(Node node, int min, int max) {
        if (node == null) return true;
        if (node.data < min || node.data > max){
            return false;
        } else {
            if (checkBST(node.left, min, node.data)) {
                return checkBST(node.right, node.data, max);
            } else {
                return false;
            }
        }
    }