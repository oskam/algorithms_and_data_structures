/**
 * Created by Magdalena on 2017-04-03.
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public abstract class SelectSuperclass {

    /**
     * Decides what type of data to generate true == random, false == unique
     */
    protected boolean randomDataType = true;

    /**
     * Size of data to generate
     */
    protected int dataSize;

    /**
     * k-th position to find
     */
    protected int k;

    /**
     * ArrayList where elements are generated to
     */
    protected ArrayList<Integer> data;

    /**
     * Amount of compares done during sorting,
     */
    protected int compares;

    protected Random rand;

    public SelectSuperclass( boolean type, int size, int k ) {
        setDataSize(size);
        setK(k);
        setRandomDataType(type);
        compares = 0;
        data = new ArrayList<>();
        rand = new Random();

        generateData();

        if (dataSize < 100) {
            for (int value : data) {
                print_log(value + "");
            }
        }
    }

    protected int select(int p, int r, int i) {
        return 0;
    }

    private void generateData() {
        if (randomDataType) {
            generateRandomData();
            print_log("generating random data of size " + dataSize);
        } else {
            generateUniqueData();
            print_log("generating unique data of size " + dataSize);
        }
    }

    private void generateRandomData() {
        for (int i = 0; i < dataSize; i++) {
            data.add(rand.nextInt(dataSize * 10 + 1));
        }
    }

    private void generateUniqueData() {
        for (int i = 1; i <= dataSize; i++) {
            data.add(i);
        }
        /*
         * for(int i=0; i<dataSize; i++) { swap(i, rand.nextInt(dataSize)); }
         */
    }

    protected void swap(int i, int j) {
        data.set(i, data.set(j, data.get(i)));
    }

    protected void swapLog(int i, int j) {
        print_log("swapping");
        data.set(i, data.set(j, data.get(i)));
    }

    protected void print() {
        int index = 1;
        for (Integer element : data) {
            System.out.print(element);
            if (index++ == k)
                System.out.print("\t<---");
            if (data.indexOf(element) != data.size() - 1)
                System.out.println("");
        }
    }

    protected void quickSort(int start, int end) {
        if (start < end) {
            int pivot = qsPartition(start, end);

            quickSort(start, pivot - 1);
            quickSort(pivot + 1, end);
        }
    }

    protected int qsPartition(int start, int end) {
        int pivot = qsSelectPivot(start, end);
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

    protected int qsSelectPivot(int start, int end) {
        if ((end - start) >= 3) {
            int a, b, c;
            int aV, bV, cV;
            a = start + rand.nextInt((end - start) + 1);
            aV = data.get(a);
            b = start + rand.nextInt((end - start) + 1);
            bV = data.get(b);
            c = start + rand.nextInt((end - start) + 1);
            cV = data.get(c);

            if (aV < bV)
                if (bV < cV)
                    return b;
                else if (aV < cV)
                    return c;
                else
                    return a;
            else if (aV < cV)
                return a;
            else if (bV < cV)
                return c;
            else
                return b;
        } else {
            return start;
        }
    }

    protected int compare(Integer first, Integer second) {
        compares++;
        return ((first < second) ? -1 : ((first > second) ? 1 : 0));
    }

    protected int compareLog(Integer first, Integer second) {
        compares++;
        print_log("comparing " + first + " and " + second);
        return ((first < second) ? -1 : ((first > second) ? 1 : 0));
    }

    protected void print_log(String log) {
        if (dataSize >= 100)
            return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = dateFormat.format(System.currentTimeMillis());
        System.err.println(time + ": " + log);
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

    public void setK(int k) {
        this.k = k;
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
}

