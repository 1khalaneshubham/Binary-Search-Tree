import java.util.ArrayList;

public class IsValidBST {
    
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
        
    }

    public static Node insert(Node root,int val){
        
        if(root == null){
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            // left subtree
            root.left = insert(root.left, val);
        }else{
            // right subtree
            root.right = insert(root.right, val);
        }
        
        return root;
    }
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static boolean search(Node root,int key){
        if(root == null){
            return false;
        }
        if(root.data == key){
            return true;
        }
        if(root.data > key){
            return search(root.left, key);
        }else{
            return search(root.right, key);
        }
    }
// Delete 
    public static Node delete(Node root, int val){
        if(root.data < val){
            root.right = delete(root.right, val);
        }
        else if(root.data > val){
            root.left = delete(root.left, val);
        }else{
            // voila 
            // case 1; leaf node
            if(root.left == null && root.right == null){
                return null;
            }
            // case 2; single child
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            // case 3: both children
            Node IS = FindInorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }
        return root;
    }

    public static Node FindInorderSuccessor(Node root){
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
    
    public static void printInRange(Node root,int k1,int k2){
        
        if(root == null){
            return;
        }
        if(root.data >= k1 && root.data <= k2){
            printInRange(root.left, k1, k2);
            System.out.print(root.data+" ");
            printInRange(root.right, k1, k2);
        }
        else if(root.data < k1 ){
            printInRange(root.left, k1, k2);
        }
        else {
            printInRange(root.right, k1, k2);
        }
    } 

    public static void printPath(ArrayList<Integer> path){
        
        for(int i = 0; i<path.size(); i++){
            System.out.print(path.get(i)+" -> ");
        }
        System.out.println("Null");
    }

    public static void printRoot2Leaf(Node root, ArrayList<Integer> path){
        
        if(root == null){
            return;
        }
        path.add(root.data);
        if(root.left == null && root.right == null){
            printPath(path);
        }
        printRoot2Leaf(root.left, path);
        printRoot2Leaf(root.right, path);
        path.remove(path.size()-1);
    }

    public static boolean IsValid(Node root,Node min, Node max){
        if(root == null){
            return true;
        }

        if(min != null && root.data <= min.data){
            return false;
        }

        else if(max != null && root.data >= max.data){
            return false;
        }

        return IsValid(root.left, min, root) 
                && IsValid(root.right, root, max); 
    }

    public static void main(String[] args) {
        
        // int value[] = {8,5,3,1,4,6,10,11,14};
         int value[] = {1,1,1};
        Node root = null;

        for(int i = 0; i<value.length; i++){
            root = insert(root, value[i]);
        }
        inorder(root);
        System.out.println();

        if(IsValid(root, null, null)){
            System.out.println("Valid");
        }else{
            System.out.println("Not Valid");
        }
    }
}
