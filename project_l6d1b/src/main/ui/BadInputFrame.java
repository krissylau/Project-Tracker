package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BadInputFrame extends JFrame implements ActionListener {

    // GUI frame with error message
    public BadInputFrame(String input, String button) {
        JLabel errorMessage = new JLabel("Please enter " + input + " then press the " + button + " button again. ");
        errorMessage.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton ok = new JButton("OK");
        ok.setFont(new Font("Arial", Font.PLAIN, 18));

        add(errorMessage);
        add(ok);

        ok.addActionListener(this);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(600,120);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
