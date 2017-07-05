import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SuppressWarnings("unused")
public class InsertQuickSort extends Sort {

    public InsertQuickSort( boolean type, int size ) {
        super(type, size);
    }

    public void sort(int start, int end) {
        if (start < end) {
            // gdy fragment do sortowania ma 6 lub mniej elementow (indeksujemy od 0, wiec 0 do 5 to 6 elementow, ale 5-0=5 < 6)
            if ((end-start) < 6) {
                insertSort(start, end);
            } else {
                int pivot = partition(start, end);
                // po partycji sortujemy, bez pivota, bo jest na dobrym miejscu
                sort(start, pivot-1);
                sort(pivot+1, end);
            }
        }
    }

    private void insertSort(int start, int end) {
        int j, key;
        for(int i=start+1; i<end; i++) {
            key = data.get(i);
            j = i-1;

            while(j>=start && compare(data.get(j), key)>0) {
                data.set(j+1, data.get(j));
                this.swaps++;
                j--;
            }
            data.set(j+1, key);
        }
    }

    private int partition(int start, int end) {
        int pivot = selectPivot(start, end);
        int pivotValue = data.get(pivot);

        swap(pivot, end);

        int index = start;
        for (int i=start; i<end; i++) {
            if (compare(data.get(i), pivotValue) < 0) {
                swap(i, index);
                index++;
            }
        }
        swap(index, end);

        return index;
    }

    private int selectPivot(int start, int end) {
        int a,b,c;
        int aV,bV,cV;
        a = start + rand.nextInt((end-start)+1);
        aV = data.get(a);
        b = start + rand.nextInt((end-start)+1);
        bV = data.get(b);
        c = start + rand.nextInt((end-start)+1);
        cV = data.get(c);

        if (aV < bV)
            if (bV < cV)
                return b;
            else
            if (aV < cV)
                return c;
            else
                return a;
        else
        if (aV < cV)
            return a;
        else
        if (bV < cV)
            return c;
        else
            return b;
    }

    public void sortLog(int start, int end) {
        if (start < end) {
            if ((end-start) < 7) {
                print_log(start + "-" + end + ": part too small, will use insert sort");
                insertSortLog(start, end);
            } else {
                print_log(start + "-" + end + ": part big enough, will use quick sort");
                print_log("choosing pivot");
                int pivot = partitionLog(start, end);

                print_log("partition: " + start + "-" + (pivot-1) + " and " + (pivot+1) + "-" + end);
                sortLog(start, pivot-1);
                sortLog(pivot+1, end);
            }
        }
    }

    public void insertSortLog(int start, int end) {
        int j;
        for(int i=start+1; i<=end; i++) {
            j = i-1;
            print_log("key = " + data.get(i));

            while(j>=0 && compareLog(data.get(j), data.get(j+1))>0) {
                print_log("swapping " + data.get(j) + " > " + data.get(j+1));
                swapLog(j+1, j);
                j--;
            }
            print_log("key is sorted, now for the next step...");
        }
    }

    private int partitionLog(int start, int end) {
        int pivot = selectPivotLog(start, end);
        int pivotValue = data.get(pivot);
        print_log("pivot index: "+ pivot + " value: " + pivotValue);

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

    private int selectPivotLog(int start, int end) {
        int a,b,c;
        int aV,bV,cV;
        a = start + rand.nextInt((end-start)+1);
        aV = data.get(a);
        print_log("a: " + aV);
        b = start + rand.nextInt((end-start)+1);
        bV = data.get(b);
        print_log("b: " + bV);
        c = start + rand.nextInt((end-start)+1);
        cV = data.get(c);
        print_log("c: " + cV);

        if (aV < bV)
            if (bV < cV)
                return b;
            else
            if (aV < cV)
                return c;
            else
                return a;
        else
        if (aV < cV)
            return a;
        else
        if (bV < cV)
            return c;
        else
            return b;
    }

    public static void main(String[] args) {
        boolean type;
        if(Integer.parseInt(args[0]) <= 0)
            type = false;
        else
            type = true;

        int size = Integer.parseInt(args[1]);
        //*/
        InsertQuickSort iqs = new InsertQuickSort(type, size);
        iqs.sortLog(0, iqs.dataSize-1);
        iqs.print_log("data is now sorted (" + iqs.compares + " compares, " + iqs.swaps + " swaps)!");
        iqs.print();
        /*/
        InsertQuickSort iqs;
        PrintWriter pw;
        try {
        	// bedziemy zapisywac do pliku 'lookingfork'
            pw = new PrintWriter("lookingfork.txt");

            // wykonujemy test dla k od 1 do 29
            for (int i=1; i<30; i++) {
                int compares = 0;
                int swaps = 0;
                // dla kazdego k wykonujemy 100 prob
                for(int j=0; j<100; j++) {
                // sortujemy zawsze 10 000 elementow, typ ustalany jest w argumentach wywolania
                    iqs = new InsertQuickSort(type, 10000);
                    iqs.sort(0, iqs.dataSize-1, i);
                    compares += iqs.compares;
                    swaps += iqs.swaps;
                }
                // srednia ze 100 prob jest zapisywana do pliku wraz z wartoscia k
                pw.println(i + "\t" + (double) (compares/100) + "\t" + (double) (swaps/100));
            }

            pw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//*/

    }

    // metoda sortowania, ktora przelacza sie na insertsort gdy fragment do posortowania ma k elementow
    public void sort(int start, int end, int k) {
        if (start < end) {
            if ((end-start) < k) {
                insertSort(start, end);
            } else {
                int pivot = partition(start, end);

                sort(start, pivot-1, k);
                sort(pivot+1, end, k);
            }
        }
    }


}
