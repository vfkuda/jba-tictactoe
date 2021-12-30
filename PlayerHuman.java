package tictactoe;

class PlayerHuman extends Player {

    @Override
    public void makeMove(GameField gameField) {
        boolean correctMove = false;
        while (!correctMove) {
            FieldPoint yx = UserInterface.get().readCoords();
            int posY = yx.getY();
            int posX = yx.getX();

            //checking boundaries and occupancy
            if (posX < 1 || posX > GameField.FIELD_X || posY < 1 || posY > GameField.FIELD_Y) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                posX--;
                posY--;
                if (gameField.getTile(posY, posX) != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    gameField.setTile(posY, posX, this.player);
                    correctMove = true;
                }
            }

        }
    }
}
