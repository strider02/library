package backendControllers;

import models.Model;
import models.Role;
import models.User;
import transfers.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import static utillities.OPERATIONS.*;
import static utillities.STATUSES.SERVER_STATUS_NOK;
import static utillities.STATUSES.SERVER_STATUS_OK;

public class Resolver extends Thread {

    private Socket socket;
    private boolean isActive;
    private Communication communication;


    public Resolver(Socket socket, Communication communication) {
        this.socket = socket;
        this.communication = communication;
        this.isActive = true;
    }

    @Override
    public void run() {


        while (isActive) {
            try {

                transfers.Client cto = receive();

                transfers.Server sto = new Server();

                switch (cto.getOperation()) {
                    case GET_USER -> {

                        try {
                            Model result = Controller.getInstance().getUser((User) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case GET_USER_ROLE -> {
                        try {
                            Model result = Controller.getInstance().getUserRole((Role) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case ADD_USER -> {
                        try {
                            boolean result = Controller.getInstance().addUser((User) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case ADD_AND_RETURN_USER -> {
                        try {
                            User result = Controller.getInstance().addAndReturnUser((User) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case GET_USERS -> {
                        try {
                            List<User> result = Controller.getInstance().getAllUsers((List) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case FIND_ROLE -> {
                        try {
                            Role result = Controller.getInstance().findRole((Role) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case GET_ROLES -> {
                        try {
                            List<Role> result = Controller.getInstance().getAllRoles((List) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case GET_USERS_WITH_ROLE -> {
                        try {
                            List<User> result = Controller.getInstance().getUsersWithRole((String) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }
                    case FIND_USER -> {
                        try {
                            User result = Controller.getInstance().findUser((int) cto.getParameter());
                            sto.setResult(result);
                            sto.setStatus(SERVER_STATUS_OK);
                        } catch (Exception e) {
                            sto.setStatus(SERVER_STATUS_NOK);
                            sto.setError(e.getMessage());
                        }
                        respond(sto);
                    }

                }

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    private transfers.Client receive() {

        transfers.Client cto = new transfers.Client();

        try {

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            cto = (transfers.Client) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return cto;
    }

    private void respond(transfers.Server sto) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
