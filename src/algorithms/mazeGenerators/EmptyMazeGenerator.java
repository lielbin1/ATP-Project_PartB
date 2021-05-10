package algorithms.mazeGenerators;

import java.util.List;

public class EmptyMazeGenerator extends AMazeGenerator {

    public EmptyMazeGenerator() {
    }

    @Override
    public Maze generate(int row, int col) {
         int[][] empty_maze = new int[row][col];
//        int[][] empty_maze = new int[row][col];
//
//        for(int m_row = 0; m_row < row; ++m_row) {
//            for(int m_col = 0; m_col < col; ++m_col) {
//                empty_maze[m_row][m_col] = 0;
//            }
//        }
//
//        this.m_maze = new Maze(empty_maze);
//        return this.m_maze;
//        return maze;
        for(int rows=0 ;rows<row; rows++){
            for(int cols=0 ; cols<col ; cols++){
                empty_maze[rows][cols] = 0;
            }

        }
        this.m_maze = new Maze(empty_maze);
        Position[] p;
        p = this.m_maze.getStartEndPosition(row, col); //two random position for the start end goal
        this.m_maze.setStartPosition(p[0]); //define the random start position
        this.m_maze.setGoalPosition(p[1]); //define the random Goal position
        this.m_maze.setVal(p[0].getRowIndex(), p[0].getColumnIndex(), 0); //0 in Start position
        this.m_maze.setVal(p[1].getRowIndex(), p[1].getColumnIndex(), 0); //0 in Goal position
        return this.m_maze;
    }
}
