package model;

public class LocalProject extends Project {

    // constructs project whose location is in Vancouver, Burnaby, or Richmond
    public LocalProject(String name, String location, ProjectList projectList) {
        super(name, location, projectList);
    }

    // EFFECTS: prints message about preparation time
    @Override
    public String print() {
        return ("This project does not require additional preparation time.");
    }
}
