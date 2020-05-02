package ui;

import exceptions.NegativeInputException;
import exceptions.UnexpectedInputException;
import model.Item;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemFrame extends JFrame implements ActionListener {
    private Project p1;
    private JButton close;
    private Boolean signal = true;

    //search item frame
    private JTextField itemSearch;
    private JButton itemSearchButton;

    //Edit Item frame
    private Item i1;
    private JTextField amountField;
    private JTextField itemStatusField;
    private JButton editAmount;
    private JButton editItemStatus;

    // GUI frame for searching for an item in Project p
    public EditItemFrame(Project p) {
        p1 = p;
        JLabel items = new JLabel("Items: ");
        items.setFont(new Font("Arial", Font.PLAIN, 18));
        add(items);

        for (String s : p1.getShipments().keySet()) {
            Item item = p1.getShipments().get(s);
            JLabel i = new JLabel(item.printData());
            i.setFont(new Font("Arial", Font.PLAIN, 18));
            add(i);
        }

        JLabel itemNameSearch = new JLabel("Please enter in the item you wish to edit: ");
        itemNameSearch.setFont(new Font("Arial", Font.PLAIN, 18));

        itemSearch = new JTextField(15);
        itemSearch.setFont(new Font("Arial", Font.PLAIN, 18));

        itemSearchButton = new JButton("Search");
        itemSearchButton.setFont(new Font("Arial", Font.PLAIN, 18));

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));

        itemSearchButton.addActionListener(this);
        close.addActionListener(this);

        add(itemNameSearch);
        add(itemSearch);
        add(itemSearchButton);
        add(close);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800,140);
    }

    // GUI frame to edit item
    public EditItemFrame(Item item) {
        i1 = item;
        JLabel itemName = new JLabel("Item: " + item.getName());
        itemName.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel itemAmount = new JLabel("Amount: " + item.getAmount());
        itemAmount.setFont(new Font("Arial", Font.PLAIN, 18));

        String status;
        if (item.getArrived()) {
            status = "Arrived";
        } else {
            status = "Shipping";
        }
        JLabel itemStatus = new JLabel("Arrival Status: " + status);
        itemStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel amountMessage = new JLabel("Please enter in a number");
        amountMessage.setFont(new Font("Arial", Font.PLAIN, 18));

        amountField = new JTextField(15);
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));

        editAmount = new JButton("Change Amount");
        editAmount.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel statusMessage = new JLabel("Please enter yes for arrived, no otherwise");
        statusMessage.setFont(new Font("Arial", Font.PLAIN, 18));

        itemStatusField = new JTextField(15);
        itemStatusField.setFont(new Font("Arial", Font.PLAIN, 18));

        editItemStatus = new JButton("Change Arrival Status");
        editItemStatus.setFont(new Font("Arial", Font.PLAIN, 18));

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 18));

        editAmount.addActionListener(this);
        editItemStatus.addActionListener(this);
        close.addActionListener(this);

        add(itemName);
        add(itemAmount);
        add(itemStatus);
        add(amountMessage);
        add(amountField);
        add(editAmount);
        add(statusMessage);
        add(itemStatusField);
        add(editItemStatus);
        add(close);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            dispose();
        }
        if (e.getSource() == itemSearchButton) {
            searchItem();
        }
        try {
            editItemButtons(e);
        } catch (NumberFormatException nfe) {
            BadInputFrame notNum = new BadInputFrame("a number (0, 1, 2...)", "change amount");
        } catch (NegativeInputException ex) {
            BadInputFrame negNum = new BadInputFrame("a positive number", "change amount");
        } catch (UnexpectedInputException uie) {
            BadInputFrame notYesNo = new BadInputFrame("yes or no", "change status");
        }
    }

    private void editItemButtons(ActionEvent e) throws NegativeInputException, UnexpectedInputException {
        if (e.getSource() == editAmount) {
            int amount = Integer.parseInt(amountField.getText());
            i1.setAmount(amount);
            AddedMessageFrame changeAmount = new AddedMessageFrame("amount", Integer.toString(amount));
        }
        if (e.getSource() == editItemStatus) {
            String status = itemStatusField.getText();
            if (!status.equalsIgnoreCase("yes") && !status.equalsIgnoreCase("no")) {
                throw new UnexpectedInputException();
            }
            if (status.equalsIgnoreCase("yes")) {
                i1.setArrived("true");
                AddedMessageFrame changeStatus = new AddedMessageFrame("status", "arrived");
            } else {
                i1.setArrived("false");
                AddedMessageFrame changeStatus = new AddedMessageFrame("status", "shipping");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: searches p1 for item that matches itemSearch textField input, constructs new BadInputFrame if not found
    private void searchItem() {
        for (String s : p1.getShipments().keySet()) {
            Item item = p1.getShipments().get(s);
            if (s.equalsIgnoreCase(itemSearch.getText())) {
                signal = false;
                EditItemFrame editItem = new EditItemFrame(item);
                dispose();
                break;
            }
        }
        if (signal) {
            BadInputFrame notFound = new BadInputFrame("a different name", "search");
        }
    }
}
