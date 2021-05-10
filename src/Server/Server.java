package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService threadPoolExecutor;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop = false;
        this.threadPoolExecutor = Executors.newCachedThreadPool();
    }

    public void start() {
        Thread tmp = new Thread(this);
        tmp.start();
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());
                    threadPoolExecutor.execute(() -> {
                        try {
                            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
//                            clientSocket.getInputStream().close();
//                            clientSocket.getOutputStream().close();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                } catch (SocketTimeoutException e){
                    System.out.println("Socket timeout");
                }
            }
            threadPoolExecutor.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        System.out.println("The server stopped running");
        stop = true;
    }
}
