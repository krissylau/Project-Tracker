package model;


import exceptions.NegativeInputException;

import java.util.ArrayList;
import java.util.Objects;

public class Item {
    private String name;
    private int amount = 0;
    private boolean arrived = false;
    private ArrayList<Project> projects = new ArrayList<>();
    private Printer printer = new Printer();

    // Construct Item object with item and amount
    public Item(String name) {
        this.name = name;
    }

    // EFFECTS: returns name
    public String getName() {
        return this.name;
    }


    // MODIFIES: this
    // EFFECTS: sets amount of this needed; throws negative input exception if amount < 0
    public void setAmount(int amount) throws NegativeInputException {
        if (amount < 0) {
            throw new NegativeInputException();
        }
        this.amount = amount;
    }

    // EFFECTS: returns amount
    public int getAmount() {
        return this.amount;
    }

    // EFFECTS: returns arrived
    public boolean getArrived() {
        return this.arrived;
    }

    // MODIFIES: this
    // EFFECTS: sets arrived to true if item has arrived, false otherwise
    public void setArrived(String a) {
        if (a.equalsIgnoreCase("true")) {
            this.arrived = true;
        } else if (a.equalsIgnoreCase("false")) {
            this.arrived = false;
        }
    }

    // EFFECTS: returns project
    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    // MODIFIES: this
    // EFFECTS: sets project as p
    public void addProject(Project p) {
        if (!projects.contains(p)) {
            this.projects.add(p);
            p.addItem(this.name, this);
        }
    }

    // EFFECTS: returns string of form "itemName of itemAmount"
    public String printData() {
        String i = getName();
        Integer a = getAmount();
        return printer.print("", a + " of " + i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item1 = (Item) o;
        return amount == item1.amount && arrived == item1.arrived && Objects.equals(name, item1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, arrived);
    }
}
