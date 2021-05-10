package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable{
    private int RowIndex;
    private int ColumnIndex;

    public Position(int row_index, int col_index) {
        this.RowIndex = row_index;
        this.ColumnIndex = col_index;
    }

    public int getRowIndex() {
        return this.RowIndex;
    }

    public int getColumnIndex() {
        return this.ColumnIndex;
    }


    @Override
    public String toString() {
        return "{" + RowIndex +','+ ColumnIndex + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return RowIndex == position.RowIndex && ColumnIndex == position.ColumnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(RowIndex, ColumnIndex);
    }
}
