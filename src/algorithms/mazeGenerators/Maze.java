package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Maze implements Serializable{
    private Position StartPosition;
    private Position GoalPosition;
    private int[][] m_maze;

    public Maze(int[][] m_maze) {
        this.m_maze = m_maze;
    }


    public Maze(byte[] byte_array) {
        int row_size = this.extractVar(byte_array, 0, 2);
        int col_size = this.extractVar(byte_array, 2, 4);
        int start_row = this.extractVar(byte_array, 4, 6);
        int start_col = this.extractVar(byte_array, 6, 8);
        int goal_row = this.extractVar(byte_array, 8, 10);
        int goal_col = this.extractVar(byte_array, 10, 12);
        this.StartPosition = new Position(start_row, start_col);
        this.GoalPosition = new Position(goal_row, goal_col);
        this.m_maze = new int[row_size][col_size];
        int index = 12;

        for(int row = 0; row < row_size; ++row) {
            for(int col = 0; col < col_size; ++col) {
                this.m_maze[row][col] = byte_array[index];
                ++index;
            }
        }

    }
    public int getRowSize() {
        return this.m_maze.length;
    }

    public int getColSize() {
        return this.m_maze[0].length;
    }


    public Maze(int[][] myGrid , Position startPosition, Position goalPosition)  {
        StartPosition = startPosition;
        GoalPosition = goalPosition;
        m_maze = myGrid;
    }

    public void setVal(int row, int col, int val){
        if(row >=0 && row < m_maze.length && col >=0 && col < m_maze[0].length)
            this.m_maze[row][col] = val;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.m_maze.length; i++) {
            res = res + "{";
            for (int j = 0; j < this.m_maze[0].length; j++) {

                if (i == this.StartPosition.getRowIndex() && j == this.StartPosition.getColumnIndex()) {
                    res = res + " S";
                } else if (i == this.GoalPosition.getRowIndex() && j == this.GoalPosition.getColumnIndex()) {
                    res = res + " E";
                } else {
                    res = res +" " + this.m_maze[i][j];
                }
            }
            res = res + " }" +"\n";

        }
        return res.toString();
    }

    public void print() {
        System.out.print(this.toString());
    }

    public int value_of_position(int row,int col){
        if(row>=0 && row<this.m_maze.length && col>=0 && col < this.m_maze[0].length){
            return m_maze[row][col];
        }else{
            return -1;
        }

    }


    public Position[] getStartEndPosition(int row, int col) {
        Random rnd = new Random();
        int edge = rnd.nextInt(4);
        Position[] res = new Position[2];
        int Index;
        switch (edge) {
            case 0:
                Index = rnd.nextInt(col);
                res[0] = new Position(0, Index);
                Index = rnd.nextInt(col);
                res[1] = new Position(row - 1, Index);
                break;
            case 1:
                Index = rnd.nextInt(row);
                res[0] = new Position(Index, col - 1);
                Index = rnd.nextInt(row);
                res[1] = new Position(Index, 0);
                break;
            case 2:
                Index = rnd.nextInt(col);
                res[0] = new Position(row - 1, Index);
                Index = rnd.nextInt(col);
                res[1] = new Position(0, Index);
                break;
            case 3:
                Index = rnd.nextInt(row);
                res[0] = new Position(Index, 0);
                Index = rnd.nextInt(row);
                res[1] = new Position(Index, col - 1);
        }

        return res;

    }


    public Position getStartPosition() {
        return StartPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public int[][] getMap() {return this.m_maze;}

    public byte[] toByteArray() {   //[row,col,row_of_start,col_of_start,row_of_goal,col_of_goal,byte_of_maze]
        ArrayList<Byte> byteArray = new ArrayList();

        this.convertIntToByteArray(byteArray,this.m_maze.length);
        this.convertIntToByteArray(byteArray,this.m_maze[0].length);
        this.convertIntToByteArray(byteArray,this.StartPosition.getRowIndex());
        this.convertIntToByteArray(byteArray,this.StartPosition.getColumnIndex());
        this.convertIntToByteArray(byteArray,this.GoalPosition.getRowIndex());
        this.convertIntToByteArray(byteArray,this.GoalPosition.getColumnIndex());


        for(int row = 0; row < this.m_maze.length; ++row) {
            for(int col = 0; col < this.m_maze[0].length; ++col) {
                byteArray.add((byte)this.m_maze[row][col]);
            }
        }

        int i;
        byte[] byte_array = new byte[byteArray.size()];
         for(i = 0; i < byteArray.size(); ++i){
             byte_array[i] = (Byte)byteArray.get(i);
         }
        return byte_array;
    }

    public void convertIntToByteArray(ArrayList<Byte> byteArray,int num_int) {
        int counter;
        for(counter = 2; num_int > 0; num_int -= 255) {
            if(num_int - 255 > 0) {
                byteArray.add((byte) -1);
            } else {
                byteArray.add((byte)num_int);
            }
            --counter;
        }

        while(counter > 0) {
            byteArray.add((byte)0);
            --counter;
        }

    }

    private int extractVar(byte[] arr, int from, int end) {
        int value;
        for(value = 0; from != end; ++from) {
            value += arr[from] & 255;
        }

        return value;
    }
}


