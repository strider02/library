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
import java.util.List;
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
            Thread.sleep(200);
            socket = new Socket("localhost", 9000);
        } catch (IOException | InterruptedException e) {
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

        } catch (IOException | ClassNotFoundException e) {
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

    public List getUsers() throws Exception {

        UserCrudController crud = new UserCrudController();


        return crud.all();
    }

    public Role getRole(int role_id) throws Exception {

        RoleCrudController crud = new RoleCrudController();

        return crud.find(role_id);
    }

    public List<Role> getRoles() throws Exception {

        RoleCrudController crud = new RoleCrudController();

        return crud.all();
    }

    public List<User> getUsersWithRole(String roleName) throws Exception {

        UserCrudController crud = new UserCrudController();

        return crud.getUsersWithRole(roleName);
    }

    public User findUser(int user_id) throws Exception {

        UserCrudController crud = new UserCrudController();

        return crud.find(user_id);
    }

    public boolean deleteUser(int user_id) throws Exception {

        UserCrudController crud = new UserCrudController();

        return crud.delete(user_id);
    }

    /*
     * --------------------------------
     * HELPERS
     */
    private void attachParams(Model model, Map<String, String> params) {

        params.forEach((column, value) -> {
            Method method = null;
            String crumb = column.substring(0, 1).toUpperCase() + column.substring(1);
            String part = crumb.replace("_", "");

            try {
                method = model
                        .getClass()
                        .getMethod("set" + part, String.class);

                method.invoke(model, value);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
