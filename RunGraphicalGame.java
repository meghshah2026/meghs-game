import processing.core.*;

public class RunGraphicalGame extends PApplet {
    GameBoard game;
    Display display;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        game = new GameBoard(4, 4);
        display = new Display(this, 50, 50, 400, 400);
        display.initializeWithGame(game);
    }

    public void draw() {
        background(200);
        display.drawGrid(game.getGrid());


        displayScore();


        if (game.isGameOver()) {
            displayGameOver();
        }
    }

    public void keyReleased() {
        if (game.isGameOver()) {
            return;
        }

        int keyVal = -1;
        if (key == CODED) {
            if (keyCode == UP) keyVal = 0;
            else if (keyCode == DOWN) keyVal = 1;
            else if (keyCode == LEFT) keyVal = 2;
            else if (keyCode == RIGHT) keyVal = 3;

            if (keyVal != -1) {
                game.move(keyVal);
            }
        }
    }


    private void displayGameOver() {
        textAlign(CENTER, CENTER);
        textSize(32);
        fill(255, 0, 0);
        text("GAME OVER", width / 2, height / 2);
    }


    private void displayScore() {
        textAlign(LEFT, TOP);
        textSize(20);
        fill(0);
        text("Score: " + game.getScore(), 10, 10);
    }

    public static void main(String[] args) {
        PApplet.main("RunGraphicalGame");
    }
}
