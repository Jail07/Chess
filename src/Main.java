import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;
import views.BoardView;
import views.utils.BoardColors;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captureChessPieces = new ArrayList<>();
        System.out.print(BoardColors.ANSI_YELLOW_BACKGROUND);
        System.out.print(BoardColors.ANSI_BLACK);
        while (!chessMatch.isCheckMate()){
            try{
                System.out.print(BoardColors.ANSI_RESET);
                BoardView.clearScreen();
                BoardView.printMatch(chessMatch, captureChessPieces);
                System.out.println();
                System.out.print("Введите исходную позицию: ");
                ChessPosition source = BoardView.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                BoardView.clearScreen();
                BoardView.printBoard(chessMatch.getPieces(),possibleMoves);
                System.out.println();
                System.out.print("Введите целевую позицию: ");
                ChessPosition target  = BoardView.readChessPosition(sc);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if(capturedPiece != null){
                    captureChessPieces.add(capturedPiece);
                }
                if(chessMatch.getPromoted() != null){
                    System.out.println("Если фигура была превращена, выбрать новую фигуру (Ферзь/Ладья/Конь/Слон).");
                    String type = sc.nextLine();
                    chessMatch.replacepromotedPiece(type);
                }
            }
            catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        BoardView.clearScreen();
        BoardView.printMatch(chessMatch, captureChessPieces);
    }
}
