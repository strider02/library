package bootstrap;

import controllers.Controller;
import models.Role;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Helper {

    /**
     * Method removes error message in provided time
     *
     * @param label
     * @param seconds
     */
    public static void delay(JLabel label, int seconds) {

        CompletableFuture.delayedExecutor(seconds, TimeUnit.SECONDS).execute(() -> {
            try {

                Method method;

                method = label.getClass().getMethod("setText", String.class);
                method.invoke(label, "");
                // if label has background, then unset it
                label.setBackground(null);


            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

                throw new RuntimeException(e);
            }
        });

    }

    public static void showNoty(JLabel noty, String message, String type, int duration) {

        noty.setVisible(true);
        Color color = null;

        switch (type) {
            case "success":
                color = new Color(0, 153, 76, 90);
                break;
            case "warning":
                color = new Color(250, 215, 20);
                break;
            case "danger":
                color = new Color(204, 0, 0, 90);
                break;
        }

        noty.setBackground(color);
        noty.setText(message);

        delay(noty, duration);
    }

    public static User getUsersRole(User user) throws Exception {
        Role role = Controller.getInstance().getRole(user.getRoleId());

        user.setRoleId(role.getId());
        user.setRole(role);

        return user;
    }

    public static void populateUsersTable(JTable table, List users) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        Object[] row = new Object[6];

        for (User user : (Iterable<User>) users) {
            row[0] = user.getId();
            row[1] = user.getFirstname();
            row[2] = user.getLastname();
            row[3] = user.getEmail();
            row[4] = user.getEmail();
            row[5] = user.getRole().getName();

            model.addRow(row);
        }
    }

    public static User getValidatedAuthUser(User authUser, User user) throws Exception {
        if (authUser == null) authUser = Helper.getUsersRole(user);
        else {
            authUser = authUser.getRoleId() == user.getRoleId() ? user : Helper.getUsersRole(user);
        }

        return authUser;

    }

    public static String[] getRolesForFilter() {

        try {
            List<Role> roles = Controller.getInstance().getRoles();

            String[] roleNames = new String[roles.size() + 1];
            roleNames[0] = "*";

            for (int i = 0; i < roles.size(); i++) {

                Role role = roles.get(i);
                roleNames[i + 1] = role.getName();
            }

            return roleNames;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
