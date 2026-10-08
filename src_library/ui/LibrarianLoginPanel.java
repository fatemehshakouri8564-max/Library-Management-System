package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Librarian;
import service.LibrarySystem;

public class LibrarianLoginPanel extends JPanel {

    private MainFrame mainFrame;
    private LibrarySystem system;

    private JTextField personnelIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    public LibrarianLoginPanel(MainFrame mainFrame, LibrarySystem system) {

        this.mainFrame = mainFrame;
        this.system = system;

        setLayout(new GridLayout(3, 2));

        backButton = new JButton("Back");

        personnelIdField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Personnel ID:"));
        add(personnelIdField);

        add(new JLabel("Password:"));
        add(passwordField);

        add(backButton);
        add(loginButton);

        backButton.addActionListener(e -> goBack());
        loginButton.addActionListener(e -> login());
    }

    private void login() {

        String personnelId = personnelIdField.getText();
        String password = new String(passwordField.getPassword());

        try {

            Librarian l = system.loginLibrarian(personnelId, password);
            mainFrame.setCurrentLibrarian(l);

            JOptionPane.showMessageDialog(this, "Login Successful");

            mainFrame.showCard(MainFrame.LIBRARIAN_MENU);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        mainFrame.showCard(MainFrame.WELCOME);
    }
}
