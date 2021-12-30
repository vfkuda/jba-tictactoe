package tictactoe;

import java.util.List;
import java.util.function.BinaryOperator;

public class PlayerAIHard extends Player {

    protected FieldPoint minimax(GameField field, char player) {
        char status = field.evaluateStatus();
        FieldPoint p;

        p = new FieldPoint(0, 0);
        if (status == this.player) {
            p.setScore(10);
            return p;
        } else if (status == getOpponent()) {
            p.setScore(-10);
            return p;

        } else if (status == Game.STATUS_DRAW) {
            p.setScore(0);
            return p;
        }

        List<FieldPoint> moves = field.getEmpties();
        for (FieldPoint move : moves) {
            field.setTile(move, player);
            p = minimax(field, getOpponent(player));
            move.setScore(p.getScore());
            field.setTile(move, GameField.SPACE);
        }

//        //        p  is the best move
//        for (FieldPoint move : moves) {
//                if (move.getScore() > p.getScore()) {
//                    p = move;
//                }
//            }
//        }
        BinaryOperator<FieldPoint> bo;
        if (player == this.player) {
            //            maximize
            bo = (bestmove, mv) -> bestmove.getScore() < mv.getScore() ? mv : bestmove;
        } else {
            //            maximize
            bo = (bestmove, mv) -> bestmove.getScore() > mv.getScore() ? mv : bestmove;
        }
        p = moves.stream().reduce(bo).get();
        return p;
    }

    @Override
    public void makeMove(GameField field) {
        System.out.println("Making move level \"hard\"");
        FieldPoint bestmove = minimax(field.clone(), this.player);
        field.setTile(bestmove, this.player);

    }
}
