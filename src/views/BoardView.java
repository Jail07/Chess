package views;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.utils.Color;
import views.utils.BoardColors;

import java.util.*;
import java.util.stream.Collectors;

public class BoardView {

    // Метод для очистки экрана
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Метод для чтения позиции шахматной фигуры с консоли
    public static ChessPosition readChessPosition(Scanner sc){
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Ошибка при чтении позиции на доске. Допустимые значения от a1 до h8");
        }
    }

    // Метод для печати состояния игры
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces){
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println("Ход: " + chessMatch.getTurn());
        if (!chessMatch.isCheckMate()){
            System.out.println("Ожидание игрока: " + chessMatch.getCurrentPlayer());
            if (chessMatch.isCheck()){
                System.out.println("Шах!");
            }
        } else {
            System.out.println("Шах и мат!");
            System.out.println("Победитель: " + chessMatch.getCurrentPlayer());
        }
    }

    // Метод для печати доски
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    // Метод для печати доски с возможными ходами
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    // Метод для печати фигуры
    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(BoardColors.ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print(BoardColors.ANSI_RED + "@" + BoardColors.ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(BoardColors.ANSI_WHITE + piece + BoardColors.ANSI_RESET);
            } else {
                System.out.print(BoardColors.ANSI_YELLOW + piece + BoardColors.ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    // Метод для печати захваченных фигур
    private static void printCapturedPieces(List<ChessPiece> chessPieces){
        List<ChessPiece> white = chessPieces.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = chessPieces.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Захваченные фигуры: ");
        System.out.print("Белые фигуры: ");
        System.out.print(BoardColors.ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(BoardColors.ANSI_RESET);
        System.out.print("Черные фигуры: ");
        System.out.print(BoardColors.ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
    }
}
