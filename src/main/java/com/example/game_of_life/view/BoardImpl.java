package com.example.game_of_life.view;

import com.example.game_of_life.logic.IndexFactory;
import com.example.game_of_life.logic.cash.CellCash;
import com.example.game_of_life.logic.enums.CellType;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardImpl extends JFrame implements Board {
    private final int width;
    private final int height;
    private final int columns;
    private final int rows;
    private final int numberOfCells;
    private final Random randomaiser = new Random();
    private final CellCash cash;
    private final IndexFactory indexFactory;

    public BoardImpl(int numberOfCells) throws HeadlessException {
        this.width = numberOfCells;
        this.height = numberOfCells;
        this.numberOfCells = numberOfCells;
        this.columns = (int) Math.sqrt(numberOfCells);
        this.rows = numberOfCells / columns;
        indexFactory = new IndexFactory(columns);
        this.cash = new CellCash(numberOfCells);
        setupBoard();
        try {
            startLifeCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setupBoard() {
        fillTheBoard();
        this.setLayout(new GridLayout(rows, columns));
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void fillTheBoard() {
        for (int index = 0; index < numberOfCells; index++) {
            CellType cellType = getCellType(index);
            int[] neighborsIndexes = indexFactory.getIndexes(cellType, index);
            Cell cell = new Cell(index, neighborsIndexes, randomaiser.nextBoolean());
            this.add(cell);
            this.cash.addToCash(cell);
        }
    }

    @Override
    public String toString() {
        return "BoardImpl{" +
                "width=" + width +
                ", height=" + height +
                ", numberOfCells=" + numberOfCells +
                '}';
    }

    private void startLifeCycle() throws InterruptedException {
        while (true) {
            for (Cell cell : cash.getAllCells()) {
                int numbersAliveCells = cash.getNumberAliveNeighbors(cell.getNeighborsIndexes());
                if (cell.isAlive() && (numbersAliveCells < 2 || numbersAliveCells > 3)) {
                    cell.changeStatus(false);
                    continue;
                }
                if (numbersAliveCells == 3) {
                    cell.changeStatus(true);
                }
            }
            this.repaint();
        }
    }

    private CellType getCellType(int indexCurrentCell) {
        if (indexCurrentCell % 10 == 0) {
            return CellType.LC;
        }
        if (indexCurrentCell % 10 == 9) {
            return CellType.RC;
        }
        return CellType.MC;
    }
}
