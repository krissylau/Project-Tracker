package test;

import model.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location local = new Location();

    @Test
    public void testLocal() {
        assertFalse(local.isLocal("hawaii"));
        assertTrue(local.isLocal("Vancouver"));
        assertTrue(local.isLocal("Richmond"));
        assertTrue(local.isLocal("Burnaby"));
    }
}
