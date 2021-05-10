package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    protected int m_row;
    protected int m_col;
    protected Maze m_maze;

    public AMazeGenerator() {
    }

    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long start_time = System.currentTimeMillis();
        this.generate(row,col);
        long end_time = System.currentTimeMillis();
        long res = end_time - start_time;
        return res;
    }
}