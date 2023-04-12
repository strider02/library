package views.modalWindows;

import bootstrap.Helper;
import bootstrap.UserValidator;
import controllers.Controller;
import models.User;
import views.Dashboard;
import views.Register;
import views.Welcome;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author admin
 */
public class AddUserForm extends JFrame {

    private static User user;
    private File file = null;

    private JPanel createOperationPanel;
    private ButtonGroup roleButtonGroup;
    private JRadioButton adminRadio, employeeRadio, clientRadio;
    private JLabel createOperationTitleDetails, backToAllUsers, createOperationTitleMain, emailLabel, errorEmail, errorLastname, errorFirstname, errorPassword, errorUsername, imageLabel, imagePreviewLabel, lastnameLabel, nameLabel, passwordlLabel, roleLabel, usernameLabel, noty;
    private JTextField email, lastname, firstname, username;
    private JPasswordField password, passwordRepeat;
    private JButton browse, cancel, submit;


    /**
     * Creates new form AddUserForm
     */
    public AddUserForm(User user) throws Exception {
        AddUserForm.user = Helper.getUsersRole(user);

        initComponents();
    }

    private void initComponents() {

        roleButtonGroup = new ButtonGroup();
        createOperationPanel = new JPanel();
        createOperationTitleMain = new JLabel();
        createOperationTitleDetails = new JLabel();
        nameLabel = new JLabel();
        firstname = new JTextField();
        backToAllUsers = new JLabel();
        lastnameLabel = new JLabel();
        lastname = new JTextField();
        emailLabel = new JLabel();
        email = new JTextField();
        usernameLabel = new JLabel();
        username = new JTextField();
        passwordlLabel = new JLabel();
        password = new JPasswordField();
        passwordRepeat = new JPasswordField();
        roleLabel = new JLabel();
        adminRadio = new JRadioButton();
        employeeRadio = new JRadioButton();
        clientRadio = new JRadioButton();
        imageLabel = new JLabel();
        noty = new JLabel();
        imagePreviewLabel = new JLabel();
        browse = new JButton();
        submit = new JButton();
        cancel = new JButton();
        errorFirstname = new JLabel();
        errorLastname = new JLabel();
        errorEmail = new JLabel();
        errorUsername = new JLabel();
        errorPassword = new JLabel();

        setTitle("Java Core Swing GUI project - Add new user");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("../storage/icons/favicon.png")));
        setName("frameModal");

        createOperationPanel.setFocusTraversalPolicyProvider(true);

        // noty
        noty.setVisible(false);
        noty.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        noty.setHorizontalAlignment(0);
        noty.setOpaque(true);
        noty.setBounds(1920 - 750, 40, 400, 60);
        createOperationPanel.add(noty);

        createOperationTitleMain.setFont(new Font("Segoe UI", Font.BOLD, 22));
        createOperationTitleMain.setText("Users");

        createOperationTitleDetails.setFont(new Font("Segoe UI", 0, 14));
        createOperationTitleDetails.setText("Add user.");

        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        nameLabel.setText("Firstname");

        backToAllUsers.setForeground(new Color(102, 102, 255));
        backToAllUsers.setText("<< Back to all users");
        backToAllUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Dashboard.main(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        lastnameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lastnameLabel.setText("Last name");

        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        emailLabel.setText("Email");

        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        usernameLabel.setText("Username");


        passwordlLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passwordlLabel.setText("Password");


        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        roleLabel.setText("Role");

        roleButtonGroup.add(adminRadio);
        adminRadio.setText("Admin");

        roleButtonGroup.add(employeeRadio);
        employeeRadio.setText("Employee");

        roleButtonGroup.add(clientRadio);
        clientRadio.setText("Client");

        imageLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        imageLabel.setText("Image");

        imagePreviewLabel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        imagePreviewLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        browse.setText("Choose file");
        browse.setInheritsPopupMenu(true);
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Helper.browseActionPerformed(file, imagePreviewLabel);
            }
        });

        submit.setBackground(new Color(0, 153, 76, 90));
        submit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submit.setForeground(new Color(255, 255, 255));
        submit.setText("Save and back");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        cancel.setBackground(new Color(204, 0, 0, 90));
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancel.setForeground(new Color(50, 50, 50));
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Dashboard.main(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        errorFirstname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorFirstname.setForeground(new Color(153, 0, 0));
        errorFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
        errorFirstname.setName("errorFirstname");

        errorLastname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorLastname.setForeground(new Color(153, 0, 0));
        errorLastname.setHorizontalAlignment(SwingConstants.RIGHT);
        errorLastname.setName("errorLastname");

        errorEmail.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorEmail.setForeground(new Color(153, 0, 0));
        errorEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        errorEmail.setName("errorEmail");

        errorUsername.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorUsername.setForeground(new Color(153, 0, 0));
        errorUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        errorUsername.setName("errorUsername");

        errorPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorPassword.setForeground(new Color(153, 0, 0));
        errorPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        errorPassword.setName("errorPassword");

        GroupLayout createOperationPanelLayout = new GroupLayout(createOperationPanel);
        createOperationPanel.setLayout(createOperationPanelLayout);
        createOperationPanelLayout.setHorizontalGroup(
                createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(imageLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(passwordRepeat, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(username, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(email, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lastname, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(createOperationTitleMain, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(28, 28, 28)
                                                                                .addComponent(createOperationTitleDetails, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(backToAllUsers, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(firstname, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(adminRadio, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(employeeRadio, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(clientRadio, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(errorFirstname, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(lastnameLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(errorLastname, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(passwordlLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(errorPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(errorUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(errorEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addGap(0, 37, Short.MAX_VALUE)))
                                                .addContainerGap())
                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                .addComponent(imagePreviewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(browse)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(submit)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancel)
                                                .addGap(47, 47, 47))))
        );
        createOperationPanelLayout.setVerticalGroup(
                createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(createOperationTitleMain, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createOperationTitleDetails, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backToAllUsers, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(errorFirstname))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(firstname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(lastnameLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(errorLastname))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(GroupLayout.Alignment.TRAILING, createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(lastname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(errorEmail, GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(email, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(errorUsername))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(username, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(passwordlLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(errorPassword))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordRepeat, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(adminRadio)
                                        .addComponent(employeeRadio)
                                        .addComponent(clientRadio))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(imagePreviewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(createOperationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(browse)
                                                .addComponent(submit)
                                                .addComponent(cancel)))
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(createOperationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(createOperationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }


    private void submitActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            // requested inputs and values
            Map<String, String> request = new HashMap<>();

            request.put("firstname", this.firstname.getText().trim());
            request.put("lastname", this.lastname.getText().trim());
            request.put("email", this.email.getText().trim());
            request.put("username", this.username.getText().trim());
            request.put("password", new String(this.password.getPassword()).trim());
            request.put("passwordRepeat", new String(this.passwordRepeat.getPassword()).trim());

            String role = Helper.getSelectedItem(roleButtonGroup);

            if (role != null)
                request.put("role_id", role);

            if (file != null)
                request.put("image", this.file.getName());


            // performing request validation
            Map<String, String> validated = UserValidator.validate(request);

            if (!validated.isEmpty()) {
                // Handle validation errors here (e.g., display error messages to the user)
                Helper.showValidationMessages(createOperationPanel, validated);

            } else {

                // Validation passed, do something with the input data (e.g., save it to a database)
                Map<String, String> params = new HashMap<>();
                params.put("username", request.get("username"));


                // checking if user with provided username exists
                User user = Controller.getInstance().getUser(params);


                if (user.getUsername() != null) {

                    Helper.showNoty(noty, "Sorry, requested username already exists. Please provide another one.", "danger", 5);


                } else {
                    String image = null;

                    params.clear();

                    params.put("firstname", request.get("firstname"));
                    params.put("lastname", request.get("lastname"));
                    params.put("email", request.get("email"));
                    params.put("username", request.get("username"));
                    params.put("password", request.get("password"));

                    if (role != null)
                        request.put("role_id", role);

                    if (image != null)
                        params.put("image", "storage/avatars/" + image);


                    // save and login new user
                    User newUser = Controller.getInstance().addAndReturnUser(params);

                    if (newUser.getUsername() == null) {
                        Helper.showNoty(noty, "Error occurred while registration process.", "danger", 5);

                        return;
                    }

                    // saving image to disk after successful storing new user to database
                    if (file != null)
                        Helper.storeFile(file, null);

                    this.dispose();
                    Dashboard.main(newUser);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
