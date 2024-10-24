package boardgame;

import boardgame.exceptions.BoardException;

public class Board {
    private Integer rows; // Количество строк на доске
    private Integer columns; // Количество столбцов на доске
    private Piece[][] pieces; // Массив фигур

    // Конструктор доски
    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Ошибка при создании доски: " +
                    "Необходимо, чтобы было хотя бы 1 строка и 1 столбец");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns]; // Инициализация массива фигур
    }

    // Возвращает количество строк
    public Integer getRows() {
        return rows;
    }

    // Возвращает количество столбцов
    public Integer getColumns() {
        return columns;
    }

    // Возвращает фигуру по индексу строки и столбца
    public Piece piece(Integer row, Integer column){
        if (!positionExists(row, column)) {
            throw new BoardException("Позиция находится за пределами доски");
        }
        return pieces[row][column];
    }

    // Возвращает фигуру по позиции
    public Piece piece(Position position){
        if (!positionExists(position)) {
            throw new BoardException("Позиция находится за пределами доски");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    // Устанавливает фигуру на указанную позицию
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("На позиции уже есть фигура " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    // Удаляет фигуру с доски
    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Позиция находится за пределами доски");
        }
        if (piece(position) == null) {
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    // Проверяет, существует ли позиция по строке и столбцу
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // Проверяет, существует ли позиция
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    // Проверяет, есть ли фигура на позиции
    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Позиция находится за пределами доски");
        }
        return piece(position) != null;
    }
}
