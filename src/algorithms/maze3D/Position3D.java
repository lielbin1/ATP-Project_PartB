package algorithms.maze3D;

import java.util.Objects;

public class Position3D {
    private int RowIndex;
    private int ColumnIndex;
    private int DepthIndex;

    public int getDepthIndex() {
        return this.DepthIndex;
    }

    public int getRowIndex() {
        return this.RowIndex;
    }

    public int getColumnIndex() {
        return this.ColumnIndex;
    }

    public Position3D(int depthIndex, int rowIndex, int columnIndex) {
        RowIndex = rowIndex;
        ColumnIndex = columnIndex;
        DepthIndex = depthIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D that = (Position3D) o;
        return RowIndex == that.RowIndex && ColumnIndex == that.ColumnIndex && DepthIndex == that.DepthIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(DepthIndex,RowIndex, ColumnIndex);
    }

    @Override
    public String toString() {
        return "Position{"+ DepthIndex+',' + RowIndex +','+ ColumnIndex + '}';
    }
}
