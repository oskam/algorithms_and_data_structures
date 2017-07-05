
public class Node<K extends Comparable<K>> {
        /** Node key */
        public K key;
        /** Node rank */
        public Integer lSize;
        /** Node's parent reference */
        public Node<K> parent;
        /** Node's left child reference */
        public Node<K> left;
        /** Node's right child reference */
        public Node<K> right;

        /**
         * Node class constructor.
         * 
         * @param key
         *            node key
         */
        public Node(K key) {
                this.key = key;
                lSize = 0;
                left = null;
                right = null;
        }
}
