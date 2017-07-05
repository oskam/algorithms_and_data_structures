/**
 * Created by Magdalena on 2017-04-12.
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    private PrintWriter writer;
    private String fileName;
    private Random generator = new Random();
    private ArrayList<Integer> nodes = new ArrayList<>();
    private int compares = 0;


    private Test(String fileName) {
        this.fileName = fileName;
        try {
            writer = new PrintWriter(this.fileName);
            writer.write("");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveStats(int n, int compares) {
        writer.println(n + "\t" + compares);
    }

    private BinarySearchTree<Integer> createRandomTree(int size){
        nodes.clear();
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        for (int i=0; i<size; i++ ) {
            int rand_node = generator.nextInt(size);
//            System.out.println(rand_node);
            t.insert(rand_node);
            nodes.add(rand_node);
        }
        return t;
    }

    private BinarySearchTree<Integer> createUnbalancedTree(int size){
        nodes.clear();
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        for (int i=0; i<size; i++ ) {
            int node = i;
//            System.out.println(rand_node);
            t.insert(node);
            nodes.add(node);
        }
        return t;
    }
//
//    private void testForN( int n) {
//        int trials = 200;
//        int step = 50;
//        int rand_index;
//        for (int i=0; i<trials; i++) {
//            BinarySearchTree<Integer> tree = createTree(n*step);
////            System.out.println(nodes);
//            rand_index = generator.nextInt(n*step);
//            int node = nodes.get(rand_index);
////            System.out.println(node);
////            System.out.println(tree);
//            tree.find(node);
//            compares += tree.compares;
//         }
//        compares /= trials;
//        saveStats(n*step, compares);
//        }

    private void testForN(int testType, int n) {
        int trials = 200;
        int step = 50;
        int rand_index;
        for (int i = 0; i < trials; i++) {
            switch (testType) {
                case 0:
                    BinarySearchTree<Integer> tree = createRandomTree(n * step);
                    rand_index = generator.nextInt(n * step);
                    int node = nodes.get(rand_index);
                    tree.find(node);
                    compares += tree.compares;
                    break;
                case 1:
                    BinarySearchTree<Integer> tree2 = createUnbalancedTree(n * step);
                    node = nodes.get(0);
                    tree2.find(node);
                    compares += tree2.compares;
                    break;
                case 2:
                    BinarySearchTree<Integer> tree3 = createUnbalancedTree(n * step);
                    node = nodes.get(n*step-1);
                    tree3.find(node);
                    compares += tree3.compares;
                    break;
            }

        }
        compares /= trials;
        saveStats(n * step, compares);
    }

    public static void main(String[] args) {
        int n = 200;
//
        Test averStat = new Test("averagestats.txt");
        averStat.writer.println("Average Case");
        for (int i = 1; i <= n; i++) {
            averStat.testForN(0, i);
        }
        averStat.writer.close();
        System.out.println("Binary Search Tree statistics are ready");
//
//        Test bestStat = new Test("besttats.txt");
//        bestStat.writer.println("Best Case");
//        for (int i = 1; i <= 10; i++) {
//            bestStat.testForN(1, i);
//        }
//        bestStat.writer.close();
//        System.out.println("Binary Search Tree statistics are ready");
//
//        Test worstStat = new Test("worststats.txt");
//        worstStat.writer.println("Worst Case");
//        for (int i = 1; i <= 10; i++) {
//            worstStat.testForN(2, i);
//        }
//        worstStat.writer.close();
//        System.out.println("Binary Search Tree statistics are ready");
    }
}
