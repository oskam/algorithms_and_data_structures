

import java.util.ArrayList;
//import java.util.Random;

public class DeterministicSelect {
    /**
     * ArrayList where elements are generated to
     */
    protected ArrayList<Integer> data;
   
    //protected Random rand;
    
    public DeterministicSelect(ArrayList<Integer> data) {
        this.data = data;
        
        //rand = new Random();
    }
    
    public void detSelect(int k) {
        int x = select(0, data.size() - 1, k);
        
        //System.out.println("element at position " + k + " is: " + x);
    }
    
    protected int select(int start, int end, int i) {
        if (start == end)
            return start;
        int pivot = 0;
        while (true) {
            pivot = partition(start, end);
            if (i - 1 == pivot)
                return pivot;
            else if (i - 1 < pivot)
                end = pivot - 1;
            else
                start = pivot + 1;
        }
    }
    
    protected int partition(int start, int end) {
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
    
    protected int selectPivot(int start, int end) {
        if (end - start < 5) {
            return median(start, end);
        }
        for (int i = start; i <= end; i += 5) {
            int end2 = i + 4;
            if (end2 > end)
                end2 = end;
            int median5 = median(i, end2);
            swap(median5, (start + (int) Math.floor((i - start) / 5)));
        }
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
    
    protected void swap(int i, int j) {
        data.set(i, data.set(j, data.get(i)));
    }
    
    protected int compare(Integer first, Integer second) {
        return ((first < second) ? -1 : ((first > second) ? 1 : 0));
    }
}
