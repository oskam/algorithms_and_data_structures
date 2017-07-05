/**
 * Created by Magdalena on 2017-04-09.
 */
class Node<K extends Comparable<K>> {
    /** Node key */
    K key;
    /** Node's parent reference */
    Node<K> parent;
    /** Node's left child reference */
    Node<K> left;
    /** Node's right child reference */
    Node<K> right;

    /**
     * Node class constructor.
     *
     * @param key
     *            node key
     */
    Node(K key) {
        this.key = key;
        left = null;
        right = null;
    }
}

