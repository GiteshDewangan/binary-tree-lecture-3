// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
        this.val = 0;
        this.left = null;
        this.right = null;
    }

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

import java.util.*;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        if (root != null) st.push(root);

        while (!st.isEmpty()) {
            TreeNode temp = st.pop();
            ans.add(temp.val);

            // C++ code me pehle left push kiya tha fir right
            // but hum postorder reverse kar rahe hain to same logic follow karenge
            if (temp.left != null) st.push(temp.left);
            if (temp.right != null) st.push(temp.right);
        }

        // reverse ans list
        Collections.reverse(ans);

        return ans;
    }
}
