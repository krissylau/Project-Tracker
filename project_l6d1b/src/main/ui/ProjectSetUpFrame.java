package ui;

import exceptions.UnexpectedInputException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ProjectSetUpFrame extends JFrame implements ActionListener {
    private Location local = new Location();
    private JButton addProject;
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    private JButton close;
    private ProjectList list = new ProjectList();
    private Project p1 = new LocalProject("", "", list);

    // GUI frame to set up new Project
    public ProjectSetUpFrame(ProjectList list) {
        this.list = list;
        JLabel clientName = new JLabel("Enter new client name: ");
        clientName.setFont(new Font("Arial", Font.PLAIN, 18));
        t1 = new JTextField(15);
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel location = new JLabel("Enter city client is located in: ");
        location.setFont(new Font("Arial", Font.PLAIN, 18));
        t2 = new JTextField(15);
        t2.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel date = new JLabel("Enter project start date: ");
        date.setFont(new Font("Arial", Font.PLAIN, 18));
        t3 = new JTextField(15);
        t3.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel complete = new JLabel("Is the project complete? (yes or no) ");
        complete.setFont(new Font("Arial", Font.PLAIN, 18));
        t4 = new JTextField(15);
        t4.setFont(new Font("Arial", Font.PLAIN, 18));
        addProject = new JButton("Add Project");
        addProject.setFont(new Font("Arial", Font.PLAIN, 18));
        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));


        add(clientName);
        add(t1);
        add(location);
        add(t2);
        add(date);
        add(t3);
        add(complete);
        add(t4);
        add(addProject);
        add(close);

        addProject.addActionListener(this);
        close.addActionListener(this);

        setLayout(new FlowLayout());  // FlowLayout, GridLayout, Null layout
        setVisible(true);
        setSize(325, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addProject) {
                projectSetUp();
                dispose();
            }
        } catch (UnexpectedInputException ex) {
            BadInputFrame notYesNo = new BadInputFrame("yes or no", "add project");
        }
        if (e.getSource() == close) {
            dispose();
        }
        try {
            list.save();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    // MODIFIES: p1, list
    // EFFECTS: constructs new project from user input
    private void projectSetUp() throws UnexpectedInputException {
        String name = t1.getText();
        String location = t2.getText();
        String date = t3.getText();
        String status = t4.getText();
        if (!status.equalsIgnoreCase("yes") && !status.equalsIgnoreCase("no")) {
            throw new UnexpectedInputException();
        }
        p1 = local.setNewProject(location, name, list);
        p1.setStartDate(date);
        if (status.equalsIgnoreCase("yes")) {
            p1.setProjectStatus("true");
        }
        list.getList().add(p1);
        AddedMessageFrame addedProject = new AddedMessageFrame("project", "the system.", "items", p1, list);
    }
}


