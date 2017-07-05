
public class InsertionSort extends Sort {

    public InsertionSort( boolean type, int size ) {
        super(type, size);
    }

    public void sort() {
        int j, key;
        for(int i=1; i<dataSize; i++) {
            key = data.get(i);
            j = i-1;

            while(j>=0 && compare(data.get(j), key)>0) {
                data.set(j+1, data.get(j));
                this.swaps++;
                j--;
            }
            data.set(j+1, key);
        }
    }

    public void sortLog() {
        int j, key;
        print_log("insertion sort is starting");
        for(int i=1; i<dataSize; i++) {
            key = data.get(i);
            j = i-1;
            print_log("key = " + data.get(i));

            while(j>=0 && compare(data.get(j), key)>0) {
                data.set(j+1, data.get(j));
                print_log("swapping " + data.get(j) + " > " + data.get(j+1));
                this.swaps++;
                j--;
            }
            data.set(j+1, key);
            print_log("inserting key at sorted position");
            print_log("key is sorted, now for the next step...");
        }
    }

    public static void main(String[] args) {
        boolean type;
        if(Integer.parseInt(args[0]) <= 0)
            type = false;
        else
            type = true;

        int size = Integer.parseInt(args[1]);

        InsertionSort is = new InsertionSort(type, size);
        is.sortLog();
        is.print_log("data is now sorted (" + is.compares + " compares, " + is.swaps + " swaps)!");
        is.print();
    }
}
