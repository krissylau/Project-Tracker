package ui;

import model.*;
import network.WebReader;

import java.io.IOException;

public class ProjectTracker {
    private ProjectList list = new ProjectList();
    private WebReader listWebReader = new WebReader();


    // EFFECTS: call projectListLoading() and construct WelcomeFrame
    private void start() throws IOException {
        projectListLoading();
        WelcomeFrame welcomeFrame = new WelcomeFrame(list);
        list.save();
    }

    // MODIFIES: list
    // EFFECTS: loads projectList from file and url
    private void projectListLoading() throws IOException {
        list.load();
        String myUrl = "load.html";
        for (Project p: listWebReader.loadFromURL(myUrl, list)) {
            if (!list.getList().contains(p)) {
                list.getList().add(p);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ProjectTracker pt = new ProjectTracker();
        pt.start();
    }
}