package pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.exceptions.ChessException;
import chess.utils.Color;

public class JK extends ChessPiece {
    private Board board;
    private Color currentPlayer;
    private ChessMatch chessMatch;

    public JK(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }
    public ChessMatch getChessMatch() {
        return chessMatch;
    }
    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return  p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return position !=null && p instanceof Rook && getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat= new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);


        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                Position placePiece = new Position(i, j);
//                System.out.println(i+" | "+ j);
                Boolean action = isThereYourPiece(placePiece);
//                System.out.println(action);
                if (action){
                    mat[i][j] = true;
                }


            }

        }

        return mat;
    }


}