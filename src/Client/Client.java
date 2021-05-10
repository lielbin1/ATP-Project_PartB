package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int port;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int port, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.port = port;
        this.strategy = strategy;
    }

    public void communicateWithServer() {
        try {
            Socket serverSocket = new Socket(this.serverIP, this.port);
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + port);
            this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
