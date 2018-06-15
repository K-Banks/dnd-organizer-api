package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClassTest {

    @Test
    public void createClass() {
        Class newClass = new Class ("test", "test");
        assertTrue(newClass instanceof Class);
    }

    @Test
    public void testGetterAndSetterForName() {
        Class newClass = new Class ("test", "test");
        newClass.setName("newName");
        assertNotEquals("true", newClass.getName());
    }

    @Test
    public void testGetterAndSetterForDescription() {
        Class newClass = new Class ("test", "test");
        newClass.setDescription("this is a new description");
        assertNotEquals("test", newClass.getDescription());
    }

    @Test
    public void testGetterAndSetterForId() {
        Class newClass = new Class ("test", "test");
        newClass.setId(3);
        assertNotEquals(null, newClass.getId());
    }
}