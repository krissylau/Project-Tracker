package test;

import exceptions.NegativeInputException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProjectListTest {
    private ProjectList projectList;
    private Project p;
    private Project p1;
    private Item i1;
    private Item i2;

    @BeforeEach
    public void runBefore() {
        projectList = new ProjectList();
        i1 = new Item("Computers");
        try {
            i1.setAmount(20);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        i2 = new Item("Keyboards");
        try {
            i2.setAmount(10);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        i2.setArrived("true");
        p = new LocalProject("Sam", "Vancouver", projectList);
        p.setStartDate("Sept. 2, 2019");
        p.setProjectStatus("false");
        p.addItem(i1.getName(), i1);
        p1 = new AwayProject("Jim", "Surrey", projectList);
        p1.setStartDate("Oct. 30, 2019");
        p1.setProjectStatus("true");
        p1.addItem(i1.getName(), i1);
        p1.addItem(i2.getName(), i2);
    }

    @Test
    public void testGetList() {
        ArrayList<Project> projects = new ArrayList<>();
        assertEquals(projects, projectList.getList());
    }

    @Test
    public void testSave() throws IOException {
        projectList.getList().add(p);
        projectList.getList().add(p1);
        projectList.save();
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        assertEquals("Projects: ", lines.get(0));
        assertEquals("Sam  Sept. 2, 2019  false  Vancouver  new project", lines.get(1));
        assertEquals("Computers  20  false  .  .", lines.get(2));
        assertEquals("Jim  Oct. 30, 2019  true  Surrey  new project", lines.get(3));
        assertEquals("Computers  20  false  .  .", lines.get(4));
        assertEquals("Keyboards  10  true  .  .", lines.get(5));
    }

    @Test
    public void testLoad() throws IOException {
        projectList.getList().add(p);
        projectList.getList().add(p1);
        projectList.save();
        ProjectList test = new ProjectList();
        test.load();
        assertEquals(p, test.getList().get(0));
        Map<String, Item> testShip = new HashMap<>();
        testShip.put(i1.getName(), i1);
        assertEquals(testShip, p.getShipments());
        assertEquals(p1, test.getList().get(1));
        Map<String, Item> testShip2 = new HashMap<>();
        testShip2.put(i1.getName(), i1);
        testShip2.put(i2.getName(), i2);
        assertEquals(testShip2, p1.getShipments());
        assertEquals(2, test.getList().size());
    }

    @Test
    public void testSetNewItem() {
        ArrayList<String> partsOfLine = new ArrayList<>();
        partsOfLine.addAll(Arrays.asList("Computers", "-3", "false", ".", "."));
//        partsOfLine.add("Computers");
//        partsOfLine.add("-3");
//        partsOfLine.add("false");
//        partsOfLine.add(".");
//        partsOfLine.add(".");
        try {
            Item test = projectList.setNewItem(partsOfLine);
            test.setAmount(-3);
        } catch (NegativeInputException e) {
            // expected
        }
    }


}
