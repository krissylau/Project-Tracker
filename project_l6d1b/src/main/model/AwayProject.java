package model;

public class AwayProject extends Project {

    // constructs a project whose location is not in Vancouver, Burnaby, or Richmond
    public AwayProject(String name, String location, ProjectList projectList) {
        super(name, location, projectList);
    }

    // EFFECTS: returns string about preparation time
    @Override
    public String print() {
        return ("This project requires additional time to prepare for due to its location.");
    }
}
