/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aioffline2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Faiza
 */
public class Node1 implements Comparable<Node1> {

    static public int SIZE = MyClass.boardsize;
    static private double DEV = 0.001;
    // the state of node
    public int[] state;
    //distance from start
    public int level;
    // heuristic score
    public int heuristic;
    public Node1 parent;

    public Node1() {
        state = new int[SIZE];
        heuristic = 0;
        level = 0;
    }


    public Node1(int[] tiles) {
        state = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            state[i] = tiles[i];
        }
        heuristic = 0;
        level = 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Arrays.hashCode(this.state);
        hash = 79 * hash + this.heuristic;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node1 other = (Node1) obj;

        if (this.heuristic != other.heuristic) {
            return false;
        }
        if (!Arrays.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }

    public boolean isGoal() {
        int[] goal = new int[SIZE];
        for (int i = 0; i < SIZE - 1; i++) {
            goal[i] = i + 1;
        }
        goal[SIZE - 1] = 0;

        return (Arrays.equals(this.state, goal));
    }

    public static void setSIZE(int SIZE) {
        Node1.SIZE = SIZE;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int[] getState() {
        return state;
    }

    static public int getSize() {
        return SIZE;
    }

    public int getLevel() {
        return level;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public ArrayList<Node1> generateNeighbours() {

        ArrayList<Node1> neighbours = new ArrayList<>();
        int wide = (int) Math.sqrt(SIZE * 1.0);
        int blanki = 0;
        int blankj = 0;
        for (int i = state.length - 1; i >= 0; i--) {
            if (state[i] == 0) {
                blanki = i / wide;
                blankj = i % wide;
                break;
            }
        }

        if (blanki > 0) {
            // can move up
            int[] tiles = new int[SIZE];
            for (int i = state.length - 1; i >= 0; i--) {
                tiles[i] = state[i];
            }
            int temp = tiles[blanki * wide + blankj];
            tiles[blanki * wide + blankj] = tiles[(blanki - 1) * wide + blankj];
            tiles[(blanki - 1) * wide + blankj] = temp;
            Node1 node = new Node1(tiles);
            switch (Huristic.option) {
                case 1:
                    node.computeHeuristic1();
                    break;
                case 2:
                    node.computeHeuristic2();
                    break;
                default:
                    node.computeHeuristic3();
                    break;
            }
            node.setParent(this);
            neighbours.add(node);
        }

        if (blanki < (wide - 1)) {
            // can move down
            int[] tiles = new int[SIZE];
            for (int i = state.length - 1; i >= 0; i--) {
                tiles[i] = state[i];
            }
            int temp = tiles[blanki * wide + blankj];
            tiles[blanki * wide + blankj] = tiles[(blanki + 1) * wide + blankj];
            tiles[(blanki + 1) * wide + blankj] = temp;
            Node1 node = new Node1(tiles);
            switch (Huristic.option) {
                case 1:
                    node.computeHeuristic1();
                    break;
                case 2:
                    node.computeHeuristic2();
                    break;
                default:
                    node.computeHeuristic3();
                    break;
            }
            node.setParent(this);
            neighbours.add(node);
        }

        if (blankj > 0) {
            // can move left
            int[] tiles = new int[SIZE];
            for (int i = state.length - 1; i >= 0; i--) {
                tiles[i] = state[i];
            }
            int temp = tiles[blanki * wide + blankj];
            tiles[blanki * wide + blankj] = tiles[blanki * wide + blankj - 1];
            tiles[blanki * wide + blankj - 1] = temp;
            Node1 node = new Node1(tiles);
            switch (Huristic.option) {
                case 1:
                    node.computeHeuristic1();
                    break;
                case 2:
                    node.computeHeuristic2();
                    break;
                default:
                    node.computeHeuristic3();
                    break;
            }
            node.setParent(this);
            neighbours.add(node);
        }

        if (blankj < (wide - 1)) {
            // can move right
            int[] tiles = new int[SIZE];
            for (int i = state.length - 1; i >= 0; i--) {
                tiles[i] = state[i];
            }
            int temp = tiles[blanki * wide + blankj];
            tiles[blanki * wide + blankj] = tiles[blanki * wide + blankj + 1];
            tiles[blanki * wide + blankj + 1] = temp;
            Node1 node = new Node1(tiles);
            switch (Huristic.option) {
                case 1:
                    node.computeHeuristic1();
                    break;
                case 2:
                    node.computeHeuristic2();
                    break;
                default:
                    node.computeHeuristic3();
                    break;
            }
            node.setParent(this);
            neighbours.add(node);
        }

        return neighbours;
    }

    public Node1 getParent() {
        return parent;
    }

    public void setParent(Node1 parent) {
        this.parent = parent;
    }

    //Humming Heuristic
    public void computeHeuristic1() {

        int wide = (int) Math.sqrt(SIZE * 1.0);
        for (int i = state.length - 1; i >= 0; i--) {
            if (state[i] != 0) {
                if (state[i] != (i + 1)) {
                    heuristic++;
                }

            }
        }

    }

    //Manhatan Heuristic
    public void computeHeuristic2() {

        int wide = (int) Math.sqrt(SIZE * 1.0);
        for (int i = state.length - 1; i >= 0; i--) {
            if (state[i] != 0) {
                int x = calculateManhattanDistance(
                        (state[i] - 1) / wide, (state[i] - 1) % wide, i / wide, i % wide);
                // System.out.print(state[i] + ":" + x + "\t");
                heuristic += x;
            }
        }

    }

    //Linear Heuristic
    public void computeHeuristic3() {

        int wide = (int) Math.sqrt(SIZE * 1.0);

        for (int i = state.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (state[i] != 0 && state[j] != 0) {
                    heuristic += 2 * (calculateLinearConflict(
                            i / wide, i % wide,
                            j / wide, j % wide,
                            (state[i] - 1) / wide, (state[i] - 1) % wide,
                            (state[j] - 1) / wide, (state[j] - 1) % wide) ? 1 : 0);
                }
            }
        }

        //System.out.println("heuristic : " + heuristic + " me :" + LinearHeuristic3());
        for (int i = state.length - 1; i >= 0; i--) {
            if (state[i] != 0) {
                int x = calculateManhattanDistance(
                        (state[i] - 1) / wide, (state[i] - 1) % wide, i / wide, i % wide);
                heuristic += x;
            }
        }
    }

    public int LinearHeuristic3() {
        int wide = (int) Math.sqrt(SIZE * 1.0);
        int count = 0;
        for (int i = state.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (state[i] != 0 && state[j] != 0) {

                    int ai =(int) i / wide, aj = i % wide, bi =(int) j / wide, bj = j % wide;
                    int aGoali = (int)(state[i] - 1) / wide, aGoalj = (state[i] - 1) % wide, bGoali =(int) (state[j] - 1) / wide, bGoalj = (state[j] - 1) % wide;
                    //horizontal
                    if (ai == bi && aGoali == bGoali && aj > bj && aGoalj < bGoalj) {
                        //heuristic+=2;
                        count += 2;
                    }
                    //vertical
                    if (aj == bj && aGoalj == bGoalj && ai > bi && aGoali < bGoali) {
                        //heuristic+=2;
                        count += 2;
                    }

                }
            }
        }

        return count;
    }


    private int calculateManhattanDistance(int ai, int aj, int bi, int bj) {
        return Math.abs(ai - bi) + Math.abs(aj - bj);
    }

    private boolean calculateLinearConflict(int ai, int aj, int bi, int bj,
            int aGoalI, int aGoalJ, int bGoalI, int bGoalJ) {
        double k, b;
        // first, judge whether the 4 points are in one line
        // second, judege whether the vector(ai - bi, aj - bj)
        // and the vector(aGoalI - bGoalI, aGoalJ - bGoalJ) is in contrast
        // i = k * j + b
        if (aj != bj) {
            k = (1.0 * ai - bi) / (aj - bj);
            b = ai - aj * k;
            if (isTheSame(aGoalI - aGoalJ * k, b) && isTheSame(bGoalI - bGoalJ * k, b)) {
                // yes, the 4 points are in one line
                double v1i = ai - bi, v1j = aj - bj;
                double v2i = aGoalI - bGoalI, v2j = aGoalJ - bGoalJ;
                return isTheSame(
                        (v1i * v2i + v1j * v2j)
                        / Math.sqrt((v1i * v1i + v1j * v1j) * (v2i * v2i + v2j * v2j)), -1);

            }
        }
        else {
            if (aGoalJ == aj && bGoalJ == bj) {
                // yes, the 4 points are in one line
                double v1i = ai - bi, v1j = aj - bj;
                double v2i = aGoalI - bGoalI, v2j = aGoalJ - bGoalJ;
                return isTheSame(
                        (v1i * v2i + v1j * v2j)
                        / Math.sqrt((v1i * v1i + v1j * v1j) * (v2i * v2i + v2j * v2j)), -1);
            }
        }
        return false;
    }

    private boolean isTheSame(double a, double b) {
        return Math.abs(a - b) <= DEV;
    }

    @Override
    public int compareTo(Node1 o) {
        if ((level + this.heuristic) < (o.level + o.getHeuristic())) {
            return -1;
        } else if ((level + this.heuristic) > (o.level + o.getHeuristic())) {
            return 1;
        } else {
            return 0;
        }

    }

}
//11 5 2 3 1 0 10 4 6 13 8 12 14 9 15 7
//7 2 4 6 3 1 8 0 5