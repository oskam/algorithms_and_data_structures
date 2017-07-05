import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Magdalena on 2017-05-02.
 */
public class EditDistance {

    public int complexity = 0;

    public int getDistance(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] e = new int[len1+1][len2+1];

        for (int i=0; i<=len1; i++){
            e[i][0] = i;
        }

        for (int j=0; j<=len2; j++){
            e[0][j] = j;
        }

        for (int i=0; i<len1; i++){
            for(int j=0; j<len2; j++){
                complexity++;
                e[i+1][j+1] = (int) min(e[i][j+1]+1, e[i+1][j]+1, e[i][j]+diff(word1.charAt(i),word2.charAt(j)));
                //System.out.println(e[i][j]);
            }
        }

        return e[len1][len2];
    }

    private int diff(char x, char y){
        if (x==y){
            return 0;
        } else {
            return 1;
        }
    }

    private double min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static void test(int sx, int sy) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("out.txt"));
        Random random = new Random();

        int trials = 100;
        int complexity;

        int sizex = sx;
        int sizey = sy;

        writer.println("         x          y        x+y        x*y          c");
        System.out.println("         x          y        x+y        x*y          c");

        while (sizex <= sy && sizey >= sx) {
            complexity = 0;

            for (int j = 0; j < trials; j++) {
                char[] x = new char[sizex];
                char[] y = new char[sizey];

                for (int idx = 0; idx < sizex; idx++) {
                    x[idx] = (char) (random.nextInt(26) + 65);
                }

                for (int idy = 0; idy < sizey; idy++) {
                    y[idy] = (char) (random.nextInt(26) + 65);
                }

                EditDistance ed = new EditDistance();
                int distance = ed.getDistance(String.valueOf(x), String.valueOf(y));

                complexity += ed.complexity;
            }

            complexity /= trials;

//            String out = sizex + "\t" + sizey + "\t" + (sizex + sizey) + "\t" + (sizex * sizey) + "\t" + complexity;

            String out = String.format("%10d %10d %10d %10d %10d", sizex, sizey, sizex+sizey, sizex*sizey, complexity);

            writer.println(out);
            System.out.println(out);

            sizex *= 2;
            sizey /= 2;
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        test(2, 2048);
        test(3, 3*1024);
//        String word1 = args[0];
//        String word2 = args[1];
//
//        EditDistance ed = new EditDistance();
//        System.out.println(ed.getDistance(word1, word2));
    }
}
