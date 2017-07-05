
public class DualPivotQuickSort extends Sort {

    DualPivotQuickSort ( boolean type, int size) {
        super(type, size);
    }

    private void insertSort(int start, int end) {
        int j, key;
        for(int i=start+1; i<=end; i++) {
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

    public void sort(int start, int end) {
        sort(start, end, 3);
    }

    private void sort(int start,int end, int div) {
        if ((end - start) >= 27) {
            int third = (end-start) / div;


            int m1 = start + third;
            int m2 = end - third;
            if (m1 <= start) {
                m1 = start + 1;
            }
            if (m2 >= end) {
                m2 = end - 1;
            }
            if (compare(data.get(m1), data.get(m2))<0) {
                swap(m1, start);
                swap(m2, end);
            } else {
                swap(m1, end);
                swap(m2, start);
            }

            int pivot1 = data.get(start);
            int pivot2 = data.get(end);

            int less = start + 1;
            int great = end - 1;
            // sorting
            for (int k = less; k <= great; k++) {
                if (compare(data.get(k), pivot1)<0) {
                    swap(k, less++);
                } else if (compare(data.get(k), pivot2)>0) {
                    while (k < great && compare(data.get(great), pivot2)>0) {
                        great--;
                    }
                    swap(k, great--);
                    if (compare(data.get(k), pivot1)<0) {
                        swap(k, less++);
                    }
                }
            }

            int dist = great - less;
            if (dist < 13) {
                div++;
            }
            swap(less - 1, start);
            swap(great + 1, end);

            sort(start, less - 2, div);
            sort(great + 2, end, div);
            // equal elements
            if (dist > (end-start) - 13 && compare(pivot1, pivot2) != 0) {
                for (int k = less; k <= great; k++) {
                    if (compare(data.get(k), pivot1) == 0) {
                        swap(k, less++);
                    } else if (compare(data.get(k), pivot2) == 0) {
                        swap(k, great--);
                        if (compare(data.get(k), pivot1) == 0) {
                            swap(k, less++);
                        }
                    }
                }
            }

            if (compare(pivot1, pivot2) < 0) {
                sort(less, great, div);
            }
        } else { // insertion sort for tiny array
            insertSort(start, end);
        }
    }

    public static void main(String[] args) {
        boolean type;
        if(Integer.parseInt(args[0]) <= 0)
            type = false;
        else
            type = true;

        int size = Integer.parseInt(args[1]);

        DualPivotQuickSort dpqs = new DualPivotQuickSort(type, size);
        dpqs.sort(0, dpqs.dataSize-1);
        dpqs.print_log("data is now sorted (" + dpqs.compares + " compares, " + dpqs.swaps + " swaps)!");
        dpqs.print();
    }

}
