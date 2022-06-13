package com.example.game_of_life.logic;

import com.example.game_of_life.logic.enums.CellType;

public class IndexFactory {
    private final int columnsNumber;

    public IndexFactory(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public int[] getIndexes(CellType cellType, int cellIndex) {
        switch (cellType) {
            case LC: {
                return new int[]{
                        cellIndex + 1,
                        cellIndex + columnsNumber,
                        cellIndex + columnsNumber + 1,
                        cellIndex - columnsNumber,
                        cellIndex - columnsNumber - 1
                };
            }
            case MC: {
                return new int[]{
                        cellIndex + 1,
                        cellIndex + columnsNumber - 1,
                        cellIndex + columnsNumber,
                        cellIndex + columnsNumber + 1,
                        cellIndex - 1,
                        cellIndex - columnsNumber - 1,
                        cellIndex - columnsNumber,
                        cellIndex - columnsNumber + 1
                };
            }
            case RC: {
                return new int[]{
                        cellIndex - 1,
                        cellIndex - columnsNumber,
                        cellIndex - columnsNumber - 1,
                        cellIndex + columnsNumber,
                        cellIndex + columnsNumber - 1
                };
            }
            default: {
                return new int[]{};
            }
        }
    }
}
