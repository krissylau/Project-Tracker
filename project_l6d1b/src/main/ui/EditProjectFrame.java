package ui;

import exceptions.UnexpectedInputException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Observable;

public class EditProjectFrame extends JFrame implements ActionListener {
    private ChangeStatus changeStatus = new ChangeStatus();

    //SearchProjectListFrame
    private Boolean signal = true;
    private Project p1;
    private ProjectList list = new ProjectList();
    private JLabel projectName;
    private JTextField projectNameField;
    private JButton search;
    private JButton close;

    //EditProjectFrame
    private JButton editDate;
    private JButton editStatus;
    private JButton addMoreItems;
    private JButton editItems;
    private JTextField dateField;
    private JTextField statusField;

    // GUI for searching for project in list
    public EditProjectFrame(ProjectList list) {
        this.list = list;
        projectName = new JLabel("Please enter the client's name: ");
        projectName.setFont(new Font("Arial", Font.PLAIN, 18));

        projectNameField = new JTextField(15);
        projectNameField.setFont(new Font("Arial", Font.PLAIN, 18));

        search = new JButton("Search");
        search.setFont(new Font("Arial", Font.PLAIN, 18));

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));

        search.addActionListener(this);
        close.addActionListener(this);

        add(projectName);
        add(projectNameField);
        add(search);
        add(close);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500,200);
    }

    // GUI frame for editing Project p
    public EditProjectFrame(Project p, ProjectList list) {
        p1 = p;
        this.list = list;
        JLabel clientName = new JLabel("Client: " + p1.getName());
        clientName.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel location = new JLabel("Location: " + p1.getLocation());
        location.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel startDate = new JLabel("Start Date: " + p1.getStartDate());
        startDate.setFont(new Font("Arial", Font.PLAIN, 18));

        dateField = new JTextField(15);
        dateField.setFont(new Font("Arial", Font.PLAIN, 18));

        editDate = new JButton("Change Date");
        editDate.setFont(new Font("Arial", Font.PLAIN, 18));

        String status;
        if (p1.getProjectStatus()) {
            status = "complete";
        } else {
            status = "incomplete";
        }
        JLabel projectStatus = new JLabel("Completion status: " + status);
        projectStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel statusMessage = new JLabel("Please enter yes for complete, no for incomplete");
        statusMessage.setFont(new Font("Arial", Font.PLAIN, 18));

        statusField = new JTextField(15);
        statusField.setFont(new Font("Arial", Font.PLAIN, 18));
        editStatus = new JButton("Change Status");
        editStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        editItems = new JButton("Edit Items");
        editItems.setFont(new Font("Arial", Font.PLAIN, 18));
        addMoreItems = new JButton("Add More Items");
        addMoreItems.setFont(new Font("Arial", Font.PLAIN, 18));
        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));

        editDate.addActionListener(this);
        editStatus.addActionListener(this);
        editItems.addActionListener(this);
        addMoreItems.addActionListener(this);
        close.addActionListener(this);

        add(clientName);
        add(location);
        add(startDate);
        add(projectStatus);
        JLabel items = new JLabel("Items: ");
        items.setFont(new Font("Arial", Font.PLAIN, 18));
        add(items);

        for (String s : p1.getShipments().keySet()) {
            Item item = p1.getShipments().get(s);
            JLabel i = new JLabel(item.printData());
            i.setFont(new Font("Arial", Font.PLAIN, 18));
            add(i);
        }

        add(dateField);
        add(editDate);
        add(statusMessage);
        add(statusField);
        add(editStatus);

        add(editItems);
        add(addMoreItems);
        add(close);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(430,400);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            search();
        }
        if (e.getSource() == close) {
            dispose();
        }
        if (e.getSource() == editDate) {
            p1.setStartDate(dateField.getText());
            AddedMessageFrame dateChange = new AddedMessageFrame("date", dateField.getText());
        }
        if (e.getSource() == editStatus) {
            changeStatus();
        }
        itemButtons(e);
        save();
    }

    private void save() {
        try {
            list.save();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    private void itemButtons(ActionEvent e) {
        if (e.getSource() == editItems) {
            dispose();
            EditItemFrame itemSearch = new EditItemFrame(p1);
        }
        if (e.getSource() == addMoreItems) {
            ItemSetUpFrame itemSetUp = new ItemSetUpFrame(p1, list);
        }
    }

    // MODIFIES: p1
    // EFFECTS: changes status of p1 to statusField input, constructs BadInputFrame if input incorrect
    private void changeStatus() {
        try {
            String status = statusField.getText();
            changeStatus.markComplete(p1, status);
        } catch (UnexpectedInputException uie) {
            BadInputFrame notYesNo = new BadInputFrame("yes or no", "change status");
        }
    }

    // MODIFIES: p1
    // EFFECTS: searches list for project with projectNameField input; constructs project editing frame if found;
    //          constructs BadInputFrame if project not found
    private void search() {
        for (Observable p : list.getList()) {
            p1 = (Project) p;
            if (p1.getName().equalsIgnoreCase(projectNameField.getText())) {
                signal = false;
                EditProjectFrame editProject = new EditProjectFrame(p1, list);
                //dispose();
                //break;
            }
        }
        if (signal) {
            BadInputFrame notFound = new BadInputFrame("a different name", "search");
        }
    }
}
