import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class QuickMergeSort extends Sort {

    private ArrayList<Integer> temp;

    public QuickMergeSort(boolean type, int size) {
        super(type, size);
        temp = new ArrayList<>(data);
    }

    public void sort(int start, int end) {
        if (start < end) {
            // gdy mamy 1000 lub mniej elementow do posortowania stosujemy QuickSort
            if ((end - start) < 1000) {
                quickSort(start, end);
            } else {
                int middle = (int) Math.floor((double) (start + end) / 2);

                sort(start, middle);
                sort(middle, end);

                merge(start, middle, end);
            }
        }
    }

    public void quickSort(int start, int end) {
        if (start < end) {
            int pivot = partitionLog(start, end);

            sortLog(start, pivot - 1);
            sortLog(pivot + 1, end);
        }
    }

    private int partition(int start, int end) {
        int pivot = selectPivot(start, end);
        int pivotValue = data.get(pivot);

        swap(pivot, end);

        int index = start;
        for (int i = start; i < end; i++) {
            if (compare(data.get(i), pivotValue) < 0) {
                swap(i, index);
                index++;
            }
        }
        swap(index, end);

        return index;
    }

    private int selectPivot(int start, int end) {
        if ((end - start) >= 3) {
            int a, b, c;
            int aV, bV, cV;
            a = start + rand.nextInt((end - start) + 1);
            aV = data.get(a);
            b = start + rand.nextInt((end - start) + 1);
            bV = data.get(b);
            c = start + rand.nextInt((end - start) + 1);
            cV = data.get(c);

            if (aV < bV)
                if (bV < cV)
                    return b;
                else if (aV < cV)
                    return c;
                else
                    return a;
            else if (aV < cV)
                return a;
            else if (bV < cV)
                return c;
            else
                return b;
        } else {
            return start;
        }
    }


    private void merge(int start, int middle, int end) {
        for (int i = start; i <= end; i++) {
            temp.set(i, data.get(i));
        }

        int i = start, k = start;
        int j = middle + 1;

        while (i <= middle && j <= end) {
            if (compare(temp.get(i), temp.get(j)) <= 0) {
                set(k, temp.get(i));
                i++;
            } else {
                set(k, temp.get(j));
                j++;
            }
            k++;
        }

        while (i <= middle) {
            set(k, temp.get(i));
            k++;
            i++;
        }
        while (j <= end) {
            set(k, temp.get(j));
            k++;
            j++;
        }
    }

    public void sortLog(int start, int end) {
        if (start < end) {
            // gdy mamy 1000 lub mniej elementow do posortowania stosujemy QuickSort
            if ((end - start) < 1000) {
                print_log(start + "-" + end + ": part smaller than 1000, will use quick sort");
                quickSortLog(start, end);
            } else {

                print_log(start + "-" + end + ": part bigger than 1000, will use merge sort");
                int middle = (int) Math.floor((double) (start + end) / 2);
                print_log("partition: " + start + "-" + middle + " and " + (middle+1) + "-" + end);
                sortLog(start, middle);
                print_log(start + "-" + middle + " is sorted, now for the next step...");
                sortLog(middle, end);
                print_log((middle+1) + "-" + end + " is sorted, now for the next step...");
                print_log("merging is starting");
                merge(start, middle, end);
                print_log(start + " to " + end + " is merged");
            }
        }
    }

    public void quickSortLog(int start, int end) {
        if (start < end) {
            int pivot = partition(start, end);
            print_log("partition: " + start + "-" + (pivot-1) + " and " + (pivot+1) + "-" + end);
            sort(start, pivot - 1);
            sort(pivot + 1, end);
        }
    }

    private int partitionLog(int start, int end) {
        int pivot = selectPivot(start, end);
        int pivotValue = data.get(pivot);
        print_log("pivot index: "+ pivot + " value:" + pivotValue);

        swapLog(pivot, end);

        int index = start;
        for (int i=start; i<end; i++) {
            if (compareLog(data.get(i), pivotValue) < 0) {
                swapLog(i, index);
                index++;
            }
        }
        swapLog(index, end);

        return index;
    }

    public static void main(String[] args) {
        boolean type;
        if (Integer.parseInt(args[0]) <= 0)
            type = false;
        else
            type = true;

        int size = Integer.parseInt(args[1]);
        //*/
        QuickMergeSort qms = new QuickMergeSort(type, size);
        qms.sortLog(0, qms.dataSize - 1);

        qms.print_log("data is now sorted (" + qms.compares + " compares, " + qms.swaps + " swaps)!");
        qms.print();
        /*/
        QuickMergeSort qms;
        PrintWriter pw;
        try {
        // wyniki testow zapisujemy do pliku 'lookingfork2'
            pw = new PrintWriter("lookingfork2.txt");

            // dla k od 50 do 1950 z krokiem co 50 wykonujemy testy
            for (int i=50; i<2000; i+=50) {
                int compares = 0;
                int swaps = 0;
                // dla kazdego k wykonujemy 200 prob
                for(int j=0; j<200; j++) {
                	// sortujemy zawsze 50 000 elementow , typ ustalany jest w argumentach programu
                    qms = new QuickMergeSort(type, 50000);
                    qms.sort(0, qms.dataSize-1, i);
                    compares += qms.compares;
                    swaps += qms.swaps;
                }
                // srednia z 200 prob zapisujemy do pliku wraz z wartoscia k
                pw.println(i + "\t" + (double) (compares/200) + "\t" + (double) (swaps/200));
            }

            pw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//*/
    }

    // metoda sluzaca do testowania momentu zmiany algorytmu, przyjmuje jako argument takze k ktore ustala moment zmiany
    public void sort(int start, int end, int k) {
        if (start < end) {
            if ((end - start) < k) {
                quickSort(start, end);
            } else {
                int middle = (int) Math.floor((double) (start + end) / 2);

                sort(start, middle, k);
                sort(middle, end, k);

                merge(start, middle, end);
            }
        }
    }
}
