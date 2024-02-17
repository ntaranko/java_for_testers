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

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            var triangle = new Triangle(1.0, -2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithSides() {
        try {
            var triangle = new Triangle(6.0, 2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            //ok
        }
    }

    @Test
    void testEquality(){
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(3.0, 4.0, 2.0);
        Assertions.assertEquals(t1, t2);
    }
}
