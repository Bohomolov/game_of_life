package com.example.game_of_life.view;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private static final Color ALIVE_COLOR = Color.GREEN;
    private static final Color DEAD_COLOR = Color.BLACK;
    private final int index;
    private final int[] neighborsIndexes;
    private boolean isAlive;

    public Cell(int index, int[] neighborsIndexes, boolean isAlive) {
        this.index = index;
        this.neighborsIndexes = neighborsIndexes;
        this.changeStatus(isAlive);
        this.setVisible(true);
    }

    public void changeStatus(boolean status) {
        this.isAlive = status;
        this.setBackground(this.isAlive ? ALIVE_COLOR : DEAD_COLOR);
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public int getIndex() {
        return this.index;
    }

    public int[] getNeighborsIndexes() {
        return this.neighborsIndexes;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "index=" + this.index +
                "isAlive=" + this.isAlive +
                '}';
    }
}
