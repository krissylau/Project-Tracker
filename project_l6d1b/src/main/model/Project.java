package model;

import exceptions.UnexpectedInputException;

import java.util.*;

public abstract class Project extends Observable {
    protected String name;
    protected String startDate;
    protected boolean projectStatus;
    protected Map<String, Item> shipments = new HashMap<>();
    protected String location;
    private ChangeStatus change = new ChangeStatus();
    private Printer printer = new Printer();

    // construct Project object with name, location, ProjectList
    public Project(String name, String location, ProjectList projectList) {
        this.name = name;
        this.location = location;
        this.projectStatus = false;
        addObserver(projectList);
    }

    // EFFECTS: returns client name
    public String getName() {
        return this.name;
    }

    // EFFECTS: sets project name as n
    public void setProjectName(String n) {
        this.name = n;
    }

    //EFFECTS: returns project start date
    public String getStartDate() {
        return this.startDate;
    }

    // MODIFIES: this
    // EFFECTS: sets project start date
    public void setStartDate(String d) {
        this.startDate = d;
    }

    //EFFECTS: returns project status
    public boolean getProjectStatus() {
        return this.projectStatus;
    }

    // MODIFIES: this
    // EFFECTS: sets project status as true if completed, false otherwise
    public void setProjectStatus(String s) {
        this.projectStatus = Boolean.parseBoolean(s);
    }

    // EFFECTS: gets project location
    public String getLocation() {
        return this.location;
    }

    // MODIFIES: this
    // EFFECTS: sets project location
    public void setLocation(String location) {
        this.location = location;
    }

    // EFFECTS: returns shipments
    public Map<String, Item> getShipments() {
        return this.shipments;
    }

    // MODIFIES: this
    // EFFECTS: adds item to shipments if shipment does not already contain the key
    public void addItem(String i, Item item) {
        if (!shipments.containsKey(i)) {
            this.shipments.put(i, item);
            item.addProject(this);
            setChanged();
            notifyObservers();
        }
    }

    // MODIFIES: this
    // EFFECTS: marks this project as complete and sets all items as arrived
    public void markComplete(String s) throws UnexpectedInputException {
        change.markComplete(this, s);
    }

    public abstract String print();

    // EFFECTS: prints project data
    public String printData() {
        String status;
        if (getProjectStatus()) {
            status = "Complete";
        } else {
            status = "Incomplete";
        }
        return (printer.print("Client:", getName()) + printer.print("Start Date:", getStartDate())
                + printer.print("Location:", getLocation()) + printer.print("Completion Status:", status));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(name, project.name) && Objects.equals(startDate, project.startDate)
                && Objects.equals(location, project.location); // projectStatus == project.projectStatus &&
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, projectStatus, location);
    }
}
