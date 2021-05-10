package Server;


import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public ServerStrategySolveSearchProblem() {
    }

    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            Maze maze = (Maze)in.readObject();
            String maze_name = Integer.toString((new String(maze.toByteArray(), "ISO-8859-1")).hashCode());
            String tempdir = System.getProperty("java.io.tmpdir");
            String maze_path = tempdir + maze_name;
            File file = new File(maze_path);
            if (file.createNewFile()) {
                FileOutputStream fout = new FileOutputStream(maze_path);
                ObjectOutputStream sol_file = new ObjectOutputStream(fout);
                ISearchingAlgorithm SolveBest = new BestFirstSearch();
                ISearchable SearchMaze = new SearchableMaze(maze);
                Solution sol = SolveBest.solve(SearchMaze);
                sol_file.writeObject(sol);
                sol_file.flush();
                sol_file.close();
                fout.close();
                out.writeObject(sol);
                out.flush();
            } else {
                FileInputStream fin = new FileInputStream(maze_path);
                ObjectInputStream sol_in = new ObjectInputStream(fin);
                Solution sol = (Solution)sol_in.readObject();
                out.writeObject(sol);
                out.flush();
            }
        } catch (IOException var15) {
            var15.printStackTrace();
        } catch (ClassNotFoundException var16) {
            var16.getMessage();
        }

    }
}