package test;

import model.Item;
import model.LocalProject;
import exceptions.NegativeInputException;
import model.Project;
import model.ProjectList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;
    private Project project;
    private ProjectList list = new ProjectList();

    @BeforeEach
    public void runBefore() {
        item = new Item("Computers");
        try {
            item.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        project = new LocalProject("Sue", "Vancouver", list);
    }

    @Test
    public void testConstructor() {
        assertEquals("Computers", item.getName());
        assertEquals(10, item.getAmount());
        Item test = new Item("Computers");
        assertEquals(0, test.getAmount());
    }

    @Test
    public void testGetItem() {
        assertEquals("Computers", item.getName());
    }

    @Test
    public void testSetAmount() {
        try {
            item.setAmount(10);
            assertEquals(10, item.getAmount());
        } catch (NegativeInputException e) {
            fail("No exception should have been thrown.");
        }
        try {
            item.setAmount(-2);
            fail("Negative input exception should have been thrown.");
        } catch (NegativeInputException e) {
            assertEquals(10, item.getAmount());
        }
    }
    @Test
    public void testGetAmount() {
        assertEquals(10, item.getAmount());
    }

    @Test
    public void testSetGetArrived() {
        item.setArrived("true");
        assertTrue(item.getArrived());
        item.setArrived("false");
        assertFalse(item.getArrived());
        item.setArrived("fjklds");
        assertFalse(item.getArrived());
    }

    @Test
    public void testPrintData() {
        assertEquals(" 10 of Computers", item.printData());
    }

    @Test
    public void testGetProjects() {
        item.addProject(project);
        ArrayList<Project> list = new ArrayList<>();
        list.add(project);
        assertEquals(list, item.getProjects());
    }

    @Test
    public void testAddProject() {
        item.addProject(project);
        ArrayList<Project> list = new ArrayList<>();
        list.add(project);
        assertEquals(list, item.getProjects());
        item.addProject(project);
        assertEquals(list, item.getProjects());
    }

    @Test
    public void testEquals() {
        assertTrue(item.equals(item));
        assertFalse(item.equals(null));
        assertFalse(item.equals(project));
        Item test = new Item("Computers");
        try {
            test.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        assertTrue(item.equals(test)); // item and amount equal
        try {
            test.setAmount(20);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        assertFalse(item.equals(test));  // item, arrived equal, amount not equal
        test.setArrived("true");
        try {
            test.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        assertFalse(item.equals(test));  // item, amount equal, arrived not equal
        Item test1 = new Item("Keyboards");
        try {
            test1.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        assertFalse(test.equals(test1)); // amount, arrived equal, items not equal
        try {
            test1.setAmount(20);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        assertFalse(test.equals(test1)); // item equal, arrived and amount not equal
        try {
            test1.setAmount(20);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        test1.setArrived("true");
        assertFalse(test.equals(test1));
    }

    @Test
    public void testHashcode() {
        Item test = new Item("Computers");
        try {
            test.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        assertEquals(test.hashCode(), item.hashCode());
        test.setArrived("true");
        assertFalse(test.hashCode() == item.hashCode());
    }
}
