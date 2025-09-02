import java.util.*;

// Node structure
class Node {
    int data;
    Node left, right;
    Node(int val) {
        data = val;
        left = right = null;
    }
}

public class BoundaryTraversal {

    // Helper function: check if node is leaf
    static boolean isLeaf(Node root) {
        return (root.left == null && root.right == null);
    }

    // 1. Add Left Boundary (excluding leaves)
    static void addLeftBoundary(Node root, List<Integer> res) {
        Node cur = root.left;  // start from left child
        while (cur != null) {
            if (!isLeaf(cur)) res.add(cur.data); // add if not leaf
            if (cur.left != null) cur = cur.left; // prefer left
            else cur = cur.right;                 // else go right
        }
    }

    // 2. Add Right Boundary (excluding leaves, added in reverse)
    static void addRightBoundary(Node root, List<Integer> res) {
        Node cur = root.right; // start from right child
        List<Integer> temp = new ArrayList<>();
        while (cur != null) {
            if (!isLeaf(cur)) temp.add(cur.data); // add if not leaf
            if (cur.right != null) cur = cur.right; // prefer right
            else cur = cur.left;                    // else go left
        }
        // Add in reverse order
        for (int i = temp.size() - 1; i >= 0; i--) {
            res.add(temp.get(i));
        }
    }

    // 3. Add Leaf Nodes (all leaves left to right)
    static void addLeaves(Node root, List<Integer> res) {
        if (isLeaf(root)) {
            res.add(root.data);
            return;
        }
        if (root.left != null) addLeaves(root.left, res);
        if (root.right != null) addLeaves(root.right, res);
    }

    // MAIN FUNCTION: Boundary Traversal
    static List<Integer> printBoundary(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res; // if tree empty

        // Step 1: Add root (if not leaf)
        if (!isLeaf(root)) res.add(root.data);

        // Step 2: Add Left Boundary
        addLeftBoundary(root, res);

        // Step 3: Add all Leaves
        addLeaves(root, res);

        // Step 4: Add Right Boundary
        addRightBoundary(root, res);

        return res;
    }

    // Example usage
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> boundary = printBoundary(root);

        System.out.println("Boundary Traversal: " + boundary);
    }
}
