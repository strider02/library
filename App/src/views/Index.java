/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.Controller;
import models.Role;
import models.User;

import javax.swing.*;

/**
 * @author admin
 */
public class Index extends JFrame {

    // Variables declaration - do not modify
    private JTextField bank;
    private JButton cancel;
    private JPanel contentPanel;
    private JTextField creditCard;
    private JTextField email;
    private JTextField firstname;
    private JLabel jLabelBank;
    private JLabel jLabelCreaditCard;
    private JLabel jLabelEmail;
    private JLabel jLabelFirstname;
    private JLabel jLabelPassword;
    private JLabel jLabelRepeatPassword;
    private JLabel jLabelRoles;
    private JLabel jLabelLastname;
    private JLabel jLabelUsername;
    private JMenu jMenuAdmin;
    private JMenuBar jMenuBarTop;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JMenu jMenuProfile;
    private JMenu jMenuStore;
    private JTextField password;
    private JLabel profile_icon;
    private JTextField repeatPassword;
    private JLabel roles;
    private JButton submit;
    private JTextField lastname;
    private JTextField username;

    private User user;
    // End of variables declaration

    /**
     * Creates new form Index
     */
    public Index(User user) throws Exception {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        // finding role for specified user
        Role role = Controller.getInstance().getUserRole(user);
        user.setRole(role);

        this.user = user;
        show(user);
    }

    private void show(User user) {
//        firstname.setText(user.getFirstname());
        firstname.setText(user.getRole().getName());
        lastname.setText(user.getFirstname());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
//        repeatPassword.setText("");
    }

    private void initComponents() {

        contentPanel = new JPanel();
        profile_icon = new JLabel();
        jLabelFirstname = new JLabel();
        firstname = new JTextField();
        jLabelLastname = new JLabel();
        lastname = new JTextField();
        jLabelEmail = new JLabel();
        email = new JTextField();
        jLabelUsername = new JLabel();
        username = new JTextField();
        jLabelPassword = new JLabel();
        password = new JTextField();
        jLabelRepeatPassword = new JLabel();
        repeatPassword = new JTextField();
        jLabelBank = new JLabel();
        bank = new JTextField();
        jLabelCreaditCard = new JLabel();
        creditCard = new JTextField();
        jLabelRoles = new JLabel();
        roles = new JLabel();
        submit = new JButton();
        cancel = new JButton();
        jMenuBarTop = new JMenuBar();
        jMenuStore = new JMenu();
        jMenuAdmin = new JMenu();
        jMenuItem5 = new JMenuItem();
        jMenuItem6 = new JMenuItem();
        jMenuProfile = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 640));
        setPreferredSize(new java.awt.Dimension(800, 640));
        setSize(new java.awt.Dimension(800, 640));

        contentPanel.setMaximumSize(new java.awt.Dimension(800, 640));
        contentPanel.setMinimumSize(new java.awt.Dimension(800, 640));
        contentPanel.setName("");
        contentPanel.setLayout(null);
        contentPanel.setVisible(false);


        profile_icon.setHorizontalAlignment(SwingConstants.CENTER);
        profile_icon.setIcon(new ImageIcon("D:\\Downloads\\Browsers\\png-transparent-computer-icons-avatar-user-profile-avatar-heroes-dark-black.png"));
        contentPanel.add(profile_icon);
        profile_icon.setBounds(680, 10, 120, 120);

        jLabelFirstname.setText("First name");
        contentPanel.add(jLabelFirstname);
        jLabelFirstname.setBounds(50, 160, 100, 16);
        contentPanel.add(firstname);
        firstname.setBounds(180, 160, 170, 22);

        jLabelLastname.setText("Lastname");
        contentPanel.add(jLabelLastname);
        jLabelLastname.setBounds(50, 210, 100, 16);
        contentPanel.add(lastname);
        lastname.setBounds(180, 210, 170, 22);

        jLabelEmail.setText("Email");
        contentPanel.add(jLabelEmail);
        jLabelEmail.setBounds(50, 270, 100, 16);
        contentPanel.add(email);
        email.setBounds(180, 270, 170, 22);

        jLabelUsername.setText("Username");
        contentPanel.add(jLabelUsername);
        jLabelUsername.setBounds(50, 320, 100, 16);
        contentPanel.add(username);
        username.setBounds(180, 320, 170, 22);

        jLabelPassword.setText("Password");
        contentPanel.add(jLabelPassword);
        jLabelPassword.setBounds(50, 370, 100, 16);
        contentPanel.add(password);
        password.setBounds(180, 370, 170, 22);

        jLabelRepeatPassword.setText("Repeat password");
        contentPanel.add(jLabelRepeatPassword);
        jLabelRepeatPassword.setBounds(50, 430, 100, 16);
        contentPanel.add(repeatPassword);
        repeatPassword.setBounds(180, 430, 170, 22);

        jLabelBank.setText("Bank");
        contentPanel.add(jLabelBank);
        jLabelBank.setBounds(50, 490, 100, 16);
        contentPanel.add(bank);
        bank.setBounds(180, 490, 170, 22);

        jLabelCreaditCard.setText("CCN");
        contentPanel.add(jLabelCreaditCard);
        jLabelCreaditCard.setBounds(50, 550, 100, 16);
        contentPanel.add(creditCard);
        creditCard.setBounds(180, 550, 170, 22);

        jLabelRoles.setText("Roles");
        contentPanel.add(jLabelRoles);
        jLabelRoles.setBounds(50, 70, 100, 16);
        contentPanel.add(roles);
        roles.setBounds(180, 70, 390, 20);

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        contentPanel.add(submit);
        submit.setBounds(260, 600, 72, 23);

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        contentPanel.add(cancel);
        cancel.setBounds(390, 600, 72, 23);

        jMenuBarTop.setMaximumSize(new java.awt.Dimension(800, 32768));
        jMenuBarTop.setMinimumSize(new java.awt.Dimension(800, 23));
        jMenuBarTop.setOpaque(true);
        jMenuBarTop.setPreferredSize(new java.awt.Dimension(800, 23));

        jMenuStore.setText("Store");
        jMenuBarTop.add(jMenuStore);

        jMenuAdmin.setText("Admin");

        jMenuItem5.setText("Books");
        jMenuAdmin.add(jMenuItem5);

        jMenuItem6.setText("Users");
        jMenuAdmin.add(jMenuItem6);

        jMenuBarTop.add(jMenuAdmin);

        jMenuProfile.setText("Profile");
        jMenuProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuProfileMouseClicked(evt);
            }
        });
        jMenuBarTop.add(jMenuProfile);

        setJMenuBar(jMenuBarTop);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jMenuProfileMouseClicked(java.awt.event.MouseEvent evt) {
        contentPanel.setVisible(true);
    }

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(User user) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Index(user).setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
