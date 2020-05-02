package model;

import exceptions.NegativeInputException;
import interfaces.Load;
import interfaces.Save;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProjectList implements Save, Load, Observer {
    private ArrayList<Observable> projects = new ArrayList<>();
    private Location local = new Location();

    // returns list of observables
    public ArrayList<Observable> getList() {
        return projects;
    }

    // MODIFIES: input.txt
    // EFFECTS: saves project information to file "input.txt" in UTF-8 format
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("past.txt", "UTF-8");
        writer.println("Projects: ");
        for (Observable o : projects) {
            Project p = (Project) o;
            String client = p.getName();
            String startDate = p.getStartDate();
            String status = Boolean.toString(p.getProjectStatus());
            String location = p.getLocation();
            writer.println(client + "  " + startDate + "  " + status + "  " + location + "  " + "new project");
            for (String i : p.getShipments().keySet()) {
                String item = p.getShipments().get(i).getName();
                String amount = Integer.toString(p.getShipments().get(i).getAmount());
                String arrived = Boolean.toString(p.getShipments().get(i).getArrived());
                String st = item + "  " + amount + "  " + arrived + "  " + "." + "  " + ".";
                writer.println(st);
            }
        }
        writer.close();
    }


    // MODIFIES: this
    // EFFECTS: reads from "input.txt" and creates project list from file data
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("past.txt"));
        lines.remove(0);
        Project p = new LocalProject("", "", this);
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            String name = partsOfLine.get(0);
            String location = partsOfLine.get(3);
            if (partsOfLine.get(4).equalsIgnoreCase("new project")) {
                p = local.setNewProject(location, name, this);
                p.setStartDate(partsOfLine.get(1));
                p.setProjectStatus(partsOfLine.get(2));
                projects.add(p);
            } else {
                Item item = setNewItem(partsOfLine);
                p.addItem(partsOfLine.get(0), item);
            }
        }
    }

    // MODIFIES: item
    // EFFECTS: sets new Item based on array and returns the Item
    public Item setNewItem(ArrayList<String> partsOfLine) {
        Item item = new Item(partsOfLine.get(0));
        try {
            item.setAmount(Integer.parseInt(partsOfLine.get(1)));
        } catch (NegativeInputException e) {
            e.printStackTrace();
        }
        item.setArrived(partsOfLine.get(2));
        return item;
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("  ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    @Override
    public void update(Observable o, Object arg) {
        Project project = (Project) o;
        System.out.println("\tAn item has been added to " + project.getName() + "'s project.");
    }
}
