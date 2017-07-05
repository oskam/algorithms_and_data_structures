import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public abstract class Sort {
    /**
     * Decides what type of data to generate
     * true == random, false == descending
     */
    protected boolean randomDataType = true;

    /**
     * Size of data to generate and sort
     */
    protected int dataSize;

    /**
     * ArrayList where elements are generated to,
     * then they are sorted, and finally holds sorted elements
     */
    protected ArrayList<Integer> data;

    /**
     * Amount of compares done during sorting
     */
    protected int compares;

    /**
     * Amount of swaps done during sorting
     */
    protected int swaps;

    protected int scope;

    protected Random rand;

    public Sort(boolean type, int size){
        setDataSize(size);
        setRandomDataType(type);
        compares = 0;
        swaps = 0;
        data = new ArrayList<>();
        rand = new Random();

        generateData();

        if (dataSize < 100) {
            for (int value : data) {
                print_log(value + "");
            }
        }
    }

    public Sort(boolean type, int size, int scope){
        setDataSize(size);
        setRandomDataType(type);
        this.scope = scope;
        compares = 0;
        swaps = 0;
        data = new ArrayList<>();
        rand = new Random();

        generateScope();

        if (dataSize < 100) {
            for (int value : data) {
                print_log(value + "");
            }
        }
    }

    private void generateData() {
        if(randomDataType) {
            generateRandomData();
            print_log("generating random data of size " + dataSize);
        }
        else {
            generateDescData();
            print_log("generating descending data of size " + dataSize);
        }
    }

    protected void swap(int i, int j) {
        data.set(i, data.set(j, data.get(i)));
        swaps++;
    }

    protected void swapLog(int i, int j) {
        print_log("swapping");
        data.set(i, data.set(j, data.get(i)));
        swaps++;
    }

    protected void set(int i, int value) {
        data.set(i, value);
        swaps++;
    }

    protected void setLog(int i, int value) {
        print_log("swapping");
        data.set(i, value);
        swaps++;
    }

    protected int compare(Integer first, Integer second) {
        compares++;
        return ((first < second) ? -1 : ((first > second ) ? 1 : 0));
    }

    protected int compareLog(Integer first, Integer second) {
        compares++;
        print_log("comparing " + first + " and " + second);
        return ((first < second) ? -1 : ((first > second ) ? 1 : 0));
    }

    protected void print_log(String log) {
        if (dataSize >= 100) return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = dateFormat.format(System.currentTimeMillis());
        System.err.println(time + ": " + log);
    }

    private void generateScope() {
        for(int i=0; i<dataSize; i++) {
            data.add(rand.nextInt(scope + 1));
        }
    }

    private void generateRandomData() {
        for(int i=0; i<dataSize; i++) {
            data.add(rand.nextInt(dataSize * 10 + 1));
        }
    }

    private void generateDescData() {
        for(int i=dataSize; i>0; i--) {
            data.add(i);
        }
    }

    public void print() {
        for(Integer element : data) {
            System.out.print(element);
            if (data.indexOf(element) != data.size()-1)
                System.out.println("");
        }
    }

    public void printBinary(int bits) {
        for(Integer element : data) {
            System.out.print(String.format("%" + bits + "s", Integer.toBinaryString(element)).replace(' ', '0'));
            System.out.println("");
        }
    }


    public boolean isRandomDataType() {
        return randomDataType;
    }

    public void setRandomDataType(boolean randomDataType) {
        this.randomDataType = randomDataType;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    public int getCompares() {
        return compares;
    }

    public int getSwaps() {
        return swaps;
    }

    public void sort() {}

    public void sort(int start, int end) {}
}
