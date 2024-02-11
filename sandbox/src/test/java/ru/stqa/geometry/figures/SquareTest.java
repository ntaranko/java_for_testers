package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        double result  = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void
    canCalculatePerimeter(){
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }
}
