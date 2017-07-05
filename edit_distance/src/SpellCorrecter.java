/**
 * Created by oskam on 04.05.2017.
 */
import java.io.*;
import java.util.*;


public class SpellCorrecter {

    private static ArrayList<String> dictionary;

    private static void readFile(String filename) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;


        dictionary = new ArrayList<>();
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            dictionary.add(word);
        }

        Collections.shuffle(dictionary, new Random());
    }

    private static String[] getSuggestions(String word){

        int n = dictionary.size();
        TreeMap distances = new TreeMap();

        String[] suggestedWords = new String[3];
        //int[] distances = new int[n];

        EditDistance ed = new EditDistance();

        dictionary.stream().filter(dictWord -> !word.equals(dictWord)).forEach(dictWord -> {
            distances.put(ed.getDistance(word, dictWord), dictWord);
        });

        suggestedWords[0] = (String) distances.remove(distances.firstKey());
        suggestedWords[1] = (String) distances.remove(distances.firstKey());
        suggestedWords[2] = (String) distances.remove(distances.firstKey());

        return suggestedWords;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        readFile("C:\\Users\\Magdalena\\IdeaProjects\\AiSD_edit_dist\\src\\dict");
            readFile("/Users/oskam/IdeaProjects/AiSD_edit_dist/src/dict");
        while (true) {
            String word = scanner.nextLine();

            if (word.equals("!exit")) {
                break;
            }
            long ts = System.currentTimeMillis();
            String[] suggestions = getSuggestions(word);

            for (String suggestion : suggestions) {
                System.out.println(suggestion);
            }
            System.out.printf("Found in: %dms\n", System.currentTimeMillis()-ts);
        }
    }
}
