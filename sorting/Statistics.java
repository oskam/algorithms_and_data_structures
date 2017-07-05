import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Statistics {

    private String fileName;

    PrintWriter writer;

    private Sort sort;

    public Statistics(String fileName) {
        this.fileName = fileName;
        try {
            writer = new PrintWriter(this.fileName);
            writer.write("");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveStats(int n, int compares, int swaps) {
        writer.println(n + "\t" + (double) compares + "\t" + (double) swaps);
    }

    private void testForN(int sortType, int n) {
        int compares = 0, swaps = 0;
        int trials = 100;
        int step = 100;

        for (int i=0; i<trials ; i++) {
            switch (sortType) {
                case 0:
                    sort = new InsertionSort(true, n*step);
                    sort.sort();
                    break;
                case 1:
                    sort = new MergeSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
                case 2:
                    sort = new QuickSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
                case 3:
                    sort = new InsertQuickSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
                case 4:
                    sort = new QuickMergeSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
                case 5:
                    sort = new DualPivotQuickSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
                case 6:
                    sort = new InsertMergeSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
                case 7:
                    sort = new RadixSort(true, n*step, n*step*10);
                    sort.sort();
                    break;
                case 8:
                    sort = new SelectSort(true, n*step);
                    sort.sort(0, sort.dataSize-1);
                    break;
            }
            compares += sort.compares;
            swaps += sort.swaps;
        }
        compares /= trials;
        swaps /= trials;

        saveStats(n*step, compares, swaps);
    }

    public static void main(String[] args) {
        int n = 100;

        // kazdy kolejny fragment odpowiada za testowanie kolejnych algorytmow,
        // komentujac odpowiednie fragmenty mozna testowac tylko te wybrane algorytmy,
        // wyniki kazdego testu sa zapisywane do osobnego pliku

//        Statistics isStat = new Statistics("insertstats.txt");
//        isStat.writer.println("InsertionSort");
//        for (int i=1; i<=n; i++) {
//            isStat.testForN(0, i);
//        }
//        isStat.writer.close();
//        System.out.println("Insertion sort statistics are ready");
//
//        Statistics msStat = new Statistics("mergestats.txt");
//        msStat.writer.println("MergeSort");
//        for (int i=1; i<=n; i++) {
//            msStat.testForN(1, i);
//        }
//        msStat.writer.close();
//        System.out.println("Merge sort statistics are ready");
//
        Statistics qsStat = new Statistics("quickstats.txt");
        qsStat.writer.println("QuickSort");
        for (int i=1; i<=n; i++) {
            qsStat.testForN(2, i);
        }
        qsStat.writer.close();
        System.out.println("Quick sort statistics are ready");
//
//        Statistics iqsStat = new Statistics("insertquickstats.txt");
//        iqsStat.writer.println("InsertQuickSort");
//        for (int i=1; i<=n; i++) {
//            iqsStat.testForN(3, i);
//        }
//        iqsStat.writer.close();
//        System.out.println("Insert-Quick sort statistics are ready");
//
//        Statistics qmsStat = new Statistics("quickmergestats.txt");
//        qmsStat.writer.println("QuickMergeSort");
//        for (int i=1; i<=n; i++) {
//            qmsStat.testForN(4, i);
//        }
//        qmsStat.writer.close();
//        System.out.println("Quick-Merge sort statistics are ready");
//
//        Statistics dpqsStat = new Statistics("dpquickstats.txt");
//        dpqsStat.writer.println("DualPivotQuickSort");
//        for (int i=1; i<=n; i++) {
//            dpqsStat.testForN(5, i);
//        }
//        dpqsStat.writer.close();
//        System.out.println("DualPivotQuick sort statistics are ready");
//
//        Statistics imsStat = new Statistics("insertmergestats.txt");
//        imsStat.writer.println("InsertMergeSort");
//        for (int i=1; i<=n; i++) {
//            imsStat.testForN(6, i);
//        }
//        imsStat.writer.close();
//        System.out.println("Insert-Merge sort statistics are ready");

//        Statistics rsStat = new Statistics("radixsortstats.txt");
//        rsStat.writer.println("RadixSort");
//        for (int i=1; i<=n; i++) {
//            rsStat.testForN(7, i);
//        }
//        rsStat.writer.close();
//        System.out.println("Radix sort statistics are ready");
//
//        Statistics ssStat = new Statistics("selectstats.txt");
//        rsStat.writer.println("SelectSort");
//        for (int i=1; i<=n; i++) {
//            ssStat.testForN(8, i);
//        }
//        ssStat.writer.close();
//        System.out.println("Select sort statistics are ready");

    }
}
