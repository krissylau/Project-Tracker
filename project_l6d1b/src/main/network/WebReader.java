package network;

import exceptions.NegativeInputException;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

public class WebReader {
    private URL url;
    private BufferedReader br;
    private String line;
    private StringBuilder stringBuilder;
    private ProjectList pl = new ProjectList();
    private Project project = new LocalProject("", "", pl);
    private ArrayList<Project> listOfProjects = new ArrayList<>();
    private Item item;
    private Location local = new Location();

    // MODIFIES: this
    // EFFECTS: returns arrayList of projects read from URL
    public ArrayList<Project> loadFromURL(String myUrl, ProjectList list) throws IOException {
        stringBuilder = new StringBuilder();
        try {
            extract(myUrl);
            String data = stringBuilder.toString();
            String[] dataArray = data.split("@", 1000);
            for (Integer i = 0; i < dataArray.length; i += 5) {
                if (dataArray[i + 4].equalsIgnoreCase("new project")) {
                    setProject(dataArray, i, list);
                } else {
                    setNewItem(dataArray, i);
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
            return listOfProjects;
        }

    }

    // MODIFIES: item, project
    // EFFECTS: constructs new Item and adds Item to project
    private void setNewItem(String[] dataArray, Integer i) {
        item = new Item(dataArray[i]);
        Integer amount = Integer.parseInt(dataArray[i + 1]);
        String arrived = dataArray[i + 2];
        try {
            item.setAmount(amount);
        } catch (NegativeInputException e) {
            e.printStackTrace();
        } finally {
            item.setArrived(arrived);
            project.addItem(item.getName(), item);
        }
    }

    // MODIFIES: list, project
    // EFFECTS: constructs new Project from URL data and adds Project to list
    private void setProject(String[] dataArray, Integer i, ProjectList list) {
        String location = dataArray[i + 3];
        String status = dataArray[i + 2];
        String date = dataArray[i + 1];
        String name = dataArray[i];
        if (local.isLocal(location)) {
            project = new LocalProject(name, location, list);
        } else {
            project = new AwayProject(name, location, list);
        }
        project.setProjectStatus(status);
        project.setStartDate(date);
        listOfProjects.add(project);
    }

    // MODIFIES: this
    // EFFECTS: appends strings from myURL to stringBuilder
    private void extract(String myUrl) throws IOException {
        url = new URL(myUrl);
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }
    }

}
