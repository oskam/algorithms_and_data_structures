import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class FindLCS {

    public int complexity = 0;

    public int[][] lcsLength(char[] x, char[] y) {
        int[][] c = new int[x.length + 1][y.length + 1];

        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
                complexity++;
            }
        }
        return c;
    }

    public String backtrack(int[][] c, char[] x, char[] y, int i, int j) {
        if (i == 0 || j == 0) {
            return "";
        } else if (x[i - 1] == y[j - 1]) {
//            complexity++;
            return (backtrack(c, x, y, i - 1, j - 1) + x[i - 1]);
        } else {
//            complexity++;
            if (c[i][j - 1] > c[i - 1][j]) {
                return backtrack(c, x, y, i, j - 1);
            } else {
                return backtrack(c, x, y, i - 1, j);
            }
        }
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

                FindLCS lcs = new FindLCS();
                int c[][] = lcs.lcsLength(x, y);
                lcs.backtrack(c, x, y, x.length, y.length);

                complexity += lcs.complexity;
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

    public static void runWithArguments(String[] args) {
        FindLCS lcs = new FindLCS();

        int i = args[0].length();
        int j = args[1].length();

        char[] x = args[0].toCharArray();
        char[] y = args[1].toCharArray();

        int[][] c = lcs.lcsLength(x, y);

        String lcsString = lcs.backtrack(c, x, y, i, j);

        System.out.println(lcsString);
    }

    public static void main(String[] args) throws IOException {
        test(2, 2048);
        test(3, 3*1024);
        //runWithArguments(args);
    }
}
