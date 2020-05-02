package ui;

import model.ProjectList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame implements ActionListener {
    JButton newProject;
    JButton pastProjects;
    JLabel welcome;
    private ProjectList list;

    // Welcome GUI frame
    public WelcomeFrame(ProjectList list) {
        this.list = list;
        welcome = new JLabel("Welcome to this project tracker!");
        welcome.setFont(new Font("Arial", Font.PLAIN, 30));

        newProject = new JButton("New Project");
        newProject.setFont(new Font("Arial", Font.PLAIN, 18));

        pastProjects = new JButton("See Past Projects");
        pastProjects.setFont(new Font("Arial", Font.PLAIN, 18));

        add(welcome);
        add(newProject);
        add(pastProjects);
        newProject.addActionListener(this); // ActionListener param that is an Interface
        pastProjects.addActionListener(this);
        setLayout(new FlowLayout());  // FlowLayout, GridLayout, Null layout
        setVisible(true);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newProject) {
            ProjectSetUpFrame setup = new ProjectSetUpFrame(list);
        }
        if (e.getSource() == pastProjects) {
            LoadFrame load = new LoadFrame(list);
        }
    }
}
