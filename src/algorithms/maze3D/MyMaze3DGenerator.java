package algorithms.maze3D;


import java.util.ArrayList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    private ArrayList<Position3D> listOfWall;
    private Position3D StartPosition;
    private Position3D GoalPosition;
    private Random rnd = new Random();

    public Maze3D generate(int depth, int row, int column) {
        if (!this.is_valid_arg(depth,row, column)) {
            int[][][] empty_maze = new int[0][0][0];
            this.StartPosition = new Position3D(0, 0,0);
            this.GoalPosition = new Position3D(0, 0,0);
            return new Maze3D(empty_maze);
        } else {
            this.m_row=row;
            this.m_col=column;
            this.m_depth=depth;
            this.listOfWall=new ArrayList();
            int[][][] maze_one=this.ones_matrix(depth,row,column);
            this.m_maze= new Maze3D(maze_one);
            this.StartPosition=this.getStartPosition(depth,row,column);
            this.m_maze.setStartPosition(this.StartPosition); //define the random start position
            this.m_maze.setVal(this.StartPosition.getDepthIndex(), this.StartPosition.getRowIndex(), this.StartPosition.getColumnIndex(), 0); //0 in Start position
            this.add_wall_to_list(this.StartPosition);

            while(!this.listOfWall.isEmpty()){ //prim
                Position3D current_p=this.listOfWall.remove(this.get_position_from_list()); //1. remove random wall from list
                if(this.num_of_neighbors(current_p)==1){ //2.  neighbor and 1 cell in the maze
                    this.m_maze.setVal(current_p.getDepthIndex(),current_p.getRowIndex(),current_p.getColumnIndex(),0); //2.1. //add to maze
                    this.GoalPosition=current_p; // this is the current goal
                    this.add_wall_to_list(current_p); //add the new neighbors

                }
            }
            this.m_maze.setGoalPosition(this.GoalPosition);
            return this.m_maze;

        }
    }
    
    public int num_of_neighbors(Position3D p){
        int row = p.getRowIndex();
        int column = p.getColumnIndex();
        int depth = p.getDepthIndex();

        int num = 0;
        if (this.is_valid_position(depth-1,row, column) && !this.is_wall(depth-1,row, column )) {
            ++num;
        }
        if (this.is_valid_position(depth+1,row, column) && !this.is_wall(depth+1,row, column )) {
            ++num;
        }
        if (this.is_valid_position(depth,row, column + 1) && !this.is_wall(depth,row, column + 1)) {
            ++num;
        }
        if (this.is_valid_position(depth,row, column - 1) && !this.is_wall(depth,row, column - 1)) {
            ++num;
        }
        if (this.is_valid_position(depth,row - 1, column) && !this.is_wall(depth,row - 1, column)) {
            ++num;
        }
        if (this.is_valid_position(depth,row + 1, column) && !this.is_wall(depth,row + 1, column)) {
            ++num;
        }
        return num;
    }

    public Position3D getStartPosition(int depth,int row,int column){
        Random rnd = new Random();
        int edge = rnd.nextInt(6);
        Position3D res = null;
        int Index1,Index2;
        switch (edge) {
            case 0:
                Index1 =rnd.nextInt(column);
                Index2 =rnd.nextInt(row);
                res = new Position3D(0,Index1,Index2);
            case 1:
                Index1 =rnd.nextInt(column);
                Index2 =rnd.nextInt(row);
                res = new Position3D(depth-1,Index1,Index2);
                break;
            case 2:
                Index1 =rnd.nextInt(depth);
                Index2 =rnd.nextInt(row);
                res = new Position3D(Index1,Index2,0);
                break;
            case 3:
                Index1 =rnd.nextInt(depth);
                Index2 =rnd.nextInt(row);
                res = new Position3D(Index1,Index2, column -1);
            case 4:
                Index1 =rnd.nextInt(depth);
                Index2 =rnd.nextInt(column);
                res = new Position3D(Index1,0,Index2);
            case 5:
                Index1 =rnd.nextInt(depth);
                Index2 =rnd.nextInt(column);
                res = new Position3D(Index1,row-1,Index2);
        }
        return res;
    }

    private int get_position_from_list() {
        Random rnd = new Random();
        return rnd.nextInt(this.listOfWall.size());
    }
    public void add_wall_to_list(Position3D p){
        int row = p.getRowIndex();
        int column = p.getColumnIndex();
        int depth = p.getDepthIndex();


        if (this.is_valid_position(depth -1,row , column) && this.is_wall(depth-1,row , column)) {
            this.listOfWall.add(new Position3D(depth -1,row , column));
        }
        if (this.is_valid_position(depth+1,row ,column) && this.is_wall(depth +1,row, column)) {
            this.listOfWall.add(new Position3D(depth +1,row , column));
        }


        if (this.is_valid_position(depth  ,row + 1, column) && this.is_wall(depth  ,row + 1, column)) {
            this.listOfWall.add(new Position3D(depth -1 ,row + 1, column));
        }
        if (this.is_valid_position(depth  ,row - 1, column) && this.is_wall(depth  ,row - 1, column)) {
            this.listOfWall.add(new Position3D(depth  ,row -1, column));
        }
        if (this.is_valid_position(depth  ,row , column-1) && this.is_wall(depth  ,row , column-1)) {
            this.listOfWall.add(new Position3D(depth  ,row , column-1));
        }

        if (this.is_valid_position(depth ,row, column + 1) && this.is_wall(depth  ,row, column + 1)) {
            this.listOfWall.add(new Position3D(depth  ,row, column + 1));
        }

    }

    public boolean is_wall(int depth, int row, int column){
        return this.m_maze.value_of_position3D(depth,row,column)==1;
    }

    private boolean is_valid_arg(int depth, int row, int column) {
        if (row > 0 && column > 0 && depth >0) {
            return row != 1 || column != 1 || depth !=1;
        } else {
            return false;
        }
    }

    public boolean  is_valid_position(int depth,int row,int column){
        if(row<0){
            return false;
        }else if (row>this.m_row-1){
            return false;
        }
        else if(depth<0){
            return false;
        }else if(depth > this.m_depth-1){
            return false;
        }
        else if(column<0){
            return false;
        }else{
            return column <=this.m_col-1;
        }

    }



    public int[][][] ones_matrix(int depth,int row,int column) {
        int[][][] matrix = new int[depth][row][column];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    matrix[i][j][k] = 1;
                }

            }
        }
        return matrix;
    }
}
