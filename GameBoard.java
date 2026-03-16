import java.util.Random;

public class GameBoard {
    private int[][] grid;
    private final int SIZE;
    private Random random;
    private int score;

    public GameBoard(int rows, int cols) {
        this.SIZE = rows;
        grid = new int[SIZE][SIZE];
        random = new Random();
        score = 0;
        addRandomTile();
        addRandomTile();
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }

    public void move(int direction) {
        boolean moved = false;

        if (direction == 0) {
            moved = moveUp();
        } else if (direction == 1) {
            moved = moveDown();
        } else if (direction == 2) {
            moved = moveLeft();
        } else if (direction == 3) {
            moved = moveRight();
        }

        if (moved) {
            addRandomTile();
        }
    }


    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i < SIZE - 1 && grid[i][j] == grid[i + 1][j]) {
                    return false;
                }
                if (j < SIZE - 1 && grid[i][j] == grid[i][j + 1]) {
                    return false;
                }
            }
        }


        return true;
    }

    private boolean moveLeft() {
        boolean moved = false;

        for (int row = 0; row < SIZE; row++) {
            int[] newRow = new int[SIZE];
            int index = 0;

            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] != 0) {
                    newRow[index++] = grid[row][col];
                }
            }

            for (int i = 0; i < SIZE - 1; i++) {
                if (newRow[i] != 0 && newRow[i] == newRow[i + 1]) {
                    newRow[i] *= 2;
                    newRow[i + 1] = 0;
                    score += newRow[i];
                    moved = true;
                }
            }

            int[] finalRow = new int[SIZE];
            index = 0;
            for (int val : newRow) {
                if (val != 0) {
                    finalRow[index++] = val;
                }
            }

            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] != finalRow[col]) moved = true;
                grid[row][col] = finalRow[col];
            }
        }

        return moved;
    }

    private boolean moveRight() {
        rotate180();
        boolean moved = moveLeft();
        rotate180();
        return moved;
    }

    private boolean moveUp() {
        rotateLeft();
        boolean moved = moveLeft();
        rotateRight();
        return moved;
    }

    private boolean moveDown() {
        rotateRight();
        boolean moved = moveLeft();
        rotateLeft();
        return moved;
    }

    private void rotateRight() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                newGrid[j][SIZE - 1 - i] = grid[i][j];
        grid = newGrid;
    }

    private void rotateLeft() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                newGrid[SIZE - 1 - j][i] = grid[i][j];
        grid = newGrid;
    }

    private void rotate180() {
        rotateRight();
        rotateRight();
    }

    public void addRandomTile() {
        int emptyCount = 0;

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (grid[i][j] == 0)
                    emptyCount++;

        if (emptyCount == 0) return;

        int spot = random.nextInt(emptyCount);
        int count = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    if (count == spot) {
                        grid[i][j] = random.nextDouble() < 0.9 ? 2 : 4;
                        return;
                    }
                    count++;
                }
            }
        }
    }

}
