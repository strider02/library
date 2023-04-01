/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.Controller;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author admin
 */
public class Login extends JFrame {

    // Variables declaration
    private JPanel background;
    private JLabel window;
    private JLabel form;
    private JLabel profileIcon;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton submit;

    private JLabel errorUsername;
    private JLabel errorPassword;
    private JLabel error;
    private JLabel register;
    // End of variables declaration


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Creates new form Login
     */
    public Login() throws Exception {

        // start server
        backendControllers.Controller.getInstance().start();

        // setting view and starting server
        controllers.Controller.getInstance().connectToServer();

        // initialise components
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

//    public static void destroy() {
//        Login =  null;
//    }

    // todo: center align window vertical and horizontal
    private void initComponents() {

        background = new JPanel();
        window = new JLabel();
        form = new JLabel();
        profileIcon = new JLabel();
        usernameLabel = new JLabel();
        username = new JTextField();
        passwordLabel = new JLabel();
        password = new JPasswordField();
        submit = new JButton();

        errorUsername = new JLabel();
        errorPassword = new JLabel();
        error = new JLabel();

        register = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setSize(new Dimension(1920, 1080));
        setTitle("Java Core Swing GUI project - Login");

        // set size and layout
        background.setLayout(null);
        background.setSize(new java.awt.Dimension(1920, 1080));

        // avatar icon
        profileIcon.setIcon(new ImageIcon("App/src/storage/avatar80x80.png"));
        profileIcon.setHorizontalAlignment(SwingConstants.CENTER);
        profileIcon.setVerticalAlignment(SwingConstants.CENTER);
        profileIcon.setBounds(0, -180, 1920, 1080);
        background.add(profileIcon);

        // server validation error
        error.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        error.setForeground(new Color(153, 0, 51));
//        error.setText("There is no user with those credentials!");
        error.setHorizontalAlignment(SwingConstants.CENTER);
        error.setVerticalAlignment(SwingConstants.CENTER);
        error.setBounds(1920 / 2 - 230, 420, 460, 25);
        background.add(error);

        // username
        usernameLabel.setText("Username");
        usernameLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 50, 70, 23);
        background.add(usernameLabel);

        username.setBackground(new Color(255, 255, 255));
        username.setBounds(1920 / 2 - 50, 1080 / 2 - 50, 200, 23);
        background.add(username);

        errorUsername.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorUsername.setForeground(new Color(153, 0, 51));
//        errorUsername.setText("Username is required!");
        errorUsername.setBounds(1920 / 2 - 45, 1080 / 2 - 30, 200, 23);
        background.add(errorUsername);

        // password
        passwordLabel.setText("Password");
        passwordLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 10, 70, 23);
        background.add(passwordLabel);

        password.setBackground(new Color(255, 255, 255));
        password.setBounds(1920 / 2 - 50, 1080 / 2 - 10, 200, 23);
        background.add(password);

        errorPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorPassword.setForeground(new Color(153, 0, 51));
//        errorPassword.setText("Password is required!");
        errorPassword.setBounds(1920 / 2 - 45, 1080 / 2 + 10, 200, 23);
        background.add(errorPassword);

        // submit button action
        submit.setText("Login");
        submit.setBounds(1920 / 2 + 50, 1080 / 2 + 80, 100, 23);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        background.add(submit);

        // registration action
        register.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        register.setForeground(new Color(255, 255, 255));
        register.setText("Not registered yet? You should do it now. Click here!");
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerMouseClicked(evt);
            }
        });
        register.setBounds(1920 / 2 - 60, 670, 1920, 23);
        background.add(register);

        // card background image
        form.setIcon(new ImageIcon("App/src/storage/background_login480x400.jpg"));
        form.setHorizontalAlignment(SwingConstants.CENTER);
        form.setVerticalAlignment(SwingConstants.CENTER);
        form.setBounds(0, -70, 1920, 1080);
        background.add(form);

        // default background image
        window.setIcon(new ImageIcon("App/src/storage/background.jpg"));
        window.setBounds(0, 0, 1920, 1080);
        background.add(window);


        // creating group layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.PREFERRED_SIZE, 1080, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            String username = this.username.getText().trim();
            char[] password = this.password.getPassword();
//            String username = "milan";
//            char[] password = {'1', '2', '3'};

            // performing request validation
            if (!validateRequest(username, password))
                return;

            // preparing request data as parameters for further processing
            String passwd = new String(password);

            Map<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", passwd);

            // finding user with requested username and password
            User user = Controller.getInstance().getUser(params);

            if (user.getFirstname() != null) {
                this.dispose();
                Index.main(user);

            } else
                error.setText("There is no user with those credentials.");
            // execute code after xx seconds
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                this.error.setText("");
            });

        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registerMouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
        Register.main(null);
    }

    private boolean validateRequest(String username, char[] password) {

        boolean result = true;

        if (username == null | username.equals("")) {
            result = false;
            this.errorUsername.setText("Username is required!");
            this.username.grabFocus();
            this.username.requestFocus();

            // execute code after xx seconds
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                this.errorUsername.setText("");
            });
        }

        if (password.length == 0) {
            result = false;
            this.errorPassword.setText("Password is required!");
            this.password.grabFocus();
            this.password.requestFocus();

            // execute code after xx seconds
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                this.errorPassword.setText("");

            });
        }

        return result;

    }


}

