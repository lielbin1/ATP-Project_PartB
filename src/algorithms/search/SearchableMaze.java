package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private int[][] Maze;
    private MazeState StartState;
    private MazeState GoalState;


    public SearchableMaze(Maze maze) {
        this.Maze = maze.getMap();
        this.StartState = new MazeState(0,maze.getStartPosition());
        this.GoalState = new MazeState(215641684,maze.getGoalPosition());

    }


    @Override
    public AState getStartState() {
        return this.StartState;
    }

    @Override
    public AState getGoalState() {
        return this.GoalState;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> adjList = new ArrayList();
        int RowIndex = ((Position) ((MazeState) s).getState()).getRowIndex();
        int ColIndex = ((Position) ((MazeState) s).getState()).getColumnIndex();
        MazeState mazeState;
        Position position;

        if (RowIndex != 0 && this.Maze[RowIndex - 1][ColIndex] == 0) {
            position = new Position(RowIndex - 1, ColIndex);
            mazeState = new MazeState(10, position);
            adjList.add(mazeState);
        }

        if (RowIndex != 0 && ColIndex != this.Maze[0].length - 1 && this.Maze[RowIndex - 1][ColIndex + 1] == 0) {
            position = new Position(RowIndex - 1, ColIndex + 1);
            mazeState = new MazeState(15, position);
            adjList.add(mazeState);
        }

        if (ColIndex != this.Maze[0].length - 1 && this.Maze[RowIndex][ColIndex + 1] == 0) {
            position = new Position(RowIndex, ColIndex + 1);
            mazeState = new MazeState(10, position);
            adjList.add(mazeState);
        }

        if (RowIndex != this.Maze.length - 1 && ColIndex != this.Maze[0].length - 1 && this.Maze[RowIndex + 1][ColIndex + 1] == 0) {
            position = new Position(RowIndex + 1, ColIndex + 1);
            mazeState = new MazeState(15, position);
            adjList.add(mazeState);
        }

        if (RowIndex != this.Maze.length - 1 && this.Maze[RowIndex + 1][ColIndex] == 0) {
            position = new Position(RowIndex + 1, ColIndex);
            mazeState = new MazeState(10, position);
            adjList.add(mazeState);
        }

        if (RowIndex != this.Maze.length - 1 && ColIndex != 0 && this.Maze[RowIndex + 1][ColIndex - 1] == 0) {
            position = new Position(RowIndex + 1, ColIndex - 1);
            mazeState = new MazeState(15, position);
            adjList.add(mazeState);
        }

        if (ColIndex != 0 && this.Maze[RowIndex][ColIndex - 1] == 0) {
            position = new Position(RowIndex, ColIndex - 1);
            mazeState = new MazeState(10, position);
            adjList.add(mazeState);
        }

        if (RowIndex != 0 && ColIndex != 0 && this.Maze[RowIndex - 1][ColIndex - 1] == 0) {
            position = new Position(RowIndex - 1, ColIndex - 1);
            mazeState = new MazeState(15, position);
            adjList.add(mazeState);
        }

        return adjList;
    }
}
