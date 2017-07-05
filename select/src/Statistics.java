/**
 * Created by Magdalena on 2017-04-03.
 */
public class Statistics {

    public static void main(String[] args) {
        if (args.length !=3) {
            System.out.println("Wrong arguments, give: <type> <size> <k>");
        }
        boolean type = true;
        int size = 0;
        int k = 0;

        try {
            if(Integer.parseInt(args[0]) <= 0)
                type = false;
            else
                type = true;

            size = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.out.println("Wrong arguments.");
        }

        RandomizedSelect rs = null;
        Select s = null;

        int rsCompares = 0;
        int rsMax = 0;
        int rsMin = 0;

        int sCompares = 0;
        int sMax = 0;
        int sMin = 0;

        int trials = 1000;

        for (int i=0; i<trials; i++) {
            rs = new RandomizedSelect(type, size, k);
            rs.select(0, size-1, k);
            rsCompares += rs.compares;
            if (rs.compares > rsMax || i == 0)
                rsMax = rs.compares;
            if (rs.compares < rsMin || i == 0)
                rsMin = rs.compares;

            s = new Select(type, size, k);
            s.select(0, size-1, k);
            sCompares += s.compares;
            if (s.compares > sMax || i == 0)
                sMax = s.compares;
            if (s.compares < sMin || i == 0)
                sMin = s.compares;
        }

        System.out.println("RandomizedSelect: \tMIN: " + rsMin + "\tMAX: " + rsMax + "\tAVG: " + ((double) rsCompares/trials));
        System.out.println("DeterministicSelect: \tMIN: " + sMin + "\tMAX: " + sMax + "\tAVG: " + ((double) sCompares/trials));

    }

}

