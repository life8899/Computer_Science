import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Runs each Replacement Algorithm with 25, 50, 75, and 100 Frames on each Trace File for Plotting
 */
public class CollectData
{
    public static void main(String[] args)
    {
        System.out.println("--------------------------------");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: args[0] = "../data/swim.trace"; break;
                case 1: args[0] = "../data/bzip.trace"; break;
                case 2: args[0] = "../data/gcc.trace"; break;
                case 3: args[0] = "../data/sixpack.trace"; break;
            }
            System.out.println(args[0]);
            System.out.println("--------------------------------");
            for (int j = 1; j < 5; j++) {
                System.out.println("\n----------------");
                args[1] = String.valueOf(25 * j);
                MemSim.main(args);
                HashedMemSim.main(args);
                System.out.println("----------------\n");
            }
        }
        String duration = "Execution Time: " + new SimpleDateFormat("mm:ss:SS").format(new Date(System.currentTimeMillis() - startTime));
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                System.out.println("|" + duration + "|");
            } else {
                for (int j = 0; j < duration.length() + 2; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
        System.out.println("\n--------------------------------");
    }
}