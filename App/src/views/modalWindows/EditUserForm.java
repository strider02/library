package views.modalWindows;

import controllers.Controller;
import models.User;
import views.Dashboard;
import views.Welcome;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author admin
 */
public class EditUserForm extends JFrame {

    private static User user;

    private JPanel editOperationPanel;
    private ButtonGroup roleButtonGroup;
    private JRadioButton adminRadio, employeeRadio, clientRadio;
    private JLabel editOperationTitleDetails, backToAllUsers, editOperationTitleMain, emailLabel, errorEmail, errorLastname, errorName, errorPassword, errorUsername, imageLabel, imagePreviewLabel, lastnameLabel, nameLabel, passwordlLabel, roleLabel, usernameLabel;
    private JTextField email, lastname, name, password, passwordRepeat, username;
    private JButton browse, cancel, submit;


    /**
     * Creates new form AddUserForm
     */
    public EditUserForm(User user, JTable table, int row) {

        EditUserForm.user = user;

        initComponents();
        showFieldValues(table, row);
    }

    // todo: zavrsiti edit operaciju
    private void showFieldValues(JTable table, int row) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = (int) model.getValueAt(row, 0);


        User user = null;
        try {
            user = Controller.getInstance().findUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        name.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
//        roleButtonGroup.setSelected(user.getRole().getName());
    }

    private void initComponents() {

        roleButtonGroup = new ButtonGroup();
        editOperationPanel = new JPanel();
        editOperationTitleMain = new JLabel();
        editOperationTitleDetails = new JLabel();
        nameLabel = new JLabel();
        name = new JTextField();
        backToAllUsers = new JLabel();
        lastnameLabel = new JLabel();
        lastname = new JTextField();
        emailLabel = new JLabel();
        email = new JTextField();
        usernameLabel = new JLabel();
        username = new JTextField();
        passwordlLabel = new JLabel();
        password = new JTextField();
        passwordRepeat = new JTextField();
        roleLabel = new JLabel();
        adminRadio = new JRadioButton();
        employeeRadio = new JRadioButton();
        clientRadio = new JRadioButton();
        imageLabel = new JLabel();
        imagePreviewLabel = new JLabel();
        browse = new JButton();
        submit = new JButton();
        cancel = new JButton();
        errorName = new JLabel();
        errorLastname = new JLabel();
        errorEmail = new JLabel();
        errorUsername = new JLabel();
        errorPassword = new JLabel();

        setTitle("Java Core Swing GUI project - Edit user");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("../storage/icons/favicon.png")));
        setName("frameModal");

        editOperationPanel.setFocusTraversalPolicyProvider(true);

        editOperationTitleMain.setFont(new Font("Segoe UI", 1, 22));
        editOperationTitleMain.setText("Users");

        editOperationTitleDetails.setFont(new Font("Segoe UI", 0, 14));
        editOperationTitleDetails.setText("Edit user.");

        nameLabel.setFont(new Font("Segoe UI", 1, 12));
        nameLabel.setText("Name");

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


        lastnameLabel.setFont(new Font("Segoe UI", 1, 12));
        lastnameLabel.setText("Last name");

        emailLabel.setFont(new Font("Segoe UI", 1, 12));
        emailLabel.setText("Email");

        usernameLabel.setFont(new Font("Segoe UI", 1, 12));
        usernameLabel.setText("Username");


        passwordlLabel.setFont(new Font("Segoe UI", 1, 12));
        passwordlLabel.setText("Password");


        roleLabel.setFont(new Font("Segoe UI", 1, 12));
        roleLabel.setText("Role");

        roleButtonGroup.add(adminRadio);
        adminRadio.setText("Admin");

        roleButtonGroup.add(employeeRadio);
        employeeRadio.setText("Employee");

        roleButtonGroup.add(clientRadio);
        clientRadio.setText("Client");

        imageLabel.setFont(new Font("Segoe UI", 1, 12));
        imageLabel.setText("Image");

        imagePreviewLabel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        imagePreviewLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        browse.setText("Choose file");
        browse.setInheritsPopupMenu(true);

        submit.setBackground(new Color(0, 153, 76, 90));
        submit.setFont(new Font("Segoe UI", 1, 14));
        submit.setForeground(new Color(255, 255, 255));
        submit.setText("Save and back");

        cancel.setBackground(new Color(204, 0, 0, 90));
        cancel.setFont(new Font("Segoe UI", 1, 14));
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

        errorName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorName.setForeground(new Color(153, 0, 0));
        errorName.setHorizontalAlignment(SwingConstants.RIGHT);
        errorName.setText("Name is required");

        errorLastname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorLastname.setForeground(new Color(153, 0, 0));
        errorLastname.setHorizontalAlignment(SwingConstants.RIGHT);
        errorLastname.setText("Last name is required");

        errorEmail.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorEmail.setForeground(new Color(153, 0, 0));
        errorEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        errorEmail.setText("Email is required");

        errorUsername.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorUsername.setForeground(new Color(153, 0, 0));
        errorUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        errorUsername.setText("Username is required");

        errorPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 9));
        errorPassword.setForeground(new Color(153, 0, 0));
        errorPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        errorPassword.setText("Password is required and has to be match");

        GroupLayout createOperationPanelLayout = new GroupLayout(editOperationPanel);
        editOperationPanel.setLayout(createOperationPanelLayout);
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
                                                                                .addComponent(editOperationTitleMain, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(28, 28, 28)
                                                                                .addComponent(editOperationTitleDetails, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(backToAllUsers, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(adminRadio, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(employeeRadio, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(clientRadio, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(createOperationPanelLayout.createSequentialGroup()
                                                                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(errorName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                        .addComponent(editOperationTitleMain, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editOperationTitleDetails, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
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
                                                                                        .addComponent(errorName))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(name, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(editOperationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(editOperationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }


}
