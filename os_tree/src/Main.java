

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    
    private static OSTree<Integer> ost = null;
    private static DeterministicSelect ds = null;
    private static RandomizedSelect rs = null;
    
    private static Random random = new Random();
    private static ArrayList<Integer> data = new ArrayList<>();

    private static long ostTime = 0;
    private static long dsTime = 0;
    private static long rsTime = 0;
    
    private static long start = 0;
    private static long end = 0;

    private static void test() throws FileNotFoundException {
        
        int k;
        int trials = 10000;
        int size = 1000;

        int[] ks = new int[trials];
        for (int i=0; i<ks.length; i++) {
            ks[i] = random.nextInt(size) + 1;
        }

        System.out.println("random k-th statistic selected " + trials + " times from new random data sets of " + size);
        ostTime = 0;
        dsTime = 0;
        rsTime = 0;
        for (int i = 0; i < trials; i++) {
            data = generateRandomData(size);
            k = ks[i];
            
            start = System.currentTimeMillis();
            ost = new OSTree<>();
            for (Integer value : data) {
                //System.out.println(value);
                ost.insert(value);
            }
            ost.osSelect(k);   
            end = System.currentTimeMillis();
            
            ostTime += (end - start);
            
            start = System.currentTimeMillis();
            ds = new DeterministicSelect(data);
            ds.detSelect(k);
            end = System.currentTimeMillis();
            
            dsTime += (end - start);
            
            start = System.currentTimeMillis();
            rs = new RandomizedSelect(data);
            rs.randSelect(k);
            end = System.currentTimeMillis();
            
            rsTime += (end - start);
        }
        
        System.out.println("OSTSelect time: " + ostTime);
        System.out.println("DetSelect time: " + dsTime);
        System.out.println("RndSelect time: " + rsTime);
        
        System.out.println("random k-th statistic selected " + trials + " times from new sorted data sets of "+ size);
        ostTime = 0;
        dsTime = 0;
        rsTime = 0;
        data = generateSortedData(size);
        for (int i = 0; i < trials; i++) {

            k = ks[i];
            
            start = System.currentTimeMillis();
            ost = new OSTree<>();
            for (Integer value : data) {
                //System.out.println(value);
                ost.insert(value);
            }
            ost.osSelect(k);   
            end = System.currentTimeMillis();
            
            ostTime += (end - start);
            
            start = System.currentTimeMillis();
            ds = new DeterministicSelect(data);
            ds.detSelect(k);
            end = System.currentTimeMillis();
            
            dsTime += (end - start);
            
            start = System.currentTimeMillis();
            rs = new RandomizedSelect(data);
            rs.randSelect(k);
            end = System.currentTimeMillis();
            
            rsTime += (end - start);
        }
        
        System.out.println("OSTSelect time: " + ostTime);
        System.out.println("DetSelect time: " + dsTime);
        System.out.println("RndSelect time: " + rsTime);
        
        System.out.println("random k-th statistic selected " + trials + " times from one random data set of " + size);
        ostTime = 0;
        dsTime = 0;
        rsTime = 0;
        data = generateRandomData(size);
        ost = new OSTree<>();
        for (Integer value : data) {
            //System.out.println(value);
            ost.insert(value);
        }

        ds = new DeterministicSelect(data);

        rs = new RandomizedSelect(data);
        
        for (int i = 0; i < trials; i++) {
            k = ks[i];
            
            start = System.currentTimeMillis();
            ost.osSelect(k);   
            end = System.currentTimeMillis();
            
            ostTime += (end - start);
            
            start = System.currentTimeMillis();
            ds.detSelect(k);
            end = System.currentTimeMillis();
            
            dsTime += (end - start);
            
            start = System.currentTimeMillis();
            rs.randSelect(k);
            end = System.currentTimeMillis();
            
            rsTime += (end - start);
        }
        
        System.out.println("OSTSelect time: " + ostTime);
        System.out.println("DetSelect time: " + dsTime);
        System.out.println("RndSelect time: " + rsTime);

        System.out.println("random k-th statistic selected " + trials + " times from one sorted data set of " + size);
        ostTime = 0;
        dsTime = 0;
        rsTime = 0;
        data = generateSortedData(size);
        ost = new OSTree<>();
        for (Integer value : data) {
            //System.out.println(value);
            ost.insert(value);
        }

        ds = new DeterministicSelect(data);

        rs = new RandomizedSelect(data);

        for (int i = 0; i < trials; i++) {
            k = ks[i];

            start = System.currentTimeMillis();
            ost.osSelect(k);
            end = System.currentTimeMillis();

            ostTime += (end - start);

            start = System.currentTimeMillis();
            ds.detSelect(k);
            end = System.currentTimeMillis();

            dsTime += (end - start);

            start = System.currentTimeMillis();
            rs.randSelect(k);
            end = System.currentTimeMillis();

            rsTime += (end - start);
        }

        System.out.println("OSTSelect time: " + ostTime);
        System.out.println("DetSelect time: " + dsTime);
        System.out.println("RndSelect time: " + rsTime);
    }
    
    private static ArrayList<Integer> generateRandomData(int size) { 
        ArrayList<Integer> data = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(size * 10 + 1));
        }
        
        return data;
    }
    
    private static ArrayList<Integer> generateSortedData(int size) {
        ArrayList<Integer> data = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            data.add(i);
        }
        
        return data;
    }

    public static void runWithArguments(String[] args) {

        int type = Integer.parseInt(args[0]);
        int value = Integer.parseInt(args[1]);

        OSTree<Integer> tree = new OSTree<Integer>();
        ArrayList<Integer> exampleData = new ArrayList<Integer>(Arrays.asList(26,17,41,14,21,30,47,10,16,19,23,28,38,7,12,15,20,35,39,3));

        for (Integer nodeValue : exampleData) {
            //System.out.println(value);
            tree.insert(nodeValue);
        }

        tree.inorder();

        if (type == 1){
            System.out.println(tree.osSelect(value).key);
        } else {
            System.out.println(tree.osRank(value));
        }
    }

    public static void main(String[] args) throws IOException {
//        test();
        runWithArguments(args);
    }
}
