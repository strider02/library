package views;

import bootstrap.Helper;
import controllers.Controller;
import models.User;
import views.cell.TableActionCellEditor;
import views.cell.TableActionCellRender;
import views.cell.TableActionEvent;
import views.modalWindows.AddUserForm;
import views.modalWindows.EditUserForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author admin
 */
public class Dashboard extends JFrame {

    private static User authUser;
    private static Dashboard instance;

    private String[] tableColumns;
    private boolean canAdd = false;
    private boolean canShow = false;
    private boolean canEdit = false;
    private boolean canDelete = false;


    private JPanel sidePanel, contentPanel;
    private JScrollPane jScrollPane;
    private JTable dataTable;
    private JComboBox<String> filterRoles;
    private JLabel brand, contentTitle, contentTitleDetailText, iconFilter, iconSearch, menuDashboard, menuProfile, menuLogout, menuUsers, noty;
    private JTextField search;
    private JButton buttonAddUser;


    public static void main(User user) throws Exception {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        authUser = Helper.getValidatedAuthUser(authUser, user);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

                    if (instance == null) instance = new Dashboard();

                    instance.setVisible(true);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    /**
     * Creates new form Dashboard
     */
    public Dashboard() throws Exception {

        setPermissions();

        initComponents();

        List users = Controller.getInstance().getUsers();
        generateListOperation("*");
        Helper.populateUsersTable(dataTable, users);

        setTableActionEvent();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void setPermissions() {

        switch (authUser.getRole().getName()) {
            case "admin" -> {
                setTableColumns(new String[]{"Id", "Name", "Lastname", "Email", "Username", "Role", "Actions"});
                setCanAdd(true);
                setCanEdit(true);
                setCanShow(true);
                setCanDelete(true);
            }
            case "employee" -> {
                setTableColumns(new String[]{"Id", "Name", "Lastname", "Email", "Username", "Role", "Actions"});
                setCanAdd(true);
                setCanEdit(true);
                setCanShow(true);
            }
            case "client" -> {
                setTableColumns(new String[]{"Id", "Name", "Lastname", "Email", "Actions"});
                setCanShow(true);
            }
        }
    }

    private void setTableActionEvent() {

        TableActionEvent event = new TableActionEvent() {

            @Override
            public void onEdit(int row) {
                try {
//                    setPermissions();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (isCanEdit()) {
                    // open edit form
                    try {
                        new EditUserForm(authUser, dataTable, row).setVisible(true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    // show noty
                    Helper.showNoty(noty, "Sorry, you don't have privileges to perform EDIT action.", "warning", 5);
                }
            }

            @Override
            public void onDelete(int row) {

                try {
                    setPermissions();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (isCanDelete()) {
                    // delete row

                    DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                    int id = (int) model.getValueAt(row, 0);

                    try {
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            if (Controller.getInstance().deleteUser(id)) {
                                List allUsers = null;
                                allUsers = Controller.getInstance().getUsers();
                                Helper.populateUsersTable(dataTable, allUsers);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // show noty
                    Helper.showNoty(noty, "Sorry, you don't have privileges to perform DELETE action.", "warning", 5);
                }
            }

            @Override
            public void onShow(int row) {

                try {
                    setPermissions();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (isCanShow()) {
                    // open show view
                    System.out.println("Show row " + row);
                } else {
                    // show noty
                    Helper.showNoty(noty, "Sorry, you don't have privileges to perform SHOW action.", "warning", 5);
                }
            }
        };

        dataTable.getColumnModel().getColumn(tableColumns.length - 1).setCellRenderer(new TableActionCellRender());
        dataTable.getColumnModel().getColumn(tableColumns.length - 1).setCellEditor(new TableActionCellEditor(event));
    }


    private void initComponents() {

        sidePanel = new JPanel();
        brand = new JLabel();
        menuDashboard = new JLabel();
        menuUsers = new JLabel();
        menuProfile = new JLabel();
        menuLogout = new JLabel();
        contentPanel = new JPanel();
        contentTitle = new JLabel();
        contentTitleDetailText = new JLabel();
        buttonAddUser = new JButton();
        filterRoles = new JComboBox<>();
        iconFilter = new JLabel();
        iconSearch = new JLabel();
        noty = new JLabel();
        search = new JTextField();
        jScrollPane = new JScrollPane();
        dataTable = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setName("frame");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setSize(new Dimension(1920, 1080));
        setTitle("Java Core Swing GUI project - Dashboard");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("../storage/icons/favicon.png")));

        sidePanel.setBackground(new java.awt.Color(102, 153, 255));
        sidePanel.setMaximumSize(new java.awt.Dimension(300, 1080));
        sidePanel.setMinimumSize(new java.awt.Dimension(300, 1080));
        sidePanel.setName("sidePanel");
        sidePanel.setPreferredSize(new java.awt.Dimension(300, 1080));

        brand.setIcon(new ImageIcon("App/src/storage/logo100x100.png"));
        brand.setHorizontalTextPosition(SwingConstants.CENTER);
        brand.setMaximumSize(new java.awt.Dimension(180, 30));
        brand.setMinimumSize(new java.awt.Dimension(180, 30));
        brand.setName("menuDashboard");
        brand.setPreferredSize(new java.awt.Dimension(180, 30));

        menuDashboard.setFont(new java.awt.Font("Segoe UI", 1, 16));
        menuDashboard.setText("Dashboard");
        menuDashboard.setMaximumSize(new java.awt.Dimension(180, 30));
        menuDashboard.setMinimumSize(new java.awt.Dimension(180, 30));
        menuDashboard.setName("menuDashboard");
        menuDashboard.setPreferredSize(new java.awt.Dimension(180, 30));

        menuUsers.setFont(new java.awt.Font("Segoe UI", 0, 16));
        menuUsers.setText("Users");
        menuUsers.setMaximumSize(new java.awt.Dimension(180, 30));
        menuUsers.setMinimumSize(new java.awt.Dimension(180, 30));
        menuUsers.setName("menuDashboard");
        menuUsers.setPreferredSize(new java.awt.Dimension(180, 30));

        menuProfile.setFont(new java.awt.Font("Segoe UI", 0, 16));
        menuProfile.setText("Profile");
        menuProfile.setMaximumSize(new java.awt.Dimension(180, 30));
        menuProfile.setMinimumSize(new java.awt.Dimension(180, 30));
        menuProfile.setName("menuDashboard");
        menuProfile.setPreferredSize(new java.awt.Dimension(180, 30));

        menuLogout.setFont(new java.awt.Font("Segoe UI", 0, 16));
        menuLogout.setText("Logout");
        menuLogout.setMaximumSize(new java.awt.Dimension(180, 30));
        menuLogout.setMinimumSize(new java.awt.Dimension(180, 30));
        menuLogout.setName("menuDashboard");
        menuLogout.setPreferredSize(new java.awt.Dimension(180, 30));

        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup().addContainerGap(23, Short.MAX_VALUE).addComponent(brand, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20)).addGroup(sidePanelLayout.createSequentialGroup().addGap(30, 30, 30).addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(menuLogout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(menuProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(menuUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(menuDashboard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        sidePanelLayout.setVerticalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(sidePanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(brand, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE).addGap(112, 112, 112).addComponent(menuDashboard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(menuUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(menuProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(menuLogout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        contentPanel.setMaximumSize(new java.awt.Dimension(1620, 1080));
        contentPanel.setMinimumSize(new java.awt.Dimension(1620, 1080));
        contentPanel.setPreferredSize(new java.awt.Dimension(1620, 309));

        // noty
        noty.setVisible(false);
        noty.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        noty.setHorizontalAlignment(0);
        noty.setOpaque(true);
        noty.setBounds(1920 - 750, 40, 400, 60);
        contentPanel.add(noty);

        contentTitle.setFont(new java.awt.Font("Segoe UI", 0, 24));
        contentTitle.setText("Users");
        contentTitle.setMaximumSize(new java.awt.Dimension(180, 30));
        contentTitle.setMinimumSize(new java.awt.Dimension(180, 30));
        contentTitle.setName("menuDashboard");
        contentTitle.setPreferredSize(new java.awt.Dimension(180, 30));

        contentTitleDetailText.setFont(new java.awt.Font("Segoe UI", 0, 14));
//        contentTitleDetailText.setText("Showing 10 entries");
        contentTitleDetailText.setMaximumSize(new java.awt.Dimension(180, 30));
        contentTitleDetailText.setMinimumSize(new java.awt.Dimension(180, 30));
        contentTitleDetailText.setName("menuDashboard");
        contentTitleDetailText.setPreferredSize(new java.awt.Dimension(180, 30));

        buttonAddUser.setFont(new java.awt.Font("Segoe UI", 0, 14));
        buttonAddUser.setText("+ Add users");
        buttonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addUser(evt);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        filterRoles.setModel(new DefaultComboBoxModel<>(Helper.getRolesForFilter()));
        filterRoles.setName("filterRoles");
        filterRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTableData(evt);
            }
        });

        iconFilter.setIcon(new ImageIcon("App/src/storage/icons/filter20x20.png"));

        iconSearch.setIcon(new ImageIcon("App/src/storage/icons/search20x20.png"));

        search.setText("Search...");
        search.setMaximumSize(new java.awt.Dimension(1620, 60));
        search.setMinimumSize(new java.awt.Dimension(1620, 60));
        search.setPreferredSize(new java.awt.Dimension(1620, 60));

        jScrollPane.setMaximumSize(new java.awt.Dimension(1520, 600));
        jScrollPane.setMinimumSize(new java.awt.Dimension(1520, 600));
        jScrollPane.setPreferredSize(new java.awt.Dimension(1520, 600));


        setTableModel(dataTable, tableColumns);

        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(contentPanelLayout.createSequentialGroup().addGap(50, 50, 50).addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(contentPanelLayout.createSequentialGroup().addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(contentPanelLayout.createSequentialGroup().addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(buttonAddUser).addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(contentPanelLayout.createSequentialGroup().addComponent(contentTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(contentTitleDetailText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup().addComponent(iconFilter).addGap(18, 18, 18).addComponent(filterRoles, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addGroup(contentPanelLayout.createSequentialGroup().addComponent(iconSearch).addGap(18, 18, 18).addComponent(search, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))).addGap(1820, 1820, 1820)))));
        contentPanelLayout.setVerticalGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(contentPanelLayout.createSequentialGroup().addGap(25, 25, 25).addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(iconSearch, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addComponent(search, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)).addGap(115, 115, 115).addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(contentTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(contentTitleDetailText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(22, 22, 22).addComponent(buttonAddUser, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(filterRoles).addComponent(iconFilter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(18, 18, 18).addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(sidePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 333, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE));

        pack();
    }

    private void filterTableData(ActionEvent evt) {
        String selected = (String) filterRoles.getSelectedItem();

        try {
            generateListOperation(selected);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void generateListOperation(String selected) throws Exception {

        List allUsers = Controller.getInstance().getUsers();
        List filteredUsers = Controller.getInstance().getUsersWithRole(selected);

        int filtered = filteredUsers.size(), total = allUsers.size();

        if (selected.equals("*"))
            Helper.populateUsersTable(dataTable, allUsers);
        else
            Helper.populateUsersTable(dataTable, filteredUsers);


        contentTitleDetailText.setText("Showing " + filtered + " of " + total + " entries.");
    }

    private void addUser(ActionEvent evt) throws Exception {

        try {
            setPermissions();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (isCanAdd()) {
            // open create form
            new AddUserForm(authUser).setVisible(true);

        } else {
            // show noty
            Helper.showNoty(noty, "Sorry, you don't have privileges to perform CREATE action.", "warning", 5);
        }

    }

    private void setTableModel(JTable table, String[] columns) {

        table.setModel(new DefaultTableModel(new Object[][]{}, columns));

        dataTable.getTableHeader().setReorderingAllowed(false);
        dataTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        dataTable.setRowHeight(40);

        jScrollPane.setViewportView(dataTable);

        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(tableColumns.length - 1).setResizable(false);
        }

    }

    public String[] getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(String[] tableColumns) {
        this.tableColumns = tableColumns;
    }

    public boolean isCanAdd() {
        return canAdd;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public boolean isCanShow() {
        return canShow;
    }

    public void setCanShow(boolean canShow) {
        this.canShow = canShow;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}
