package com.example.game_of_life.logic.cash;

import com.example.game_of_life.view.Cell;
import java.util.ArrayList;
import java.util.List;

public class CellCash {
    private final int size;
    private final List<Cell> cache;

    public CellCash(int size) {
        this.size = size;
        this.cache = new ArrayList<>(size);
    }

    public void addToCash(Cell cell) {
        cache.add(cell);
    }

    public List<Cell> getAllCells() {
        return new ArrayList<>(cache);
    }

    public int getNumberAliveNeighbors(int[] indexes) {
        int numberAliveCells = 0;
        for (int i : indexes) {
            if (i < 0 || i > size - 1) {
                continue;
            }
            Cell cell = cache.get(i);
            if (cell.isAlive()) {
                numberAliveCells += 1;
            }
        }
        return numberAliveCells;
    }
}
