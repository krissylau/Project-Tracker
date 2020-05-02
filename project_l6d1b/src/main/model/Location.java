package model;

public class Location {

    // EFFECTS: returns true if location is local
    public boolean isLocal(String location) {
        if (location.equalsIgnoreCase("Vancouver") || location.equalsIgnoreCase("Burnaby")
                || location.equalsIgnoreCase("Richmond")) {
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: p
    // EFFECTS: sets p as new AwayProject or LocalProject based on where the location is
    public Project setNewProject(String location, String name, ProjectList projectList) {
        Project p;
        if (!isLocal(location)) {
            p = new AwayProject(name, location, projectList);
        } else {
            p = new LocalProject(name, location, projectList);
        }
        return p;
    }
}
