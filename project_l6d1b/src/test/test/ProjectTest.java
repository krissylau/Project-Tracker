package test;

import model.Item;
import model.Project;
import exceptions.UnexpectedInputException;
import model.ProjectList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    protected Project p;
    protected Project p1;
    protected Item i1 = new Item("Computers");

    protected Item i2 = new Item("Keyboards");

    protected Map<String, Item> test = new HashMap<>();

    protected ProjectList list = new ProjectList();

    @Test
    public void testGetClientName() {
        assertEquals("Bob", p.getName());
    }

    @Test
    public void testSetGetStartDate() {
        p.setStartDate("Sept. 2, 2019");
        assertEquals("Sept. 2, 2019", p.getStartDate());
    }

    @Test
    public void testSetGetProjectStatus() {
        p.setProjectStatus("true");
        assertTrue(p.getProjectStatus());
        p.setProjectStatus("false");
        assertFalse(p.getProjectStatus());
    }

    @Test
    public void testGetShipments() {
        test.put(i1.getName(), i1);
        p.getShipments().put(i1.getName(), i1);
        assertEquals(test, p.getShipments());
    }

    @Test
    public void testAddItem() {
        p.addItem(i1.getName(), i1);
        assertEquals(i1, p.getShipments().get(i1.getName()));
        assertEquals(1, p.getShipments().size());
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(p);
        p.addItem(i1.getName(), i1);
        assertEquals(1, p.getShipments().size());
    }

    @Test
    public void testMarkComplete() {
        p.addItem(i1.getName(), i1);
        p.addItem(i2.getName(), i2);
        try {
            p.markComplete("yes");
            assertTrue(p.getProjectStatus());
        } catch (UnexpectedInputException e) {
            fail("No exception should be thrown");
        }

        p.setProjectStatus("false");
        try {
            p.markComplete("no");
            assertFalse(p.getProjectStatus());
        } catch (UnexpectedInputException e) {
            fail("No exception should be thrown.");
        }
        try {
            p.markComplete("fsd");
            fail("Unexpected input exception should have been thrown.");
        } catch (UnexpectedInputException unexpectedInputException) {
            //expected
        }
        assertFalse(p.getProjectStatus());
    }

    @Test
    public void testEquals() {
        assertTrue(p.equals(p));
        assertFalse(p.equals(null));
        assertFalse(p.equals(i1));
        p1.setProjectStatus("true");
        p1.setStartDate("Sept. 2, 2019");
        p.setProjectStatus("true");
        p.setStartDate("Sept. 2, 2019");
        assertTrue(p.equals(p1));
        p1.setStartDate("Sept. 3, 2019");
        assertFalse(p.equals(p1));
        p1.setProjectStatus("true");
        assertFalse(p.equals(p1));
        p1.setProjectName("Joe");
        assertFalse(p.equals(p1));
        p1.setLocation("Abbotsford");
        assertFalse(p.equals(p1));
    }

    @Test
    public void testHashcode() {
        assertEquals(p1.hashCode(), p.hashCode());
    }
}
