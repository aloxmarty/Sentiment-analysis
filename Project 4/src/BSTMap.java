import java.util.ArrayList;
import java.util.List;

/**
 * A map implemented with a binary search tree.
 */
public class BSTMap<K extends Comparable<K>, V> {

    private Node root; // points to the root of the BST.
    private int size;  // stores the number of key-value pairs in the tree

    /**
     * Create a new, empty BST.
     */
    public BSTMap() {
        root = null;
    }

    /**
     * Put (add a key-value pair) into this BST. If the key already exists, the old
     * value will be overwritten with the new one.
     */
    public void put(K newKey, V newValue) {
        put(root, newKey, newValue);
    }

    /**
     * Helper function for put.
     */
    private void put(Node current, K newKey, V newValue) {
        Node b = new Node();
        b.key = newKey;
        b.value = newValue;

        if (root == null){
            root = b;
            size++;
        }

        else{
            if (current.key.compareTo(newKey) == 0){
                current.value = newValue;
            }
            else if (newKey.compareTo(current.key) < 0) {
                if(current.left == null){
                    current.left = b;
                    size++;
                }
                else{
                    put(current.left, newKey, newValue);
                }
            }
            else{
                if(current.right == null){
                    current.right = b;
                    size++;
                }
                else{
                    put(current.right, newKey, newValue);
                }
            }
        }
    }

    /**
     * Get a value from this BST, based on its key. If the key doesn't already exist in the BST,
     * this method returns null.
     */
    private V get(Node curr, K searchKey) {
        if (curr == null) {
            return null;
        }
        else if (searchKey.compareTo(curr.key) == 0) {
            return curr.value;
        }
        else if (searchKey.compareTo(curr.key) > 0) {
            return get(curr.right, searchKey);
        }
        else {
            return get(curr.left, searchKey);
        }
    }

    public V get(K searchKey) {
        return get(root, searchKey);
    }

    /**
     * Test if a key is present in this BST. Returns true if the key is found, false if not.
     */

    public boolean containsKey(K searchKey) {

        if (get(searchKey) != null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Given a key, remove the corresponding key-value pair from this BST. If the key is not found, do nothing.
     */
    public void remove(K removeKey) {
        Node curr = root;
        Node parent = null;

        while ((curr != null) && !((curr.key.compareTo(removeKey)) == 0)){
            parent = curr;
            if (removeKey.compareTo(curr.key) < 0){
                curr = curr.left;
            }
            else{
                curr = curr.right;
            }
        }

        if (curr == null){
            return;
        }

        if (curr.left != null && curr.right != null){
            Node successor = curr.right;
            Node successorParent = curr;
            while (successor.left != null){
                successorParent = successor;
                successor = successor.left;
            }
            curr.key = successor.key;
            curr.value = successor.value; // added this line not present in the code given in class
            curr = successor;
            parent = successorParent;
        }

        Node subtree;

        if (curr.left ==  null && curr.right == null){
            subtree = null;
        }
        else if (curr.left != null){
            subtree = curr.left;
        }
        else{
            subtree = curr.right;
        }

        if (parent == null){
            root = subtree;
        }
        else if (parent.left == curr){
            parent.left = subtree;
        }
        else{
            parent.right = subtree;
        }
        size--;
    }

    /**
     * Return the number of key-value pairs in this BST.
     */
    public int size() {
        return size;
    }

    /**
     * Return the height of this BST.
     */

    public int height() {
        return height(root);
    }

    private int height (Node curr){
        int cnt = 0;

        if (curr == null){
            return -1;
        }
        else {
            int cntleft = (height(curr.left));
            int cntright = (height(curr.right));


            if (cntleft > cntright) {
                cnt = cntleft + 1;
            } else {
                cnt = cntright + 1;
            }
            return cnt;
        }
    }

    /**
     * Return a List of the keys in this BST, ordered by a preorder traversal.
     */
    public List<K> preorderKeys() {
        List<K> preorderList = new ArrayList<>();
        return preorderKeys(root, preorderList);
    }

    private List<K> preorderKeys(Node b, List<K> keylist){
        if (b!= null){
            keylist.add(b.key);
            preorderKeys(b.left, keylist);
            preorderKeys(b.right, keylist);
        }
        return keylist;
    }

    /**
     * Return a List of the keys in this BST, ordered by a inorder traversal.
     */
    public List<K> inorderKeys() {
        List<K> inorderList = new ArrayList<>();
        return inorderKeys(root, inorderList);
    }

    private List<K> inorderKeys(Node b, List<K> keylist){
        if (b!= null){
            inorderKeys(b.left, keylist);
            keylist.add(b.key);
            inorderKeys(b.right, keylist);
        }
        return keylist;
    }
    /**
     * Return a List of the keys in this BST, ordered by a postorder traversal.
     */
    public List<K> postorderKeys() {
        List<K> postorderList = new ArrayList<>();
        return postorderKeys(root, postorderList);
    }

    private List<K> postorderKeys(Node b, List<K> keylist){
        if (b!= null){
            postorderKeys(b.left, keylist);
            postorderKeys(b.right, keylist);
            keylist.add(b.key);
        }
        return keylist;
    }

    /**
     * It is very common to have private classes nested inside other classes. This is most commonly used when
     * the nested class has no meaning apart from being a helper class or utility class for the outside class.
     * In this case, this Node class has no meaning outside of this BSTMap class, so we nest it inside here
     * so as to not prevent another class from declaring a Node class as well.
     */
    private class Node {
        public K key = null;
        public V value = null;
        public Node left = null;
        public Node right = null;

        // Add a constructor here if you'd like.
        // If no constructor is defined, you get the default constructor, `new Node()`.
    }
}
