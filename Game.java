package tictactoe;

public class Game {
//    TODO: refactor me , by moving these constants some ware else
    public static final char STATUS_DRAW = '=';
    public static final char STATUS_GO = '?';
    private Player[] players = new Player[2];
    private GameField gameField = new GameField();

    private char gameStatus;

    public char play() {
        getField().draw();

        int playerIndex = 0;
        do {
            this.players[playerIndex].makeMove(this.getField());
            getField().draw();

            gameStatus = getField().evaluateStatus();

            playerIndex++;
            playerIndex = playerIndex % 2;
        } while (gameStatus == STATUS_GO);
        announceGameStatus();
        return gameStatus;
    }

    public void setPlayers(Player player1, Player player2) {
        this.players[0] = player1;
        this.players[1] = player2;
    }

    public GameField getField() {
        return this.gameField;
    }

    public String getStatus() {
        switch (this.gameStatus) {
            case Player.PLAYER_O:
            case Player.PLAYER_X:
                return this.gameStatus + " wins";
            case STATUS_DRAW:
                return "Draw";
            case STATUS_GO:
                return "Game not finished";
        }
        assert (false);
        return null;
    }

    public void announceGameStatus() {
        System.out.println(this.getStatus());
        System.out.println();
    }

}
