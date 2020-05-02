package ui;

import model.Project;
import model.ProjectList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddedMessageFrame extends JFrame implements ActionListener {
    private JButton closeAndAddItems;
    private JButton close;
    private Project p1;
    private ProjectList list;

    // MODIFIES: this
    // EFFECTS: GUI frame with message for adding p to list
    public AddedMessageFrame(String message, String message1, String message2, Project p, ProjectList list) {
        p1 = p;
        this.list = list;
        JLabel l1 = new JLabel("This " + message + " has been added to " + message1);
        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(l1);

        if (message.equalsIgnoreCase("project")) {
            closeAndAddItems = new JButton("Close and add " + message2);
            closeAndAddItems.setFont(new Font("Arial", Font.PLAIN, 18));
            closeAndAddItems.addActionListener(this);
            add(closeAndAddItems);
        }

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));
        close.addActionListener(this);
        add(close);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,250);
    }

    // GUI frame for message that parameter change has been changed to parameter message
    public AddedMessageFrame(String change, String message) {
        JLabel l1 = new JLabel("The " + change + " has been changed to: " + message);
        l1.setFont(new Font("Arial", Font.PLAIN, 18));

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));

        close.addActionListener(this);

        add(l1);
        add(close);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,200);
    }

    // buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeAndAddItems) {
            dispose();
            ItemSetUpFrame itemSetUp = new ItemSetUpFrame(p1, list);

        }
        if (e.getSource() == close) {
            dispose();
        }
    }
}
