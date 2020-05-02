package test;

import model.AwayProject;
import exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AwayProjectTest extends ProjectTest {

    @BeforeEach
    public void runBefore() {
        p = new AwayProject("Bob", "Surrey", list);
        p1 = new AwayProject("Bob", "Surrey", list);
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
        assertEquals("Surrey", p.getLocation());
    }

    @Test
    public void testGetLocation() {
        assertEquals("Surrey", p.getLocation());
    }

    @Test
    public void testPrint() {
        assertEquals("This project requires additional time to prepare for due to its location.", p.print());
    }

    @Test
    public void testPrintData() {
        p.setStartDate("Sept. 2, 2019");
        assertEquals("Client: BobStart Date: Sept. 2, 2019Location: SurreyCompletion Status: Incomplete", p.printData());
        p.setProjectStatus("true");
        assertEquals("Client: BobStart Date: Sept. 2, 2019Location: SurreyCompletion Status: Complete", p.printData());
    }

}
