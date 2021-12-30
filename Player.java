package tictactoe;

public abstract class Player {
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static final char PLAYER_UNKNOWN = '?';

    protected char player;
//    protected String complexityLevel;

    public Player() {

    }

//    public Player(String complexityLevel) {
//        this.complexityLevel = complexityLevel;
//    }

    public void playAs(char player) {
        this.player = player;
    }

    public char getOpponent(char player) {
        return player == PLAYER_X ? PLAYER_O : PLAYER_X;
    }

    public char getOpponent() {
        return getOpponent(this.player);
    }

    public abstract void makeMove(GameField field);
}
