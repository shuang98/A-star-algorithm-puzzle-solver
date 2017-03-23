package hw3.puzzle;

public class WordPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality. Make sure to set your current working directory
     * to be the one containing words10000.txt.
     **********************************************************************/
    public static void main(String[] args) {
        String start = "horse";
        String goal = "nurse";

        Word startState = new Word(start, goal);
        Solver solver = new Solver(startState);

        System.out.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            System.out.println(ws);
        }
    }
}
