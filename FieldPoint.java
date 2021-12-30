package tictactoe;

public class FieldPoint {
    private int y, x;
    private int score;

    public FieldPoint(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
