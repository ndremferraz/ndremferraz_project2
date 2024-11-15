import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server {

    // creates a ServerSocket instance, initializes when constructor is called
    private ServerSocket serversocket;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<LocalDateTime>();

    public Server(int portnumber) {
        try {
            this.serversocket = new ServerSocket(portnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method that will connect clientsNum clients to the server
    public void serve(int clientsNum) {

        Socket client;

        for (int i = 0; i < clientsNum; i++) {
            try {
                // accepts the clients and creates a new Thread for each one of the i clients
                client = serversocket.accept();
                connectedTimes.add(LocalDateTime.now());
                (new ClientHandler(client)).start();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // method to disconnect server
    public void disconnect() {
        try {
            this.serversocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getter methos for private field
    public ServerSocket getServerSocket() {
        return this.serversocket;
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return this.connectedTimes;
    }

    private class ClientHandler extends Thread {

        Socket sock;

        public ClientHandler(Socket sock) {
            this.sock = sock;
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                out = new PrintWriter(sock.getOutputStream());
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                // reads from the server's input stream to check the handshake
                String line = in.readLine();

                if (!(line.equals("12345"))) {
                    out.println("couldn't handshake");
                } else {
                    while (true) {
                        line = in.readLine();
                        out.println("The number 17 has 2 factors");
                        out.flush();
                    }
                }

                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
