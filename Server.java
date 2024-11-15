import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // creates a ServerSocket instance, initializes when constructor is called
    private ServerSocket serversocket;

    public Server(int portnumber) {
        try {
            this.serversocket = new ServerSocket(portnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serve(int clientsNum) {

        Socket client;

        for (int i = 0; i < clientsNum; i++) {
            try {
                client = serversocket.accept();

                PrintWriter out = new PrintWriter(client.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String line = in.readLine();

                if (line.equals("12345")) {

                } else {
                    out.println("couldn't handshake");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

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
}
