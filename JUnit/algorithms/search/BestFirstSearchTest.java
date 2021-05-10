package algorithms.search;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import org.junit.jupiter.api.Test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Assertions;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    public IMazeGenerator mg = new MyMazeGenerator();
    Maze testMyMaze = mg.generate(100,100);
    BestFirstSearch bfs = new BestFirstSearch();

    BestFirstSearchTest() {
    }

    @Test
    void solve1() {
        Maze maze_test_13 = this.mg.generate(423, 952);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_13);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve2() {
        Maze maze_test_12 = this.mg.generate(0, 3);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_12);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve3() {
        Maze maze_test_11 = this.mg.generate(-1, -4);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_11);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve4() {
        Maze maze_test_10 = this.mg.generate(2, 2);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_10);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve5() {
        Maze maze_test_9 = this.mg.generate(20, 1);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_9);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve6() {
        Maze maze_test_8 = this.mg.generate(1, 20);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_8);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve7() {
        Maze maze_test_7 = this.mg.generate(45, 14);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_7);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve8() {
        Maze maze_test_6 = this.mg.generate(17, 92);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_6);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve9() {
        Maze maze_test_5 = this.mg.generate(10, 5);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_5);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve10() {
        Maze maze_test_4 = this.mg.generate(5, 10);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_4);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve11() {
        Maze maze_test_3 = this.mg.generate(10, 10);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_3);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve12() {
        Maze maze_test_2 = this.mg.generate(0, 0);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_2);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void solve13() {
        Maze maze_test_1 = this.mg.generate(1, 1);
        SearchableMaze searchableMaze = new SearchableMaze(maze_test_1);
        Assertions.assertNotNull(searchableMaze);
    }

    @Test
    void getName() { assertEquals("BestFirstSearch" ,bfs.getName() );

    }

    @Test
    void getNumberOfNodesEvaluated() { assertEquals(0,bfs.getNumberOfNodesEvaluated());
    }

    @Test
    void searchGoalstate() {
        IMaze3DGenerator MazeGp = new MyMaze3DGenerator();
        bfs = new BestFirstSearch();
        Maze3D maze = MazeGp.generate(90, 90,100);
        SearchableMaze3D game = new SearchableMaze3D(maze);
        assertNotNull(bfs.solve(game));
    }


    @Test
    void solveMaze3D() {
        bfs =new BestFirstSearch();
        IMaze3DGenerator MazeGp = new MyMaze3DGenerator();
        Maze3D maze = MazeGp.generate(30, 40,50);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        assertNotNull(bfs.solve(searchableMaze));

    }

    @Test
    void NullSolve() {
        bfs =new BestFirstSearch();
        assertThrows(NullPointerException.class,()-> bfs.solve(null));
    }

    @Test
    void CheckPathLength(){
        IMazeGenerator MazeGp = new MyMazeGenerator();
        Maze maze = MazeGp.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        bfs = new BestFirstSearch();
        BreadthFirstSearch breadth= new BreadthFirstSearch();
        Solution s1= bfs.solve(searchableMaze);
        Solution s2=breadth.solve(searchableMaze);
        assertTrue(s2.getSolutionPath().size() <= s1.getSolutionPath().size());
    }


    @Test
    void TimeMaze3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(100, 100,100);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        bfs=new BestFirstSearch();
        assertTimeout(Duration.ofMinutes(1),()->bfs.solve(searchableMaze));
    }


    @Test
    void getNumberOfNodes(){
        bfs=new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        bfs.solve(searchableMaze);
        assertNotEquals(1,bfs.getNumberOfNodesEvaluated());
    }


}

