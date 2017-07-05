import java.util.ArrayList;

public class MergeSort extends Sort {

    private ArrayList<Integer> temp;

    public MergeSort( boolean type, int size ) {
        super(type, size);
        temp = new ArrayList<>(data);
    }

    public void sort(int start, int end) {
        if (start < end) {
            int middle = (int) Math.floor((double) (start + end) / 2);

            sort(start, middle);
            sort(middle + 1, end);

            merge(start, middle, end);
        }
    }

    private void merge(int start, int middle, int end) {
        for (int i = start; i <= end; i++) {
            temp.set(i, data.get(i));
        }

        int i = start, k = start;
        int j = middle+1;

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

    private void sortLog(int start, int end) {
        print_log("merge sort is starting");
        if (start < end) {
            int middle = (int) Math.floor((double) (start + end) / 2);

            print_log("partition: " + start + "-" + middle + " and " + (middle+1) + "-" + end);
            sortLog(start, middle);
            print_log(start + "-" + middle + " is sorted, now for the next step...");
            sortLog(middle + 1, end);
            print_log((middle+1) + "-" + end + " is sorted, now for the next step...");

            mergeLog(start, middle, end);
        }
    }

    private void mergeLog(int start, int middle, int end) {
        print_log("merging is starting");
        for (int i = start; i <= end; i++) {
            temp.set(i, data.get(i));
        }

        int i = start, k = start;
        int j = middle+1;

        while (i <= middle && j <= end) {
            if (compareLog(temp.get(i), temp.get(j)) <= 0) {
                setLog(k, temp.get(i));
                i++;
            } else {
                setLog(k, temp.get(j));
                j++;
            }
            k++;
        }

        while (i <= middle) {
            setLog(k, temp.get(i));
            k++;
            i++;
        }
        print_log(start + " to " + end + " is merged");
    }

    public static void main(String[] args) {
        boolean type;
        if(Integer.parseInt(args[0]) <= 0)
            type = false;
        else
            type = true;

        int size = Integer.parseInt(args[1]);
        //*/
        MergeSort ms = new MergeSort(type, size);
        ms.sortLog(0, ms.dataSize-1);

        ms.print_log("data is now sorted (" + ms.compares + " compares, " + ms.swaps + " swaps)!");
        ms.print();
        /*/
        MergeSort ms;
        double compares = 0;
        for (int i=0; i<1000; i++) {
            ms = new MergeSort(true, 1000);
            ms.sort(0, ms.dataSize-1);
            compares += ms.compares;
        }
        System.out.println((double)compares/1000);
        //*/
    }
}
