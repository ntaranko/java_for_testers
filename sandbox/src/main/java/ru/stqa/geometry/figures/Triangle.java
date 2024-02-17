package ru.stqa.geometry.figures;

public record Triangle(
        double side1,
        double side2,
        double side3) {


    public Triangle {
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ((side1 + side2 < side3)
                || (side1 + side3 < side2)
                || (side2 + side3 < side1)) {
            throw new IllegalArgumentException("Triangle with these sides cannot be created");
        }
    }

    public double area() {
        double p = this.perimeter() / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    public double perimeter() {
        return side1 + side2 + side3;
    }

}
