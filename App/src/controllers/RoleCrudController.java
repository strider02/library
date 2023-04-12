package controllers;

import models.Role;
import models.User;
import transfers.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utillities.OPERATIONS.*;
import static utillities.STATUSES.*;

public class RoleCrudController {
    private List<Role> roles = new ArrayList<>();


    public RoleCrudController() {
//        users.add(new User("Joe", "Doe", "joe.doe@bookstore.com", "admin", "123", "UniCredit Bank", "1234-1234-1234-1234"));
    }

    public Role find(int id) throws Exception {

        Role role = new Role();

        role.setWhere("id = '" + id + "'");

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(FIND_ROLE);
        cto.setParameter(role);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (Role) sto.getResult();
    }

    public List<Role> all() throws Exception {

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(GET_ROLES);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (List<Role>) sto.getResult();
    }

    public Role get(Role role, Map<String, String> params) throws Exception {

        StringBuilder where = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (counter == params.size() - 1)
                where.append(param.getKey() + " = '" + param.getValue() + "'");
            else
                where.append(param.getKey() + " = '" + param.getValue() + "' and ");

            counter++;
        }


        role.setWhere(where.toString());

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(GET_USER_ROLE);
        cto.setParameter(role);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (Role) sto.getResult();
    }

    public Role getUserRole(User user) throws Exception {

        Role role = user.getRole();
        role.setId(user.getRoleId());

        role.setWhere("id = '" + role.getId() + "'");

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(GET_USER_ROLE);
        cto.setParameter(role);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (Role) sto.getResult();
    }

    public List<Role> getAll() {
//        return roles;
        return null;
    }

    public void save(Role role) {
//        roles.add(role);
    }

    public void update(Role role, HashMap<String, String> params) {

        params.forEach((k, v) -> {

            Method method = null;

            try {
                method = Class
                        .forName("models.User")
                        .getMethod("set" + k.substring(0, 1).toUpperCase() + k.substring(1), String.class);

                method.invoke(role, v);

            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }


        });

        roles.add(role);
    }

    public void delete(Role role) {
        roles.remove(role);
    }
}
