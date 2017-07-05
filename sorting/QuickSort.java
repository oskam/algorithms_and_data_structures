public class QuickSort extends Sort {

    QuickSort( boolean type, int size ) {
        super(type, size);
    }

    public void sort(int start, int end) {
        if (start < end) {
            int pivot = partition(start, end);

            sort(start, pivot-1);
            sort(pivot+1, end);
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
//        return rand.nextInt((end-start)+1);
        if ((end - start) >= 3) {
            int a,b,c;
            int aV,bV,cV;
            a = start + rand.nextInt((end-start)+1);
            aV = data.get(a);
            b = start + rand.nextInt((end-start)+1);
            bV = data.get(b);
            c = start + rand.nextInt((end-start)+1);
            cV = data.get(c);

            if (compare(aV, bV) < 0)
                if (compare(bV, cV) < 0)
                    return b;
                else
                if (compare(aV, cV) < 0)
                    return c;
                else
                    return a;
            else
            if (compare(aV, cV) < 0)
                return a;
            else
            if (compare(bV, cV) < 0)
                return c;
            else
                return b;
        } else {
            return start;
        }
    }

    private void sortLog(int start, int end) {
        if (start < end) {
            int pivot = partitionLog(start, end);
            print_log("partition: " + start + "-" + (pivot-1) + " and " + (pivot+1) + "-" + end);
            sortLog(start, pivot-1);
            sortLog(pivot+1, end);
        }
    }

    private int partitionLog(int start, int end) {
        int pivot = selectPivotLog(start, end);
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

    private int selectPivotLog(int start, int end) {
        if ((end - start) >= 3) {
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

            if (compareLog(aV, bV) < 0)
                if (compareLog(bV, cV) < 0)
                    return b;
                else
                if (compareLog(aV, cV) < 0)
                    return c;
                else
                    return a;
            else
            if (compareLog(aV, cV) < 0)
                return a;
            else
            if (compareLog(bV, cV) < 0)
                return c;
            else
                return b;
        } else {
            return start;
        }
    }

    public static void main(String[] args) {
        boolean type;
        if(Integer.parseInt(args[0]) <= 0)
            type = false;
        else
            type = true;

        int size = Integer.parseInt(args[1]);

        QuickSort qs = new QuickSort(type, size);
        qs.sortLog(0, qs.dataSize-1);
        qs.print_log("data is now sorted (" + qs.compares + " compares, " + qs.swaps + " swaps)!");
        qs.print();
    }

}
