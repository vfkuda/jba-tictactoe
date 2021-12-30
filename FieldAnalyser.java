package tictactoe;

import java.util.function.Predicate;

class FieldAnalyser {
    private final byte[][] winPatterns = new byte[][]{
            {0, 0, 0, 1, 0, 2},
            {1, 0, 1, 1, 1, 2},
            {2, 0, 2, 1, 2, 2},

            {0, 0, 1, 0, 2, 0},
            {0, 1, 1, 1, 2, 1},
            {0, 2, 1, 2, 2, 2},

            {0, 0, 1, 1, 2, 2},
            {0, 2, 1, 1, 2, 0}
    };
    private GameField gf;

    public FieldAnalyser(GameField gameField) {
        this.gf = gameField;
    }

    protected FieldPoint[] getPointsAlongThePatternt(int patterntNo) {
        FieldPoint[] retVal = new FieldPoint[3];
        byte[] winPattern = winPatterns[patterntNo];
        retVal[0] = new FieldPoint(winPattern[0], winPattern[1]);
        retVal[1] = new FieldPoint(winPattern[2], winPattern[3]);
        retVal[2] = new FieldPoint(winPattern[4], winPattern[5]);
        return retVal;
    }

    protected char[] getTilesAt(FieldPoint[] points) {
        char[] retVal = new char[3];
        for (int i = 0; i < 3; i++) {
            retVal[i] = gf.getTile(points[i]);
        }
        return retVal;
    }

    protected FieldAnalytics analyzePattern(int patterntNo) {
        final char[] markers = {Player.PLAYER_X, Player.PLAYER_O};

        FieldAnalytics fa = new FieldAnalytics();
        fa.patternNo = patterntNo;
//        fa.scores = {0, 0};
        fa.points = getPointsAlongThePatternt(patterntNo);
        fa.tiles = getTilesAt(fa.points);
        for (char t : fa.tiles) {
            for (int i = 0; i < 2; i++) {
                if (markers[i] == t) {
                    fa.scores[i]++;
                }
            }
        }
        return fa;
    }

    protected FieldAnalytics findMatchingPattern(Predicate<FieldAnalytics> p) {
        for (int i = 0; i < winPatterns.length; i++) {
            FieldAnalytics fa = analyzePattern(i);
            if (p.test(fa)) {
                return fa;
            }
        }
        return null;
    }

    public char findWinner() {
        FieldAnalytics fa = findMatchingPattern(a -> (a.scores[0] == 3) || (a.scores[1] == 3));
        if (null != fa) {
            return fa.tiles[0];
        }
        return Player.PLAYER_UNKNOWN;
    }

    public FieldPoint findOpportunityFor(char player) {
        int idx = player == Player.PLAYER_X ? 0 : 1;
        FieldAnalytics fa = findMatchingPattern(a -> (a.scores[idx] == 2));
        if (null != fa) {
            return fa.getEmptyPoint();
        }
        return null;
    }
}
