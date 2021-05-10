package algorithms.maze3D;


import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private int[][][] Maze3D;
    private Maze3DState StartState;
    private Maze3DState GoalState;



    public SearchableMaze3D(Maze3D maze) {
        this.Maze3D = maze.getMap();
        this.StartState = new Maze3DState(0,maze.getStartPosition());
        this.GoalState = new Maze3DState(215641684,maze.getGoalPosition());

    }


    @Override
    public Maze3DState getStartState() {
        return StartState;
    }


    @Override
    public Maze3DState getGoalState() {
        return GoalState;
    }


    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> adjList = new ArrayList();
        int RowIndex = ((Position3D) ((Maze3DState) s).getState()).getRowIndex();
        int ColIndex = ((Position3D) ((Maze3DState) s).getState()).getColumnIndex();
        int DepthIndex = ((Position3D) ((Maze3DState) s).getState()).getDepthIndex();
        Maze3DState mazeState;
        Position3D position;

        if (RowIndex != 0 && this.Maze3D[DepthIndex][RowIndex - 1][ColIndex] == 0) {
            position = new Position3D(DepthIndex ,RowIndex - 1, ColIndex);
            mazeState = new Maze3DState(10, position);
            adjList.add(mazeState);
        }


        if (ColIndex != this.Maze3D[0][0].length - 1 && this.Maze3D[DepthIndex][RowIndex][ColIndex + 1] == 0) {
            position = new Position3D(DepthIndex,RowIndex, ColIndex + 1);
            mazeState = new Maze3DState(10, position);
            adjList.add(mazeState);
        }


        if (RowIndex != this.Maze3D[0].length - 1 && this.Maze3D[DepthIndex][RowIndex + 1][ColIndex] == 0) {
            position = new Position3D(DepthIndex,RowIndex + 1, ColIndex);
            mazeState = new Maze3DState(10, position);
            adjList.add(mazeState);
        }


        if (ColIndex != 0 && this.Maze3D[DepthIndex][RowIndex][ColIndex - 1] == 0) {
            position = new Position3D(DepthIndex,RowIndex, ColIndex - 1);
            mazeState = new Maze3DState(10, position);
            adjList.add(mazeState);
        }

        if (DepthIndex != this.Maze3D.length - 1 && this.Maze3D[DepthIndex + 1][RowIndex][ColIndex] == 0) {
            position = new Position3D(DepthIndex + 1,RowIndex, ColIndex);
            mazeState = new Maze3DState(10, position);
            adjList.add(mazeState);
        }

        if (DepthIndex != 0 && this.Maze3D[DepthIndex - 1][RowIndex][ColIndex] == 0) {
            position = new Position3D(DepthIndex - 1,RowIndex, ColIndex);
            mazeState = new Maze3DState(10, position);
            adjList.add(mazeState);
        }


        return adjList;
    }
}

