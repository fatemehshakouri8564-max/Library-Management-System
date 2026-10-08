package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WelcomePanel extends JPanel {

    private JButton studentLogin;
    private JButton librarianLogin;
    private JButton studentRegister;
    private JButton librarianRegister;
    private JLabel welcome;
    private JLabel welcome2;

    public WelcomePanel(MainFrame frame) {

 
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        welcome = new JLabel("به سیستم مدیریت کتابخانه خوش آمدید");
        welcome.setFont(new Font("Tahoma", Font.BOLD, 24));
        welcome.setForeground(new Color(40, 40, 40));
        welcome.setAlignmentX(CENTER_ALIGNMENT);

        welcome2 = new JLabel("ثبت نام کنید یا وارد شوید");
        welcome2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        welcome2.setForeground(new Color(90, 90, 90));
        welcome2.setAlignmentX(CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(welcome);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(welcome2);
        headerPanel.add(Box.createVerticalStrut(20));

        add(headerPanel, BorderLayout.NORTH);

       
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        centerWrapper.setOpaque(false);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(420, 160));

        // Student buttons
        studentLogin = createStyledButton("ورود دانشجو");
        studentLogin.addActionListener(e -> {
            frame.showCard(MainFrame.STUDENT_LOGIN);
        });

        studentRegister = createStyledButton("ثبت نام دانشجو");
        studentRegister.addActionListener(e -> {
            frame.showCard(MainFrame.STUDENT_REGISTER);
        });

        // Librarian buttons
        librarianLogin = createStyledButton("ورود کتابدار");
        librarianLogin.addActionListener(e -> {
            frame.showCard(MainFrame.LIBRARIAN_LOGIN);
        });

        librarianRegister = createStyledButton("ثبت نام کتابدار");
        librarianRegister.addActionListener(e -> {
            frame.showCard(MainFrame.LIBRARIAN_REGISTER);
        });

        buttonPanel.add(studentLogin);
        buttonPanel.add(studentRegister);
        buttonPanel.add(librarianLogin);
        buttonPanel.add(librarianRegister);

        centerWrapper.add(buttonPanel);

        add(centerWrapper, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.setPreferredSize(new Dimension(180, 55));
        return button;
    }
}
