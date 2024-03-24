package ru.stqa.geometry.figures;

public record Rectangle(
        double a,
        double b) {

    public Rectangle {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
    }

    public static void printArea(double a, double b) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f",
                a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(this.a, rectangle.a) == 0 && Double.compare(this.b, rectangle.b) == 0)
                ||(Double.compare(this.b, rectangle.a) == 0 && Double.compare(this.a, rectangle.b) == 0) ;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
