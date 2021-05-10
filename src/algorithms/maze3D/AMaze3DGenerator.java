package algorithms.maze3D;



public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    protected int m_row;
    protected int m_col;
    protected int m_depth;
    protected Maze3D m_maze;

    public AMaze3DGenerator() {
    }

    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start_time = System.currentTimeMillis();
        this.generate(depth,row,column);
        long end_time = System.currentTimeMillis();
        long res = end_time - start_time;
        return res;
    }
}
