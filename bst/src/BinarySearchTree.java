/**
 * Created by Magdalena on 2017-04-09.
 */
/**
 * Class defining Binary Search Tree
 */
class BinarySearchTree<K extends Comparable<K>> {
    /** BST root node reference */
    public Node<K> root;

    public int compares = 0;

    /**
     * BinarySearchTree constructor creates empty tree
     */
    BinarySearchTree() {
        root = null;
    }

    /**
     * Method searching node by key
     * @param key key by which node is searched
     * @return the node with value keyW ogól
     */
    private Node<K> searchNode(K key) {
        Node<K> x = root;                       // start at root
        while (x != null && key != x.key) {    // until we get to end of the tree or we find node with the key
            if (key.compareTo(x.key) < 0)       // if key is lower than current node, go left
                x = x.left;
            else
                x = x.right;                    // else go right
            compares++;
        }
        return x;                               // return found node, or null if node with given key doesnt exist
    }

    /**
     * Method checking if node with given key is in the tree, prints '1' if node exists, '0' otherwise
     * @param key key by which node is searched
     */
    void find(K key) {
        Node<K> x = searchNode(key);            // try to find node with the key

        if (x != null)                          // if it exists print '1'
            System.out.println(1);
        else
            System.out.println(0);                      // else print '0'
    }

    /**
     * Method inserting new value to the tree
     * @param key value of the node to insert
     */
    void insert(K key) {
        Node<K> node = new Node<>(key);                 // create new node with given key
        Node<K> x = root;                               // start at root
        Node<K> y = null;                               // temporary node
        while (x != null) {                             // until we get to end of the tree
            y = x;                                      // copy current node
            if ((node.key).compareTo(x.key) < 0)        // if node to insert have lower value than current node
                x = x.left;                             // go left
            else
                x = x.right;                            // else go right
        }
        node.parent = y;                                // when we found empty place for our node set it's parent
        if (y == null)                                  // if tree is empty, new node becomes root
            root = node;
        else if ((node.key).compareTo(y.key) < 0)       // else check how new node compares to its parent
            y.left = node;                              // if it have lower value make it left child
        else
            y.right = node;                             // else make it right child
    }

    /**
     * Method deleting node from the tree
     * @param key value of the node to delete
     */
    void delete(K key) {
        Node<K> node = searchNode(key);                 // find the node to delete

        if (root == null || node == null)               // if there is no three or no such node return
            return;

        Node<K> x;                                      // create temp node x that will be subtree to reattach
        Node<K> y;                                      // craete temp node y that will be moved node

        if (node.left == null || node.right == null)    // check if node to delete have 0 or 1 child
            y = node;                                   // if we are deleting node with 0 or 1 child, make it y
        else
            y = successor(node);                        // if we are deleting node with 2 children, make it's succesor an y
        // as node's succesor can't have left child, so we end with node with 0 or 1 child

        if (y.left != null)                             // check which node exists
            x = y.left;                                 // if y have left child, make it x
        else
            x = y.right;                                // if y don't have left child, make it's right child x

        if (x != null)                                  // if y's had one child, and so x is not null
            x.parent = y.parent;                        // attach x to the node that is y's parent, so y.parent will have 2 left children now

        if (y.parent == null)                           // if y is root
            root = x;                                   // make x new root
        else if (y == (y.parent).left)                  // if y is left child
            (y.parent).left = x;                        // tell it's parent that x is new left child (swap x for y, meaning deattach y from its parent and tree)
        else                                            // if y is right child
            (y.parent).right = x;                       // do as above, but make x new right child

        if (y != node)                                  // if we were operating on succesor
            node.key = y.key;                           // place that succesor at node's place as y is now deatached from the tree
    }

    /**
     * Method printing out all values from x subtree in ascending order
     * @param x node which subtree is processed (inclusive), root for walk on whole tree
     */
    private void inorder(Node<K> x) {
        if (x != null) {
            inorder(x.left);
            System.out.print(x.key + " ");
            inorder(x.right);
        } else {}
    }

    private void postorder(Node<K> x) {
        if (x != null) {
            postorder(x.right);
            System.out.print(x.key + " ");
            postorder(x.left);
        } else {}
    }

    void postorder() {
        postorder(root);
        System.out.println("");
    }

    /**
     * Method printing all values from the tree in ascending order
     */
    void inorder() {
        inorder(root);
        System.out.println("");
    }

    /**
     * Method finding given node's successor
     * @param x node which successor will be searched
     * @return node with value next to x value
     */
    private Node<K> successor(Node<K> x) {
        if (x.right != null)
            return minimum(x.right);
        Node<K> y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * Method returns minimum node in x node subtree (inclusive)
     * @param x node which subtree is processed (inclusive), root for search in whole tree
     * @return minimum element in x subtree
     */
    private Node<K> minimum(Node<K> x) {
        while (x.left != null)
            x = x.left;
        return x;
    }

    /**
     * Method printing out minimum element in the tree
     */
    void min() {
        Node<K> x = root;

        if (x == null)
            System.out.println("");
        else {
            while (x.left != null)
                x = x.left;
            System.out.println(x.key);
        }
    }

    /**
     * Method returns maximum node in x node subtree (inclusive)
     * @param x node which subtree is processed (inclusive), root for search in whole tree
     * @return maximum element in x subtree
     */
    public Node<K> maximum(Node<K> x) {
        while (x.right != null)
            x = x.right;
        return x;
    }

    /**
     * Method printing out maximum element in the tree
     */
    void max() {
        Node<K> x = root;

        if (x == null)
            System.out.println("");
        else {
            while (x.right != null)
                x = x.right;
            System.out.println(x.key);
        }
    }
}
