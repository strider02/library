package controllers;

import models.Model;
import models.Role;
import models.User;
import transfers.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class Controller {
    private Socket socket;
    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();

        return instance;
    }

    public void connectToServer() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request(transfers.Client cto) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(cto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public transfers.Server response() {

        transfers.Server sto = new Server();
        ObjectInputStream in = null;

        try {

            in = new ObjectInputStream(socket.getInputStream());
            sto = (transfers.Server) in.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return sto;
    }

    /*
     * --------------------------
     * CONTROLLER ACTIONS
     */
    public User getUser(Map<String, String> params) throws Exception {

        // creating user instance to act like parameter for query operation
        User user = new User();
        attachParams(user, params);

        UserCrudController crud = new UserCrudController();


        return crud.get(user, params);
    }

    public Role getUserRole(User user) throws Exception {

        Role role = new Role();
        user.setRole(role);

        RoleCrudController crud = new RoleCrudController();


        return crud.getUserRole(user);
    }

    public boolean addUser(Map<String, String> params) throws Exception {

        // creating user instance to act like parameter for query operation
        User user = new User();
        attachParams(user, params);

        UserCrudController crud = new UserCrudController();


        return crud.store(user, params);
    }

    public User addAndReturnUser(Map<String, String> params) throws Exception {

        // creating user instance to act like parameter for query operation
        User user = new User();
        attachParams(user, params);

        UserCrudController crud = new UserCrudController();


        return crud.storeAndReturn(user, params);
    }

    /*
     * --------------------------------
     * HELPERS
     */
    private void attachParams(Model model, Map<String, String> params) {

        params.forEach((column, value) -> {
            Method method = null;
            String crumb = column.substring(0, 1).toUpperCase() + column.substring(1);

            try {
                method = model
                        .getClass()
                        .getMethod("set" + crumb, String.class);

                method.invoke(model, value);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
