package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{
    private ArrayList<Position> listOfWall;
    private Position StartPosition;
    private Position GoalPosition;
    private Random rnd = new Random();

    public MyMazeGenerator() {
    }

    @Override
    public Maze generate(int row, int col) {
        if (!this.is_valid_arg(row, col)) {
            int[][] empty_maze = new int[0][0];
            this.StartPosition = new Position(0, 0);
            this.GoalPosition = new Position(0, 0);
            return new Maze(empty_maze);
        } else {
            this.m_row=row;
            this.m_col=col;
            this.listOfWall=new ArrayList();
            int[][] maze_one=this.ones_matrix(row,col);
            this.m_maze= new Maze(maze_one);
            this.StartPosition=this.getStartPosition(row,col);
            this.m_maze.setStartPosition(this.StartPosition); //define the random start position
            this.m_maze.setVal(this.StartPosition.getRowIndex(), this.StartPosition.getColumnIndex(), 0); //0 in Start position
            this.add_wall_to_list(this.StartPosition);

            while(!this.listOfWall.isEmpty()){ //prim
                Position current_p=this.listOfWall.remove(this.get_position_from_list()); //1. remove random wall from list
                if(this.num_of_neighbors(current_p)==1){ //2.  neighbor and 1 cell in the maze
                    this.m_maze.setVal(current_p.getRowIndex(),current_p.getColumnIndex(),0); //2.1. //add to maze
                    this.GoalPosition=current_p; // this is the current goal
                    this.add_wall_to_list(current_p); //add the new neighbors

                }
            }
            this.m_maze.setGoalPosition(this.GoalPosition);
            return this.m_maze;

        }
    }


    public int num_of_neighbors(Position p){
        int row = p.getRowIndex();
        int col = p.getColumnIndex();
        int num = 0;
        if (this.is_valid_position(row, col - 1) && !this.is_wall(row, col - 1)) {
            ++num;
        }
        if (this.is_valid_position(row, col + 1) && !this.is_wall(row, col + 1)) {
            ++num;
        }
        if (this.is_valid_position(row - 1, col) && !this.is_wall(row - 1, col)) {
            ++num;
        }
        if (this.is_valid_position(row + 1, col) && !this.is_wall(row + 1, col)) {
            ++num;
        }
        return num;
    }

    public Position getStartPosition(int row,int col){
        Random rnd = new Random();
        int edge = rnd.nextInt(4);
        Position res = null;
        int Index;
        switch (edge) {
            case 0:
                Index =rnd.nextInt(col);
                res = new Position(0,Index);
            case 1:
                Index= rnd.nextInt(row);
                res = new Position(Index,col-1);
                break;
            case 2:
                Index = rnd.nextInt(col);
                res = new Position(row-1,Index);
                break;
            case 3:
                Index = rnd.nextInt(row);
                res = new Position(Index,0);
        }
        return res;
    }

    private int get_position_from_list() {
        Random rnd = new Random();
        return rnd.nextInt(this.listOfWall.size());
    }
    public void add_wall_to_list(Position p){
        int row = p.getRowIndex();
        int col = p.getColumnIndex();


        if (this.is_valid_position(row - 1, col) && this.is_wall(row - 1, col)) {
            this.listOfWall.add(new Position(row - 1, col));
        }

        if (this.is_valid_position(row + 1, col) && this.is_wall(row + 1, col)) {
            this.listOfWall.add(new Position(row + 1, col));
        }

        if (this.is_valid_position(row, col - 1) && this.is_wall(row, col - 1)) {
            this.listOfWall.add(new Position(row, col - 1));
        }

        if (this.is_valid_position(row, col + 1) && this.is_wall(row, col + 1)) {
            this.listOfWall.add(new Position(row, col + 1));
        }

    }

    public boolean is_wall(int row, int col){
        return this.m_maze.value_of_position(row,col)==1;
    }

    private boolean is_valid_arg(int row, int col) {
        if (row > 0 && col > 0) {
            return row != 1 || col != 1;
        } else {
            return false;
        }
    }

    public boolean is_valid_position(int row,int col){
        if(row<0){
            return false;
        }else if (row>this.m_row-1){
            return false;
        }
        else if(col<0){
            return false;
        }else{
            return col <=this.m_col-1;
        }
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
