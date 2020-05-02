package ui;

import exceptions.NegativeInputException;
import exceptions.UnexpectedInputException;
import model.Item;
import model.Project;
import model.ProjectList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ItemSetUpFrame extends JFrame implements ActionListener {
    private Project p1;
    private ProjectList list;
    private JButton addItem;
    private JButton close;
    private JTextField name;
    private JTextField amount;
    private JTextField status;
    private Integer itemAmount = 0;

    // GUI frame for adding items to Project p
    public ItemSetUpFrame(Project p, ProjectList list) {
        p1 = p;
        this.list = list;
        JLabel message = new JLabel("Enter items to add into this project. ");
        message.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel instructions = new JLabel("Enter a number for amount");
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel instructions1 = new JLabel("Enter yes/no for arrived/shipping");
        instructions1.setFont(new Font("Arial", Font.PLAIN, 18));
        name = new JTextField(15);
        name.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel l1 = new JLabel("Item Name: ");
        l1.setFont(new Font("Arial", Font.PLAIN, 18));
        amount = new JTextField(15);
        amount.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel l2 = new JLabel("Item Amount: ");
        l2.setFont(new Font("Arial", Font.PLAIN, 18));
        status = new JTextField(15);
        status.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel l3 = new JLabel("Item Arrival Status: ");
        l3.setFont(new Font("Arial", Font.PLAIN, 18));
        addItem = new JButton("Add Item");
        addItem.setFont(new Font("Arial", Font.PLAIN, 18));
        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));

        add(message);
        add(instructions);
        add(instructions1);
        add(l1);
        add(name);
        add(l2);
        add(amount);
        add(l3);
        add(status);
        add(addItem);
        add(close);

        addItem.addActionListener(this);
        close.addActionListener(this);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(310,400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            setItem(e);
        } catch (NumberFormatException nfe) {
            BadInputFrame notNum = new BadInputFrame("a number (0, 1, 2...)", "add item");
        } catch (NegativeInputException ex) {
            BadInputFrame negNum = new BadInputFrame("a positive number", "add item");
        } catch (UnexpectedInputException uie) {
            BadInputFrame notYesNo = new BadInputFrame("yes or no", "add item");
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

    private void setItem(ActionEvent e) throws NegativeInputException, UnexpectedInputException {
        if (e.getSource() == addItem) {
            String name = this.name.getText();
            itemAmount = Integer.parseInt(this.amount.getText());
            Item item = new Item(name);
            item.setAmount(itemAmount);
            String status = this.status.getText();
            if (!status.equalsIgnoreCase("yes") && !status.equalsIgnoreCase("no")) {
                throw new UnexpectedInputException();
            }
            if (status.equalsIgnoreCase("yes")) {
                item.setArrived("true");
            }
            p1.addItem(name, item);
            dispose();
            ItemSetUpFrame nextItem = new ItemSetUpFrame(p1, list);
            AddedMessageFrame added = new AddedMessageFrame("item", "this project.", "", p1, list);
        }
    }
}
