package ru.stqa.geometry;

import ru.stqa.geometry.figures.Square;

import java.util.List;

public class Geometry {
    public static void main(String[] args) {
        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));

       // Consumer<Square> print = Square::printSquareArea;

        squares.forEach(Square::printSquareArea);
/*
        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(5.0, 1.5);
*/

    }

}
