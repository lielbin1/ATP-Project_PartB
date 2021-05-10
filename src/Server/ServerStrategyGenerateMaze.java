package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy,Serializable{
    private int[] row_col = new int[2];

    public ServerStrategyGenerateMaze() {
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException{

        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

        try{
            row_col = (int[])((int[])fromClient.readObject());
            IMazeGenerator generator = new MyMazeGenerator();
            Maze maze = generator.generate(row_col[0],row_col[1]);
            MyCompressorOutputStream mazeCompressor = new MyCompressorOutputStream(toClient);
            mazeCompressor.write(maze.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
