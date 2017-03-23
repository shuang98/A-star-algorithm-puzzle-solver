package astar;


import java.util.LinkedList;

public class Board implements WorldState {

    int[][] tiles;
    int blankRow;
    int blankCol;

    public Board(int[][] tiles) {
        this.tiles = copyTiles(tiles);
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    blankCol = j;
                    blankRow = i;
                }
            }
        }
    }

    private int[][] copyTiles(int[][] array) {
        int[][] t = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                t[i][j] = array[i][j];
            }
        }
        return t;
    }

    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }


    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public int hamming() {
        int count = 0;
        for (int i = 1; i < tiles.length * tiles.length; i++) {
            if (tiles[(i - 1) / tiles.length][(i - 1) % tiles.length] != i) {
                count++;
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 1; i <= tiles.length * tiles.length; i++) {
            if (tiles[(i - 1) / tiles.length][(i - 1) % tiles.length] != i) {
                count += distance((i - 1) / tiles.length, (i - 1) % tiles.length);
            }
        }
        return count;
    }

    public int distance(int i, int j) {
        int num = tiles[i][j];
        if (num == 0) {
            return 0;
        }
        int goalRow = (num - 1) / tiles.length;
        int goalCol = (num - 1) % tiles.length;
        return Math.abs(goalCol - j) + Math.abs(goalRow - i);
    }

    @Override
    public Iterable<WorldState> neighbors() {
        LinkedList<WorldState> neighbors = new LinkedList<>();
        if (blankRow - 1 >= 0) {
            int[][] t = copyTiles(tiles);
            t[blankRow][blankCol] = tiles[blankRow - 1][blankCol];
            t[blankRow - 1][blankCol] = 0;
            neighbors.add(new Board(t));
        }
        if (blankCol - 1 >= 0) {
            int[][] t = copyTiles(tiles);
            t[blankRow][blankCol] = tiles[blankRow][blankCol - 1];
            t[blankRow][blankCol - 1] = 0;
            neighbors.add(new Board(t));
        }
        if (blankRow + 1 < tiles.length) {
            int[][] t = copyTiles(tiles);
            t[blankRow][blankCol] = tiles[blankRow + 1][blankCol];
            t[blankRow + 1][blankCol] = 0;
            neighbors.add(new Board(t));
        }
        if (blankCol + 1 < tiles.length) {
            int[][] t = copyTiles(tiles);
            t[blankRow][blankCol] = tiles[blankRow][blankCol + 1];
            t[blankRow][blankCol + 1] = 0;
            neighbors.add(new Board(t));
        }
        return neighbors;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Board)) {
            return false;
        }
        if (((Board) o).size() != size()) {
            return false;
        }
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != ((Board) o).tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
