package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        var side = 7.;
        Square.printSquareArea(7.0);
        Square.printSquareArea(3.0);
        Square.printSquareArea(5.5);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(5.0, 1.5);

    }

}
