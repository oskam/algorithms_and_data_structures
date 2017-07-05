/**
 * Created by Magdalena on 2017-04-03.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Select extends SelectSuperclass {

    public Select( boolean type, int size, int k ) {
        super(type, size, k);
    }

    @Override
    protected int select(int start, int end, int i) {
        if (start == end)
            return start;
        int pivot = 0;
        while (true) {
            // GROUP LIST INTO ELEMENTS SMALLER THAN PIVOT
            // AND LARGER THAN PIVOT WITH IT IN THE MIDDLE
            // THEN RETURN THE PIVOT INDEX
            pivot = partition(start, end);
            // COMPARING 'i-1' WITH 'pivot' AS WE ARE LOOKING
            // FOR i-TH ELEMENT COUNTING FROM 1 AND INDEXES ARE FROM 0
            // IF PIVOT IS i-TH INDEX RETURN IT
            if (i - 1 == pivot)
                return pivot;
                // i-TH ELEMENT IS IN GROUP OF SMALLER ELEMENTS THAN PIVOT
            else if (i - 1 < pivot)
                end = pivot - 1;
                // i-TH ELEMENT IS IN GROUP OF GREATER ELEMENTS THAN PIVOT
            else
                start = pivot + 1;
        }
    }

    protected int partition(int start, int end) {
        // SELECT PIVOT INDEX AS MEDIAN OF MEDIANS
        int pivot = selectPivot(start, end);
        int pivotValue = data.get(pivot);
        swap(pivot, end);
        int index = start;
        for (int i = start; i < end; i++) {
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
        if (end - start < 5) {
            return median(start, end);
        }
        // GET MEDIANS OF ALL THE 5-THS AND MOVE THEM TO LIST BEGINNING
        for (int i = start; i <= end; i += 5) {
            int end2 = i + 4;
            // IF LAST SUBGROUP EXTENDS BEYOND LIST SIZE
            // CHANGE SUBEND TO LIST END
            if (end2 > end)
                end2 = end;
            // GET MEDIAN OF 5-TH
            int median5 = median(i, end2);
            // MOVE MEDIAN OF 5-TH TO BEGGINING AT INDEX CORRESPONDING
            // TO THAT 5-TH INDEX IN ALL 5-THS
            swap(median5, (start + (int) Math.floor((i - start) / 5)));
        }
        // CALL SELECT ON ALL MEDIANS TO GET MEDIAN OF THE MEDIANS
        // START IS START, END IN CALCULETED BY NUMBER OF 5-THS IN LIST
        // AS THERE ARE THAT MUCH MEDIANS ON THE BEGGINING OF THE LIST NOW
        // AND WE ARE LOOKING FOR MEDIAN OF MEDIANS SO CALLING SELECT
        // LOOKING FOR K-TH ELEMENT WHERE K IS HALF OF ALL MEDIANS
        return select(start, start + (int) Math.ceil((end - start) / 5),
                (int) Math.floor(start + (end - start) / 10) + 1);
    }

    private int median(int start, int end) {
        int j;
        for (int i = start + 1; i <= end; i++) {
            j = i - 1;

            while (j >= start && compare(data.get(j), data.get(j + 1)) > 0) {
                swap(j + 1, j);
                j--;
            }
        }
        return ((int) Math.floor((end + start) / 2));
    }

    private int selectLog(int start, int end, int i) {
        print_log("looking for " + i + "-th element in array " + start + "-" + end);
        if (start == end) {
            print_log("1 element in array, k-th element is " + data.get(start));
            return start;
        }
        int pivot = 0;
        while (true) {
            pivot = partitionLog(start, end);

            if (i - 1 == pivot) {
                return pivot;
            } else if (i - 1 < pivot) {
                print_log("k-th element is in left part, now end is: " + (pivot - 1));
                end = pivot - 1;
            } else {
                print_log("k-th element is in right part, now start is: " + (pivot + 1));
                start = pivot + 1;
            }
        }
    }

    private int partitionLog(int start, int end) {
        int pivot = selectPivotLog(start, end);
        int pivotValue = data.get(pivot);
        print_log("pivot index: " + pivot + " value:" + pivotValue);

        swapLog(pivot, end);

        int index = start;
        for (int i = start; i < end; i++) {
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
        if (end - start < 5) {
            print_log("5 or less elements, just return median as pivot");
            return medianLog(start, end);
        }

        for (int i = start; i <= end; i += 5) {
            int end2 = i + 4;
            if (end2 > end)
                end2 = end;
            print_log("5ve: " + i + "-" + end2);
            int median5 = medianLog(i, end2);
            print_log("median of 5ve: " + data.get(median5));
            swapLog(median5, (start + (int) Math.floor((i - start) / 5)));
           // print();
        }
        return selectLog(start, start + (int) Math.ceil((end - start) / 5),
                (int) Math.floor(start + (end - start) / 10) + 1);
    }

    private int medianLog(int start, int end) {
        print_log("searching for median using insertsort");
        int j;
        for (int i = start + 1; i <= end; i++) {
            j = i - 1;

            while (j >= start && compareLog(data.get(j), data.get(j + 1)) > 0) {
                swapLog(j + 1, j);
                j--;
            }
        }
        return ((int) Math.floor(start + (end - start) / 2));
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.out.println("Wrong arguments, give: <type> <size> <k>");
        }
        boolean type = true;
        int size = 0;
        int k = 0;

        try {
            if (Integer.parseInt(args[0]) <= 0)
                type = false;
            else
                type = true;

            size = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.out.println("Wrong arguments.");
        }

        Select s = new Select(type, size, k);
        int result = s.data.get(s.selectLog(0, s.dataSize - 1, k));
        s.print_log("k-th element: " + result + " found in " + s.compares + " compares");
        s.print();
        System.out.println("");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Thread.sleep(100);
        s.print_log("Press Enter to sort");
        try {
            if (br.readLine() != null) {
                s.quickSort(0, s.dataSize - 1);
                s.print();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
