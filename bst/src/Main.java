/**
 * Created by Magdalena on 2017-04-09.
 */
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();

        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));

        int n = Integer.parseInt(reader.readLine());
        ArrayList<String[]> data = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            data.add(line.split(" "));
        }

        reader.close();

        if (data.size() != n) {
            System.out.println("Błąd odczytu danych");
            return;
        }

        for(String[] operation : data) {
            switch (operation[0]) {
                case "insert":
                    t.insert(Integer.parseInt(operation[1]));
                    break;
                case "delete":
                    t.delete(Integer.parseInt(operation[1]));
                    break;
                case "find":
                    t.find(Integer.parseInt(operation[1]));
                    break;
                case "min":
                    t.min();
                    break;
                case "max":
                    t.max();
                    break;
                case "inorder":
                    t.inorder();
                    break;
                case "postorder":
                    t.postorder();
                    break;
            }
        }

    }
}

