package ui;

import model.Item;
import model.Project;
import model.ProjectList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class LoadFrame extends JFrame implements ActionListener {
    private JButton close;
    private JButton editProject;
    private Project p1;
    private ProjectList list;

    // GUI frame for loading list
    public LoadFrame(ProjectList list) {
        this.list = list;
        String finalString = "";
        JTextArea finalLabel = new JTextArea(40, 40);
        JScrollPane scroll = new JScrollPane(finalLabel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        for (Observable p: this.list.getList()) {
            String project = "Project: ";
            p1 = (Project) p;
            String client = "\nClient: " + p1.getName();
            String location = "\nLocation: " + p1.getLocation();
            String date = "\nStart Date: " + p1.getStartDate();
            String pstatus;
            if (p1.getProjectStatus()) {
                pstatus = "complete";
            } else {
                pstatus = "incomplete";
            }
            String status = "\nCompletion Status: " + pstatus;
            String message = "\n" + p1.print();

            String items = "\nItems: ";
            String itemData = "";

            for (String str: p1.getShipments().keySet()) {
                Item i = p1.getShipments().get(str);
                itemData += "\n" + i.printData();
            }
            finalString += project + client + location + date + status + message + items + itemData  + "\n\n";
        }
        finalLabel.setText(finalString);
        finalLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        add(scroll);

        editProject = new JButton("Edit Project");
        editProject.setFont(new Font("Arial", Font.PLAIN, 18));
        add(editProject);
        editProject.addActionListener(this);

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));
        add(close);
        close.addActionListener(this);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(850,1000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            dispose();
        }
        if (e.getSource() == editProject) {
            dispose();
            EditProjectFrame projectEdit = new EditProjectFrame(list);
        }
    }
}
