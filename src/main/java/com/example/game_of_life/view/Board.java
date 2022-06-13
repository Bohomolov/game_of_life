package com.example.game_of_life.view;

import com.example.game_of_life.logic.factories.IndexFactory;
import com.example.game_of_life.logic.cash.CellCash;
import com.example.game_of_life.logic.enums.CellType;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JFrame {
    private final int width;
    private final int height;
    private final int columns;
    private final int rows;
    private final int numberOfCells;
    private final Random randomaiser = new Random();
    private final CellCash cash;
    private final IndexFactory indexFactory;

    public Board(int numberOfCells) throws HeadlessException {
        this.width = numberOfCells;
        this.height = numberOfCells;
        this.numberOfCells = numberOfCells;
        this.columns = (int) Math.sqrt(numberOfCells);
        this.rows = numberOfCells / this.columns;
        this.indexFactory = new IndexFactory(this.columns);
        this.cash = new CellCash(numberOfCells);
        this.setupBoard();
        this.startLifeCycle();
    }

    public void setupBoard() {
        this.fillTheBoard();
        this.setLayout(new GridLayout(this.rows, this.columns));
        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void fillTheBoard() {
        for (int index = 0; index < this.numberOfCells; index++) {
            CellType cellType = this.getCellType(index);
            int[] neighborsIndexes = this.indexFactory.getIndexes(cellType, index);
            Cell cell = new Cell(index, neighborsIndexes, this.randomaiser.nextBoolean());
            this.add(cell);
            this.cash.addToCash(cell);
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "width=" + width +
                ", height=" + height +
                ", numberOfCells=" + numberOfCells +
                '}';
    }

    private void startLifeCycle() {
        while (true) {
            for (Cell cell : this.cash.getAllCells()) {
                int numbersAliveCells = this.cash.getNumberAliveNeighbors(cell.getNeighborsIndexes());
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
        if (indexCurrentCell % this.columns == 0) {
            return CellType.LC;
        }
        if (indexCurrentCell % this.columns == this.columns - 1) {
            return CellType.RC;
        }
        return CellType.MC;
    }
}
