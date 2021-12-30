package tictactoe;

public class FieldAnalytics {

    int patternNo;
    FieldPoint[] points;
    char[] tiles = {' ', ' ', ' '};
    int[] scores = {0, 0};

    public int getTotalScore() {
        return scores[0] + scores[1];
    }

//    public boolean hasWinningScore() {
//        return (scores[0] == 3) || (scores[1] == 3);
//    }

    public FieldPoint getEmptyPoint() {
        for (int i = 0; i < 3; i++) {
            if (tiles[i] == ' ') {
                return points[i];
            }
        }
        return null;
    }

}
