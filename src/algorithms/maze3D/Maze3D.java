package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Random;

public class Maze3D {
    private Position3D StartPosition;
    private Position3D GoalPosition;
    public int[][][] m_maze;


    public Maze3D(int[] intMaze) {

    }
    public Maze3D(int[][][] m_maze) {
        this.m_maze = m_maze;
    }



    public void setVal(int depth, int row, int column, int val){
        if(row>=0 && column>=0 && depth>=0  && depth<this.m_maze.length && row < this.m_maze[0].length && column < this.m_maze[0][0].length)
            this.m_maze[depth][row][column] = val;
    }

    public void setStartPosition(Position3D startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position3D goalPosition) {
        GoalPosition = goalPosition;
    }

    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < m_maze.length; depth++){
            for(int row = 0; row < m_maze[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < m_maze[0][0].length; col++) {
                    if (depth == StartPosition.getDepthIndex() && row == StartPosition.getRowIndex() && col == StartPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == GoalPosition.getDepthIndex() && row == GoalPosition.getRowIndex() && col == GoalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(m_maze[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < m_maze.length - 1) {
                System.out.print("---");
                for (int i = 0; i < m_maze[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    public int value_of_position3D(int depth, int row,int column){
        if(row>=0 && column>=0 && depth>=0  && depth<this.m_maze.length && row < this.m_maze[0].length && column < this.m_maze[0][0].length){
            return m_maze[depth][row][column];
        }else{
            return -1;
        }

    }

    public Position3D getStartPosition() {return StartPosition;}

    public Position3D getGoalPosition() {return GoalPosition;}

    public int getRowSize() {return this.m_maze.length;}

    public int getColSize() {return this.m_maze[0].length;}

    public int[][][] getMap() {return this.m_maze;}
}


