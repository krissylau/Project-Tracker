package test;

import model.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    private Printer printer;

    @BeforeEach
    public void runBefore() {
        printer = new Printer();
    }

    @Test
    public void testPrint() {
        assertEquals("prefix suffix", printer.print("prefix", "suffix"));
    }
}
