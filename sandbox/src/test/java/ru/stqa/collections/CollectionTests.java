package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests() {
        var array = new String[]{"a", "b", "c"};
        array[0] = "a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTest() {

        var list = new ArrayList<String>(List.of("a", "b", "c", "a"));
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void setTest() {
        var set = new HashSet(List.of("a", "b", "c", "a"));
        var element = set.stream().findAny().get();
        set.add("d");
        Assertions.assertEquals(4, set.size());
    }

    @Test
    void testMap() {
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
    }
}
