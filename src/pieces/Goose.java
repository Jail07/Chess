package pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.utils.Color;

public class Goose extends ChessPiece {
    public Goose(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return  p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);


        if (getColor().equals(Color.WHITE)) {
            //above
            p.setValues(position.getRow() - 2, position.getColumn());
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

    //        //below
    //        p.setValues(position.getRow() + 1, position.getColumn());
    //        if(getBoard().positionExists(p) && canMove(p)){
    //            mat[p.getRow()][p.getColumn()] = true;
    //        }

            //left
            p.setValues(position.getRow(), position.getColumn() - 2);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            //right
            p.setValues(position.getRow(), position.getColumn() + 2);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

    //        p.setValues(position.getRow() + 1, position.getColumn() + 1);
    //        if(getBoard().positionExists(p) && canMove(p)){
    //            mat[p.getRow()][p.getColumn()] = true;
    //        }

            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            //southeast
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        } else if (getColor().equals(Color.BLACK)) {
            //above
            p.setValues(position.getRow() + 2, position.getColumn());
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            //left
            p.setValues(position.getRow(), position.getColumn() - 2);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            //right
            p.setValues(position.getRow(), position.getColumn() + 2);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            //southeast
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if(getBoard().positionExists(p) && canMove(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }

        return mat;
    }

    @Override
    public String toString(){
        return "G";
    }
}

