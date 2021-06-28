import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Zombie {
    int worldDimension;
    int[] zombie_init_position;
    int[][] creatures_init_positions;
    boolean[] isZombie;
    String move_string;
    List<int[]> zombies_positions;
    Queue<int[]> queue;

    public Zombie(String fileName) throws IOException {
        zombie_init_position = new int[2];
        zombies_positions = new ArrayList<>();
        queue = new LinkedList<>();

        String[] input = InputHelper.readFile(fileName);

        getWorldDimensions(input[0]);
        getZombieInitialPosition(input[1]);
        getCreaturesInitialPosition(input[2]);
        getListOfMoves(input[3]);
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        Zombie instance = new Zombie(fileName);
        instance.zombieMovies();
    }

    public int[] transformMove(char move) {
        int[] moves = new int[2];

        switch (move) {
            case 'U':
                moves[1] = -1;
                break;
            case 'D':
                moves[1] = 1;
                break;
            case 'L':
                moves[0] = -1;
                break;
            case 'R':
                moves[0] = 1;
                break;
            default:
        }

        return moves;
    }


    public void zombieMovies() {
        int count = 0;

        move(worldDimension, count);

        OutputHelper.printOutput(zombies_positions, creatures_init_positions, isZombie);

    }

//    private void Zombie(String fileName) throws IOException {
//        zombie_init_position = new int[2];
//        zombies_positions = new ArrayList<>();
//        queue = new LinkedList<>();
//
//        String[] input = InputHelper.readFile(fileName);
//
//        getWorldDimensions(input[0]);
//        getZombieInitialPosition(input[1]);
//        getCreaturesInitialPosition(input[2]);
//        getListOfMoves(input[3]);
//    }

    private void getWorldDimensions(String input) {
        worldDimension = Integer.parseInt(input);

        if (worldDimension <= 0) {
            throw new IllegalArgumentException("Illegal World Dimension； " + worldDimension);
        }
    }

    private void getZombieInitialPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Illegal Zombie Initial Position； " + position);
        }

        String[] zombieInitialPosition = position.replaceAll("[()]", "").split(",");

        zombie_init_position[0] = Integer.parseInt(zombieInitialPosition[0]);
        zombie_init_position[1] = Integer.parseInt(zombieInitialPosition[1]);
    }

    private void getListOfMoves(String moves) {
        if (moves == null || moves.isBlank()) {
            throw new IllegalArgumentException("Illegal List Of Moves； " + moves);
        }

        move_string = moves;
    }

    private void getCreaturesInitialPosition(String s) {
        String[] creaturesInitialPositions = s.split("[)]");
        creatures_init_positions = new int[creaturesInitialPositions.length][2];
        isZombie = new boolean[creaturesInitialPositions.length];

        for (int i = 0; i < creaturesInitialPositions.length; i++) {
            String[] position = creaturesInitialPositions[i].replace("(", "").split(",");

            creatures_init_positions[i][0] = Integer.parseInt(position[0]);
            creatures_init_positions[i][1] = Integer.parseInt(position[1]);

        }
    }

    private void move(int dimension, int count) {
        queue.offer(new int[]{zombie_init_position[0], zombie_init_position[1]});

        while (!queue.isEmpty()) {
            if (count > creatures_init_positions.length) {
                return;
            }

            int queueSize = queue.size();

            for (int k = 0; k < queueSize; k++) {
                int[] zombie = queue.poll();

                for (int i = 0; i < move_string.length(); i++) {
                    int[] moves_i = transformMove(move_string.charAt(i));

                    assert zombie != null;

                    zombie[0] += moves_i[0];
                    zombie[1] += moves_i[1];

                    if (zombie[0] >= dimension) {
                        zombie[0] -= dimension;
                    } else if (zombie[0] < 0) {
                        zombie[0] += dimension;
                    }
                    if (zombie[1] >= dimension) {
                        zombie[1] -= dimension;
                    } else if (zombie[1] < 0) {
                        zombie[1] += dimension;
                    }

                    for (int j = 0; j < creatures_init_positions.length; j++) {
                        if (zombie[0] == creatures_init_positions[j][0] && zombie[1] == creatures_init_positions[j][1] && !isZombie[j]) {
                            queue.offer(creatures_init_positions[j]);
                            isZombie[j] = true;
                            count++;
                        }
                    }

                }

                zombies_positions.add(new int[]{zombie[0], zombie[1]});
            }
        }

    }
}