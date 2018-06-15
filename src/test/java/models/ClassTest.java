package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClassTest {

    @Test
    public void createClass() {
        Class newClass = new Class ("test", "test");
        assertTrue(newClass instanceof Class);
    }
}