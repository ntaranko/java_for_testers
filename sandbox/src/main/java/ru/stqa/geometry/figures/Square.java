package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double a) {
        var text = String.format("Площадь квадрата со стороной %f = %f",
                a, squareArea(a));
        System.out.println( text);
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
