package controllers;

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

public class UserCrudController implements CrudController<User> {
    private List<User> users = new ArrayList<>();


    public UserCrudController() {
//        users.add(new User("Joe", "Doe", "joe.doe@bookstore.com", "admin", "123", "UniCredit Bank", "1234-1234-1234-1234"));
    }

    @Override
    public User find(long id) {
        return null;
    }

    @Override
    public User get(User user, Map<String, String> params) throws Exception {

        StringBuilder where = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (counter == params.size() - 1)
                where.append(param.getKey() + " = '" + param.getValue() + "'");
            else
                where.append(param.getKey() + " = '" + param.getValue() + "' and ");

            counter++;
        }


        user.setWhere(where.toString());

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(GET_USER);
        cto.setParameter(user);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (User) sto.getResult();
    }

    public boolean store(User user, Map<String, String> params) throws Exception {

        StringBuilder values = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (counter == params.size() - 1)
                values.append(param.getKey() + " = '" + param.getValue() + "'");
            else
                values.append(param.getKey() + " = '" + param.getValue() + "', ");

            counter++;
        }


        user.setValues(values.toString());

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(ADD_USER);
        cto.setParameter(user);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (boolean) sto.getResult();
    }

    public User storeAndReturn(User user, Map<String, String> params) throws Exception {

        StringBuilder values = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (counter == params.size() - 1)
                values.append(param.getKey() + " = '" + param.getValue() + "'");
            else
                values.append(param.getKey() + " = '" + param.getValue() + "', ");

            counter++;
        }


        user.setValues(values.toString());

        // creating transfer object from client side
        transfers.Client cto = new transfers.Client();
        cto.setOperation(ADD_AND_RETURN_USER);
        cto.setParameter(user);

        // sending request to server
        Controller.getInstance().request(cto);

        // getting response from server
        Server sto = Controller.getInstance().response();

        if (sto.getStatus() == SERVER_STATUS_NOK)
            throw new Exception(sto.getError());


        return (User) sto.getResult();
    }

    @Override
    public void update(User user, HashMap<String, String> params) {

        params.forEach((k, v) -> {

            Method method = null;

            try {
                method = Class
                        .forName("models.User")
                        .getMethod("set" + k.substring(0, 1).toUpperCase() + k.substring(1), String.class);

                method.invoke(user, v);

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

        users.add(user);
    }

    @Override
    public List<User> getAll() {
//        return users;
        return null;
    }

    @Override
    public void save(User user) {
//        users.add(user);
    }


    @Override
    public void delete(User user) {
        users.remove(user);
    }



    private static void prepareData(User user, Map<String, String> params) {

        params.forEach((k, v) -> {

            Method method = null;

            try {
                method = Class
                        .forName("models.User")
                        .getMethod("set" + k.substring(0, 1).toUpperCase() + k.substring(1), String.class);

                method.invoke(user, v);

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
    }
}
