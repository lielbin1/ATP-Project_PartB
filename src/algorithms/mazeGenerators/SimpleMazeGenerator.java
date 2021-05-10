package algorithms.mazeGenerators;

import java.util.Random;
import java.util.Arrays;

public class SimpleMazeGenerator extends AMazeGenerator {
    private Position StartPosition;
    private Position GoalPosition;
    private int m_row;
    private int m_col;

    public SimpleMazeGenerator() {
    }

    @Override
    public Maze generate(int row, int col) {
        this.m_row = row;
        this.m_col = col;
        int[][] simple_maze = this.ones_matrix(row, col);
        this.m_maze = new Maze(simple_maze);
        Random ran = new Random();

        for (int rows = 0; rows < row; rows++) {
            for (int Cols = 0; Cols < col; Cols++) {
                this.m_maze.setVal(rows, Cols, ran.nextInt(2));
            }
        }


        Position[] p;
        p = this.m_maze.getStartEndPosition(row, col); //two random position for the start end goal
        this.StartPosition = p[0];
        this.GoalPosition = p[1];
        this.m_maze.setStartPosition(this.StartPosition); //define the random start position
        this.m_maze.setGoalPosition(this.GoalPosition); //define the random Goal position
        this.m_maze.setVal(this.StartPosition.getRowIndex(), this.StartPosition.getColumnIndex(), 0); //0 in Start position
        this.m_maze.setVal(this.GoalPosition.getRowIndex(), this.GoalPosition.getColumnIndex(), 0); //0 in Goal position
        int Start_row = StartPosition.getRowIndex();
        int Start_col = StartPosition.getColumnIndex();
        int Goal_row = GoalPosition.getRowIndex();
        int Goal_col = GoalPosition.getColumnIndex();
        //the path in the maze
        while (Start_row != Goal_row || Start_col != Goal_col) {//define the random start position)
        if(Start_row != Goal_row) {
            int move_row = Start_row - Goal_row;
            if (move_row > 0) {
                --Start_row;
                this.m_maze.setVal(Start_row, Start_col, 0);
            } else if (move_row != 0) {
                ++Start_row;
                this.m_maze.setVal(Start_row, Start_col, 0);
            }
        }
        if(Start_col != Goal_col) {
            int move_col = Start_col - Goal_col;
            if (move_col > 0) {
                --Start_col;
                this.m_maze.setVal(Start_row, Start_col, 0);
            } else if (move_col != 0) {
                ++Start_col;
                this.m_maze.setVal(Start_row, Start_col, 0);
            }
        }

        }

        return m_maze;
    }


    public int[][] ones_matrix(int row, int col) {
        int[][] matrix = new int[row][col];
        for(int i=0; i<row; i++){
            for (int j=0; j<col;j++){
                matrix[i][j] = 1;
            }
        }
        return matrix;
    }


}
