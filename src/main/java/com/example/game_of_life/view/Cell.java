package com.example.game_of_life.view;

import com.example.game_of_life.logic.enums.CellType;
import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private final int index;
    private final Color aliveColor = Color.GREEN;
    private final Color deadColor = Color.BLACK;

    public int[] getNeighborsIndexes() {
        return neighborsIndexes;
    }

    private final int[] neighborsIndexes;

    public int getIndex() {
        return index;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private boolean isAlive;

    public Cell(int index, int[] neighborsIndexes, boolean isAlive) {
        this.index = index;
        this.neighborsIndexes = neighborsIndexes;
        this.isAlive = isAlive;
        this.changeColor();
        this.setVisible(true);
    }


    public void changeStatus(boolean status) {
        this.isAlive = status;
        changeColor();
    }

    private void changeColor() {
        this.setBackground(isAlive ? aliveColor : deadColor);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "index=" + index +
                "isAlive=" + isAlive +
                '}';
    }
}
