import java.util.List;

public class OutputHelper {
    public static void printOutput(List<int[]> zombies_positions, int[][] creatures_initial_positions, boolean[] isZombie) {
        System.out.println("zombies’ positions:");
        zombies_positions.forEach(a -> System.out.print("(" + a[0] + "," + a[1] + ")"));

        System.out.println();

        System.out.println("creatures’ positions:");

        for (int i = 0; i < creatures_initial_positions.length; i++) {
            if (!isZombie[i]) {
                System.out.print("(" + creatures_initial_positions[i][0] + "," + creatures_initial_positions[i][1] + ")");
            }
        }

    }
}
