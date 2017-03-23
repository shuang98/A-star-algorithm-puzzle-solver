package astar;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EightPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
    **********************************************************************/
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input/puzzle11.txt"));
        int N = in.nextInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.nextInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        System.out.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            System.out.println(ws);
        }
    }
}
