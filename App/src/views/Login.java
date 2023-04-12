package views;

import bootstrap.Helper;
import controllers.Controller;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author admin
 */
public class Login extends JFrame {

    // Variables declaration
    private JPanel panelMain;
    private JLabel backgroundImage, form, profileIcon, usernameLabel, passwordLabel, errorUsername, errorPassword, error, register;
    private JTextField username;
    private JPasswordField password;
    private JButton submit;


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

    private void initComponents() {

        panelMain = new JPanel();
        backgroundImage = new JLabel();
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
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("../storage/icons/favicon.png")));

        // set size and layout
        panelMain.setLayout(null);
        panelMain.setSize(new java.awt.Dimension(1920, 1080));

        // avatar icon
        profileIcon.setIcon(new ImageIcon("App/src/storage/avatar80x80.png"));
        profileIcon.setHorizontalAlignment(SwingConstants.CENTER);
        profileIcon.setVerticalAlignment(SwingConstants.CENTER);
        profileIcon.setBounds(0, -180, 1920, 1080);
        panelMain.add(profileIcon);

        // server validation error
        error.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        error.setForeground(new Color(153, 0, 51));
//        error.setText("There is no user with those credentials!");
        error.setHorizontalAlignment(SwingConstants.CENTER);
        error.setVerticalAlignment(SwingConstants.CENTER);
        error.setBounds(1920 / 2 - 230, 420, 460, 25);
        panelMain.add(error);

        // username
        usernameLabel.setText("Username");
        usernameLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 50, 70, 23);
        panelMain.add(usernameLabel);

        username.setBackground(new Color(255, 255, 255));
        username.setBounds(1920 / 2 - 50, 1080 / 2 - 50, 200, 23);
        panelMain.add(username);

        errorUsername.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorUsername.setForeground(new Color(153, 0, 51));
//        errorUsername.setText("Username is required!");
        errorUsername.setBounds(1920 / 2 - 45, 1080 / 2 - 30, 200, 23);
        panelMain.add(errorUsername);

        // password
        passwordLabel.setText("Password");
        passwordLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 10, 70, 23);
        panelMain.add(passwordLabel);

        password.setBackground(new Color(255, 255, 255));
        password.setBounds(1920 / 2 - 50, 1080 / 2 - 10, 200, 23);
        panelMain.add(password);

        errorPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorPassword.setForeground(new Color(153, 0, 51));
//        errorPassword.setText("Password is required!");
        errorPassword.setBounds(1920 / 2 - 45, 1080 / 2 + 10, 200, 23);
        panelMain.add(errorPassword);

        // submit button action
        submit.setText("Login");
        submit.setBounds(1920 / 2 + 50, 1080 / 2 + 80, 100, 23);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        panelMain.add(submit);

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
        panelMain.add(register);

        // card background image
        form.setIcon(new ImageIcon("App/src/storage/background_login480x400.jpg"));
        form.setHorizontalAlignment(SwingConstants.CENTER);
        form.setVerticalAlignment(SwingConstants.CENTER);
        form.setBounds(0, -70, 1920, 1080);
        panelMain.add(form);

        // default background image
        backgroundImage.setIcon(new ImageIcon("App/src/storage/background.jpg"));
        backgroundImage.setBounds(0, 0, 1920, 1080);
        panelMain.add(backgroundImage);


        // creating group layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelMain, GroupLayout.PREFERRED_SIZE, 1080, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            String username = this.username.getText().trim();
            char[] password = this.password.getPassword();

            // performing request validation
//            if (!validateRequest(username, password)) return;

            // preparing request data as parameters for further processing
            String passwd = new String(password);

            Map<String, String> params = new HashMap<>();
//            params.put("username", username);
//            params.put("password", passwd);
            params.put("id", "1");

            // finding user with requested username and password
            User user = Controller.getInstance().getUser(params);

            if (user.getFirstname() != null) {
                this.dispose();
                Dashboard.main(user);

            } else
                error.setText("There is no user with those credentials.");

            // remove error message
            Helper.delay(error, 5);

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

            // remove error message
            Helper.delay(this.errorUsername, 5);
        }

        if (password.length == 0) {
            result = false;
            this.errorPassword.setText("Password is required!");
            this.password.grabFocus();
            this.password.requestFocus();

            // remove error message
            Helper.delay(this.errorUsername, 5);
        }

        return result;
    }

}
