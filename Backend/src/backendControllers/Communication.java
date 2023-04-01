package backendControllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends Thread {

    private boolean isDone;

    public Communication() {
        this.isDone = false;
    }

    public void startServer() throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);

        System.out.println("Server is up and running!");

        while (!isDone) {
            Socket socket = serverSocket.accept();

            System.out.println("Client has been successfully connected!");

            Resolver resolver = new Resolver(socket, this);

            resolver.start();
        }

        serverSocket.close();

    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
