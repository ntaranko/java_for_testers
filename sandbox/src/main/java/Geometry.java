public class Geometry {
    public static void main(String[] args) {
        var side = 7.;
        printSquareArea(7.0);
        printSquareArea(3.0);
        printSquareArea(5.5);

        printRectangleArea(3.0, 5.0);
        printRectangleArea(5.0, 1.5);

    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами "
                + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double a) {
        System.out.println("Площадь квадрата со стороной " + a +
                " = " + squareArea(a));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
