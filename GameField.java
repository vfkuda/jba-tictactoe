package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static tictactoe.Player.*;

public class GameField {
    public static final char SPACE = ' ';
    public static final int FIELD_X = 3;
    public static final int FIELD_Y = 3;
    private FieldAnalyser fa;
    private char[][] gameField = new char[FIELD_Y][FIELD_X];

    public GameField() {
        this.initializeWithString(dupes(SPACE, FIELD_X * FIELD_Y));
        this.fa = new FieldAnalyser(this);
    }

    public FieldAnalyser getAnalyzer() {
        return fa;
    }

    public void draw() {

        System.out.println("---------");
        for (int i = 0; i < FIELD_Y; i++) {
            System.out.print("| ");
            for (int j = 0; j < FIELD_X; j++) {
                System.out.print(this.gameField[i][j] + " ");
            }
            System.out.println('|');
        }
        System.out.println("---------");
    }

    public char whosPlayingNext() {
        int Xs = 0;
        int Os = 0;
        for (int i = 0; i < FIELD_Y; i++) {
            for (int j = 0; j < FIELD_X; j++) {
                switch (this.gameField[i][j]) {
                    case PLAYER_X:
                        Xs++;
                        break;
                    case PLAYER_O:
                        Os++;
                        break;
                }
            }
        }
        return Xs == Os ? PLAYER_X : PLAYER_O;

    }

    public void initializeWithString(String fieldAsString) {
        int pos = 0;

        for (int i = 0; i < FIELD_Y; i++) {
            for (int j = 0; j < FIELD_X; j++) {
                char cellVal = fieldAsString.charAt(pos++);
                gameField[i][j] = cellVal == '_' ? SPACE : cellVal;
            }
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < FIELD_Y; i++) {
            for (int j = 0; j < FIELD_X; j++) {
                char cellVal = gameField[i][j];
                sb.append(cellVal == SPACE ? '_' : cellVal);
            }
        }
        return sb.toString();
    }

    private String dupes(char ch, int n) {
        char[] filler = new char[n];
        Arrays.fill(filler, ch);
        return new String(filler);
    }

    public char getTile(int y, int x) {
        return this.gameField[y][x];
    }

    public char getTile(FieldPoint p) {
        return this.getTile(p.getY(), p.getX());
    }

    public void setTile(int y, int x, char tile) {
        this.gameField[y][x] = tile;
    }

    public void setTile(FieldPoint p, char tile) {
        this.setTile(p.getY(), p.getX(), tile);
    }

    public List<FieldPoint> getEmpties() {
        List<FieldPoint> points = new ArrayList<>();
        for (int i = 0; i < FIELD_Y; i++) {
            for (int j = 0; j < FIELD_X; j++) {
                if (gameField[i][j] == SPACE) {
                    points.add(new FieldPoint(i, j));
                }
            }
        }
        return points;
    }

    public char evaluateStatus() {
        char whoWon = fa.findWinner();
        if (whoWon != PLAYER_UNKNOWN) {
            return whoWon;
        }

//        TODO: refactor this to getEmpties
        int emptySpaces = 0;
        for (int i = 0; i < FIELD_Y; i++) {
            for (int j = 0; j < FIELD_X; j++) {
                if (gameField[i][j] == SPACE) {
                    emptySpaces++;
                }
            }
        }

        if (0 == emptySpaces) {
            return Game.STATUS_DRAW;
        }
        return Game.STATUS_GO;
    }

    public FieldPoint getRandomFreePoint() {
        Random r = new Random();
        boolean isFree;
        int x;
        int y;
        do {
            x = r.nextInt(GameField.FIELD_X);
            y = r.nextInt(GameField.FIELD_Y);
            isFree = this.getTile(y, x) == GameField.SPACE;
        } while (!isFree);
        return new FieldPoint(y, x);
    }

    public GameField clone() {
        GameField gf = new GameField();
        gf.initializeWithString(this.toString());
        return gf;
    }

}
