package chess;

import boardgame.Position;
import chess.exceptions.ChessException;

public class ChessPosition {

    private char column;
    private Integer row;

    // Конструктор с проверкой допустимых значений для шахматной позиции
    public ChessPosition(char column, Integer row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Ошибка при создании позиции на доске. Допустимые значения от a1 до h8.");
        }
        this.column = column;
        this.row = row;
    }

    // Получение колонки (столбца)
    public char getColumn() {
        return column;
    }

    // Получение строки
    public Integer getRow() {
        return row;
    }

    // Преобразование шахматной позиции в объект Position
    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    // Преобразование объекта Position обратно в шахматную позицию
    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
    }

    // Метод toString для отображения позиции в формате "a1"
    @Override
    public String toString() {
        return "" + column + row;
    }
}
