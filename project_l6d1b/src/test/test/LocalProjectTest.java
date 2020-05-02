package test;

import model.LocalProject;
import exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocalProjectTest extends ProjectTest {

    @BeforeEach
    public void runBefore() {
        p = new LocalProject("Bob", "Vancouver", list);
        p1 = new LocalProject("Bob", "Vancouver", list);
        try {
            i1.setAmount(2);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        try {
            i2.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Bob", p.getName());
        assertEquals("Vancouver", p.getLocation());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Vancouver", p.getLocation());
    }

    @Test
    public void testPrint() {
        assertEquals("This project does not require additional preparation time.", p.print());
    }

}
