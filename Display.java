

import processing.core.*;

public class Display {
    private PApplet p;

    private int x, y, w, h;
    private float dx, dy;

    private int rows, cols;


    public Display(PApplet p, int x, int y, int w, int h) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void drawGrid(int[][] grid) {
        int piece;
        int numcols = grid[0].length;
        int numrows = grid.length;


        dx = w / (float) numcols;
        dy = h / (float) numrows;

        for (int i = 0; i < numrows; i++) {
            for (int j = 0; j < numcols; j++) {
                piece = grid[i][j];

                p.fill(getTileColor(piece));
                p.rect(x + j * dx, y + i * dy, dx, dy);
                displayTextOnGrid(grid, i, j, piece);
            }
        }
    }


    private int getTileColor(int value) {
        int[] colors = {
                p.color(200,200,200),
                p.color(238,228,218),
                p.color(237,224,200),
                p.color(242,177,121),
                p.color(245,149,99),
                p.color(246,124,95),
                p.color(246,94,59),
                p.color(237,207,114),
                p.color(237,204,97),
                p.color(237,200,80),
                p.color(237,197,63),
                p.color(237,194,46)
        };

        int index;
        if (value == 0) {
            index = 0;
        } else {
            index = (int)(Math.log(value) / Math.log(2));
        }

        if (index < colors.length) {
            return colors[index];
        }

        return p.color(205,193,150);
    }

    private void displayTextOnGrid(int[][] grid, int i, int j, int value) {
        if (value != 0) {
            p.fill(0);
            p.textSize(32);
            p.textAlign(PApplet.CENTER, PApplet.CENTER);
            p.text(value, x + j * dx + dx / 2, y + i * dy + dy / 2);
        }
    }


    public void initializeWithGame(GameBoard game) {
        int[][] grid = game.getGrid();
        if (grid == null) {
            System.out.println("Your 2D grid is null! Make sure it is initialized in the GameBoard constructor.");
            return;
        }
        setNumCols(grid[0].length);
        setNumRows(grid.length);
    }


    public void setNumRows(int numRows) {
        this.rows = numRows;
        dy = h / (float) rows;
    }


    public void setNumCols(int numCols) {
        this.cols = numCols;
        dx = w / (float) cols;
    }
}
