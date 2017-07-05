/**
 * Created by Magdalena on 2017-04-03.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RandomizedSelect extends SelectSuperclass {

    public RandomizedSelect(boolean type, int size, int k) {
        super(type, size, k);
    }

    @Override
    protected int select(int start, int end, int i) {
        while (true) {
            if (start == end) {
                return data.get(start);
            }
            // GROUP LIST INTO ELEMENTS SMALLER THAN PIVOT
            // AND LARGER THAN PIVOT WITH IT IN THE MIDDLE
            // THEN RETURN THE PIVOT INDEX
            int pivot = partition(start, end);
            // COMPARING 'i-1' WITH 'pivot' AS WE ARE LOOKING
            // FOR i-TH ELEMENT COUNTING FROM 1 AND INDEXES ARE FROM 0
            // IF PIVOT IS i-TH INDEX RETURN IT
            if (i-1 == pivot)
                return data.get(pivot);
                // i-TH ELEMENT IS IN GROUP OF SMALLER ELEMENTS THAN PIVOT
            else
            if (i-1 < pivot)
                end = pivot - 1;
                // i-TH ELEMENT IS IN GROUP OF GREATER ELEMENTS THAN PIVOT
            else
                start = pivot + 1;
        }
    }

    protected int partition(int start, int end) {
        // SELECT PIVOT INDEX AS MEDIAN OF 3 RANDOM ELEMENTS
        int pivot = selectPivot(start, end);
        int pivotValue = data.get(pivot);
        swap(pivot, end);
        int index = start;
        for (int i=start; i<end; i++) {
            // IF ELEMENT IS LESSER THAN PIVOT VALUE
            if (compare(data.get(i), pivotValue) < 0) {
                // MOVE IT TO INDEX (BEFORE PIVOT)
                swap(i, index);
                index++;
            }
        }
        swap(index, end);
        // RETURN PIVOT INDEX NOW EQUAL TO THE INDEX TO CALLING METHOD
        return index;
    }

    protected int selectPivot(int start, int end) {
        // DECISION TREE FOR 3 RANDOMLY CHOSEN ELEMENTS
        // TO GET BETTER PIVOT INDEX THAN CHOOSING ONE AT RANDOM
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

    protected int selectLog(int start, int end, int i) {
        print_log("looking for " + i + "-th element in array");
        while (true) {
            if (start == end) {
                print_log("1 element in array, k-th element is " + data.get(start));
                return data.get(start);
            }
            int pivot = partitionLog(start, end);

            if (i-1 == pivot) {
                print_log("k-th element is " + data.get(pivot));
                return data.get(pivot);
            } else {
                if (i-1 < pivot) {
                    print_log("k-th element is in left part, select: " + start + "-" + (pivot - 1) + " with k: " + i);
                    end = pivot - 1;
                } else {
                    print_log("k-th element is in right part, select: " + (pivot + 1) + "-" + end + " with k: " + (i - pivot));
                    start = pivot + 1;
                }
            }
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

        print_log("array after partition: ");
        //print();

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
        if (args.length !=3) {
            System.out.println("Wrong arguments, give: <type> <size> <k>");
        }
        boolean type = true;
        int size = 0;
        int k = 0;

        try {
            if(Integer.parseInt(args[0]) <= 0)
                type = false;
            else
                type = true;

            size = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.out.println("Wrong arguments.");
        }

        RandomizedSelect rs = new RandomizedSelect(type, size, k);
        int result = rs.selectLog(0, rs.dataSize-1, k);
        rs.print_log("k-th element: " + result + " found in " + rs.compares + " compares");
        rs.print();
        System.out.println("");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        rs.print_log("Press Enter to sort");
        try
        {
            if (br.readLine() != null) {
                rs.quickSort(0, rs.dataSize-1);
                rs.print();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
