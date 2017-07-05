

import java.util.ArrayList;
import java.util.Random;

public class RandomizedSelect {
    /**
     * ArrayList where elements are generated to
     */
    protected ArrayList<Integer> data;
    
    protected Random rand;
    
    public RandomizedSelect( ArrayList<Integer> data) {
        this.data = data;
        
        rand = new Random();
    }
    
    public void randSelect(int k) {
        int x = select(0, data.size() - 1, k);
        
        //System.out.println("element at position " + k + " is: " + x);
    }
    
    protected int select(int start, int end, int i) {
        while (true) {
            if (start == end) {
                return data.get(start);
            }
            int pivot = partition(start, end);
            if (i - 1 == pivot)
                return data.get(pivot);
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
            // IF ELEMENT IS LESSER THAN PIVOT VALUE
            if (compare(data.get(i), pivotValue) < 0) {
                swap(i, index);
                index++;
            }
        }
        swap(index, end);
        return index;
    }
    
    protected int selectPivot(int start, int end) {
        return start + rand.nextInt(end-start);
    }
    
    protected void swap(int i, int j) {
        data.set(i, data.set(j, data.get(i)));
    }
    
    protected int compare(Integer first, Integer second) {
        return ((first < second) ? -1 : ((first > second) ? 1 : 0));
    }
}
