#include <bits/stdc++.h>
using namespace std;

// Node structure
struct Node {
    int data;
    Node* left;
    Node* right;
    Node(int val) {
        data = val;
        left = right = NULL;
    }
};

// Helper function: check leaf node
bool isLeaf(Node* root) {
    return (root->left == NULL && root->right == NULL);
}

// 1. Add Left Boundary (without leaves)
void addLeftBoundary(Node* root, vector<int>& res) {
    Node* cur = root->left;  // start from left child
    while (cur) {
        if (!isLeaf(cur)) res.push_back(cur->data); // add only non-leaf
        if (cur->left) cur = cur->left;             // go left if possible
        else cur = cur->right;                      // otherwise go right
    }
}

// 2. Add Right Boundary (without leaves)
void addRightBoundary(Node* root, vector<int>& res) {
    Node* cur = root->right;   // start from right child
    vector<int> temp;          // to store temporarily (because we need reverse order)
    while (cur) {
        if (!isLeaf(cur)) temp.push_back(cur->data); // add only non-leaf
        if (cur->right) cur = cur->right;            // go right if possible
        else cur = cur->left;                        // otherwise go left
    }
    // Add in reverse order (bottom to top)
    for (int i = temp.size() - 1; i >= 0; i--) {
        res.push_back(temp[i]);
    }
}

// 3. Add Leaf Nodes (all leaves left to right)
void addLeaves(Node* root, vector<int>& res) {
    if (isLeaf(root)) { 
        res.push_back(root->data); 
        return;
    }
    if (root->left) addLeaves(root->left, res);   // check left subtree
    if (root->right) addLeaves(root->right, res); // check right subtree
}

// MAIN FUNCTION: Boundary Traversal
vector<int> printBoundary(Node* root) {
    vector<int> res;
    if (!root) return res;  // if empty tree, return empty
    
    // Step 1: Add root (if not leaf)
    if (!isLeaf(root)) res.push_back(root->data);

    // Step 2: Add Left Boundary
    addLeftBoundary(root, res);

    // Step 3: Add all Leaves
    addLeaves(root, res);

    // Step 4: Add Right Boundary
    addRightBoundary(root, res);

    return res;
}

// Example usage
int main() {
    Node* root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);
    root->right->left = new Node(6);
    root->right->right = new Node(7);

    vector<int> boundary = printBoundary(root);

    cout << "Boundary Traversal: ";
    for (int x : boundary) cout << x << " ";
}
