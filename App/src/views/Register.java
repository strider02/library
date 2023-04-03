package views;

import controllers.Controller;
import models.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author admin
 */
public class Register extends JFrame {

    // Variables declaration
    private JPanel background;
    private JLabel window;
    private JLabel form;
    private JLabel profileIcon;
    private JLabel firstnameLabel;
    private JTextField firstname;
    private JLabel lastnameLabel;
    private JTextField lastname;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel passwordRepeatLabel;
    private JPasswordField passwordRepeat;
    private JLabel imageLabel;
    private JButton browse;
    private JButton submit;

    private JLabel errorFirstname;
    private JLabel errorLastname;
    private JLabel errorEmail;
    private JLabel errorUsername;
    private JLabel errorPassword;
    private JLabel errorPasswordRepeat;
    private JLabel error;

    private JLabel login;
    private File file = null;
    // End of variables declaration


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Register().setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Creates new form Login
     */
    public Register() {

        // initialise components
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void initComponents() {

        background = new JPanel();
        window = new JLabel();
        form = new JLabel();
        profileIcon = new JLabel();

        firstnameLabel = new JLabel();
        firstname = new JTextField();
        lastnameLabel = new JLabel();
        lastname = new JTextField();
        emailLabel = new JLabel();
        email = new JTextField();
        usernameLabel = new JLabel();
        username = new JTextField();
        passwordLabel = new JLabel();
        password = new JPasswordField();
        passwordRepeat = new JPasswordField();
        imageLabel = new JLabel();

        browse = new JButton();
        submit = new JButton();

        login = new JLabel();

        errorFirstname = new JLabel();
        errorLastname = new JLabel();
        errorEmail = new JLabel();
        errorUsername = new JLabel();
        errorPassword = new JLabel();
        error = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setSize(new Dimension(1920, 1080));
        setTitle("Java Core Swing GUI project - Register");

        // set size and layout
        background.setLayout(null);
        background.setSize(new Dimension(1920, 1080));

        // avatar icon
        profileIcon.setIcon(new ImageIcon("App/src/storage/avatar80X80.png"));
        profileIcon.setHorizontalAlignment(SwingConstants.CENTER);
        profileIcon.setVerticalAlignment(SwingConstants.CENTER);
        profileIcon.setBounds(0, -300, 1920, 1080);
        background.add(profileIcon);


        // server validation error
        error.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        error.setForeground(new Color(153, 0, 51));
        error.setHorizontalAlignment(SwingConstants.CENTER);
        error.setVerticalAlignment(SwingConstants.CENTER);
        error.setBounds(1920 / 2 - 230, 1080 / 2 - 220, 460, 25);
        background.add(error);

        // firstname
        firstnameLabel.setText("First name");
        firstnameLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 160, 70, 23);
        background.add(firstnameLabel);

        firstname.setBounds(1920 / 2 - 50, 1080 / 2 - 160, 200, 23);
        background.add(firstname);

        errorFirstname.setName("errorFirstname");
        errorFirstname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorFirstname.setForeground(new Color(153, 0, 51));
        errorFirstname.setBounds(1920 / 2 - 45, 1080 / 2 - 140, 200, 23);
        background.add(errorFirstname);

        // lastname
        lastnameLabel.setText("Last name");
        lastnameLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 120, 70, 23);
        background.add(lastnameLabel);

        lastname.setBounds(1920 / 2 - 50, 1080 / 2 - 120, 200, 23);
        background.add(lastname);

        errorLastname.setName("errorLastname");
        errorLastname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorLastname.setForeground(new Color(153, 0, 51));
        errorLastname.setBounds(1920 / 2 - 45, 1080 / 2 - 100, 200, 23);
        background.add(errorLastname);

        // email
        emailLabel.setText("Email");
        emailLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 80, 70, 23);
        background.add(emailLabel);

        email.setBounds(1920 / 2 - 50, 1080 / 2 - 80, 200, 23);
        background.add(email);

        errorEmail.setName("errorEmail");
        errorEmail.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorEmail.setForeground(new Color(153, 0, 51));
        errorEmail.setBounds(1920 / 2 - 45, 1080 / 2 - 60, 200, 23);
        background.add(errorEmail);

        // username
        usernameLabel.setText("Username");
        usernameLabel.setBounds(1920 / 2 - 160, 1080 / 2 - 40, 70, 23);
        background.add(usernameLabel);

        username.setBounds(1920 / 2 - 50, 1080 / 2 - 40, 200, 23);
        background.add(username);

        errorUsername.setName("errorUsername");
        errorUsername.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorUsername.setForeground(new Color(153, 0, 51));
        errorUsername.setBounds(1920 / 2 - 45, 1080 / 2 - 20, 200, 23);
        background.add(errorUsername);

        // password
        passwordLabel.setText("Password");
        passwordLabel.setBounds(1920 / 2 - 160, 1080 / 2, 70, 23);
        background.add(passwordLabel);

        password.setBounds(1920 / 2 - 50, 1080 / 2, 200, 23);
        background.add(password);


        // repeat password
        passwordRepeat.setBounds(1920 / 2 - 50, 1080 / 2 + 20, 200, 23);
        background.add(passwordRepeat);

        errorPassword.setName("errorPassword");
        errorPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorPassword.setForeground(new Color(153, 0, 51));
        errorPassword.setBounds(1920 / 2 - 45, 1080 / 2 + 40, 200, 23);
        background.add(errorPassword);

        // image
        Border blackline = BorderFactory.createLineBorder(new Color(60, 63, 65));
        imageLabel.setBorder(blackline);
        imageLabel.setOpaque(true);
        imageLabel.setText("photo");
        imageLabel.setForeground(new Color(255, 255, 255));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(new Color(60, 63, 65, 10));
        imageLabel.setBounds(1920 / 2 - 50, 1080 / 2 + 80, 80, 80);
        background.add(imageLabel);

        browse.setText("Browse");
        browse.setBounds(1920 / 2 - 160, 1080 / 2 + 140, 80, 20);
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        background.add(browse);

        // submit button action
        submit.setText("Register");
        submit.setBounds(1920 / 2 + 20, 1080 / 2 + 220, 130, 23);
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        background.add(submit);

        // login action
        login.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        login.setForeground(new Color(255, 255, 255));
        login.setText("Already registered? Login here!");
        login.setBounds(1920 / 2 + 60, 830, 1920, 23);
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        background.add(login);

        // card background image
        form.setIcon(new ImageIcon("App/src/storage/background_register480x720.jpg"));
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
            String firstname = this.firstname.getText().trim();
            String lastname = this.lastname.getText().trim();
            String email = this.email.getText().trim();
            String username = this.username.getText().trim();
            char[] password = this.password.getPassword();
            char[] passwordRepeat = this.passwordRepeat.getPassword();
            String image = null;

            if (file != null)
                image = file.getName();


            // convert array to string
            String pass = new String(password);
            String passRepeat = new String(passwordRepeat);


            // performing request validation
            if (!validateRequest(firstname, lastname, email, username, pass, passRepeat))
                return;

            Map<String, String> params = new HashMap<>();
            params.put("username", username);


            // checking if user with provided username exists
            User user = Controller.getInstance().getUser(params);


            if (user.getUsername() != null) {

                error.setText("Sorry, that username already exists. Please provide another one.");
                this.username.grabFocus();
                this.username.requestFocus();

                // remove error message after xx seconds
                this.delay(error, 5);

                return;

            } else {

                params.clear();
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("username", username);
                params.put("password", pass);
                if (image != null)
                    params.put("image", "storage/avatars/" + image);


                // save and login new user
                User newUser = Controller.getInstance().addAndReturnUser(params);

                if (newUser.getUsername() == null) {
                    error.setText("Error occurred while registration process.");

                    // remove error message after xx seconds
                    this.delay(this.error, 5);

                    return;
                }

                // saving image to disk after successful storing new user to database
                this.storeFile(file, null);

                this.dispose();
                Index.main(newUser);
            }

        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void storeFile(File file, String path) {

        String filePath = file.getAbsolutePath();
        String filename = Paths.get(filePath).getFileName().toString();
        if (path == null)
            path = "App/src/storage/avatars/";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File sourceFile = new File(filePath);
        File destinationFile = new File(path + filename);

        if (destinationFile.exists()) {
//            int dotIndex = filename.lastIndexOf(".");
//            String baseName = filename.substring(0, dotIndex);
//            String extension = filename.substring(dotIndex);
//            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//            String newFileName = baseName + "_" + timestamp + extension;
//            destinationFile = new File(path + newFileName);
            return;
        }

        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser browseImageFile = new JFileChooser();
        int showOpenDialog = browseImageFile.showOpenDialog(null);

        // extension filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(filter);

        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            file = browseImageFile.getSelectedFile();
            String selectedImagePath = file.getAbsolutePath();

            // resizing
            ImageIcon icon = new ImageIcon(selectedImagePath);
            Image image = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);


            // setting image on form label
            imageLabel.setText(null);
            imageLabel.setIcon(new ImageIcon(image));
        }
    }

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
        Login.main(null);
    }

    private boolean validateRequest(String firstname, String lastname, String email, String username, String password, String passwordRepeat) {

        boolean result = true;

        if (firstname == null | firstname.equals("")) {
            result = false;
            this.errorFirstname.setText("First name is required!");
            this.firstname.grabFocus();
            this.firstname.requestFocus();

            this.delay(this.errorFirstname, 5);
        }

        if (lastname == null | lastname.equals("")) {
            result = false;
            this.errorLastname.setText("Last name is required!");
            this.lastname.grabFocus();
            this.lastname.requestFocus();

            this.delay(this.errorLastname, 5);

        }

        if (!email.contains("@")) {
            result = false;
            this.errorEmail.setText("Email is required!");
            this.email.grabFocus();
            this.email.requestFocus();

            this.delay(this.errorEmail, 5);

        }

        if (username == null | username.equals("")) {
            result = false;
            this.errorUsername.setText("Username is required!");
            this.username.grabFocus();
            this.username.requestFocus();

            this.delay(this.errorUsername, 5);

        }

        if (password == null | password.equals("")) {
            result = false;
            this.errorPassword.setText("Password is required!");
            this.password.grabFocus();
            this.password.requestFocus();

            this.delay(this.errorPassword, 5);


            if (!passwordRepeat.equals(passwordRepeat)) {
                result = false;
                this.errorPassword.setText("Not match!");
                this.password.grabFocus();
                this.password.requestFocus();

                // execute code after xx seconds
                this.delay(this.errorPassword, 5);

            }
        }

        return result;

    }

    /**
     * Removes validation error message after xx seconds
     *
     * @param label
     * @param seconds
     */
    private void delay(JLabel label, int seconds) {
        CompletableFuture.delayedExecutor(seconds, TimeUnit.SECONDS).execute(() -> {

            try {

                Method method;

                method = label.getClass().getMethod("setText", String.class);
                method.invoke(label, "");

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }


}

