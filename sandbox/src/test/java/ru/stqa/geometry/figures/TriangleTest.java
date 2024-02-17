package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void canCalculatePerimeter() {
        var triangle = new Triangle(3.0, 4.0, 5.0);
        double perimeter = triangle.perimeter();
        Assertions.assertEquals(12.0, perimeter);
    }

    @Test
    void canCalculateArea() {
        var triangle = new Triangle(3.0, 4.0, 5.0);
        double area = triangle.area();
        Assertions.assertEquals(6.0, area);
    }
}