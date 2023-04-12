package bootstrap;

import controllers.Controller;
import models.Role;
import models.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
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


    public static void storeFile(File file, String path) {

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

        // if file exists we don't want to save it again
        // at this point file path is already persisted into database
        if (destinationFile.exists()) return;

        try {
            // coping file is not good decision
            // this is just for presentational purposes
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void browseActionPerformed(File file, JLabel placeholder) {
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
            Image image = icon.getImage().getScaledInstance(placeholder.getWidth(), placeholder.getHeight(), Image.SCALE_SMOOTH);


            // setting image on form label
            placeholder.setText(null);
            placeholder.setIcon(new ImageIcon(image));
        }
    }

    public static void showValidationMessages(JPanel panel, Map<String, String> validated) {

        for (Map.Entry<String, String> error : validated.entrySet()) {

            String fieldName = error.getKey();

            if (fieldName.equals("passwordRepeat"))
                fieldName = "password";

            String field = "error" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String errorMessage = error.getValue();

            // Display the error message to the user (e.g., using a message box or label)
            Component[] components = panel.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel && component.getName() != null && field.equals(component.getName())) {
                    JLabel label = (JLabel) component;
                    if (label.getName().contains("error")) {
                        if (label.getText().length() > 0) {
                            label.setText(label.getText() + ". " + errorMessage);
                        }
                        label.setText(errorMessage);
                        label.grabFocus();
                        label.requestFocus();
                        Helper.delay(label, 5);
                    }
                }

            }

        }
    }


    public static String getSelectedItem(ButtonGroup group) {

        Enumeration<AbstractButton> buttons = group.getElements();

        while (buttons.hasMoreElements()) {
            JRadioButton radioButton = (JRadioButton) buttons.nextElement();

            if (radioButton.isSelected())
                return radioButton.getText();
        }

        return null;
    }
}
