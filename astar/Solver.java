package astar;


import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by stanley on 3/21/17.
 */
public class Solver {
    MinPQ<SearchNode> queue;
    LinkedList<WorldState> solution;
    public Solver(WorldState initial) {
        queue = new MinPQ<>(new SearchNodeComparator());
        queue.insert(new SearchNode(initial, 0, null));
        solution = getSolution();
    }

    private LinkedList<WorldState> getSolution() {
        SearchNode min = queue.delMin();
        while (!min.state.isGoal()) {
            for (WorldState n : min.state.neighbors()) {
                if (min.previous == null || !n.equals(min.previous.state)) {
                    queue.insert(new SearchNode(n, min.moves + 1, min));
                }
            }
            min = queue.delMin();
        }
        LinkedList<WorldState> s = new LinkedList<>();
        while (min != null) {
            s.addFirst(min.state);
            min = min.previous;
        }
        return s;
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

    private class SearchNode {
        WorldState state;
        SearchNode previous;
        int moves;
        int estimate;
        SearchNode(WorldState state, int moves, SearchNode previous) {
            this.state = state;
            this.previous = previous;
            this.moves = moves;
            estimate = state.estimatedDistanceToGoal();
        }

        int getPriority() {
            return moves + estimate;
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            if (o2 == null && o1 != null) {
                return 1;
            } else if (o1 == null && o2 != null) {
                return -1;
            } else if (o1 == null && o2 == null) {
                return 0;
            }
            return o1.getPriority() - o2.getPriority();
        }
    }
}
