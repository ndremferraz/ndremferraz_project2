import java.net.ServerSocket;

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

    // getter methos for private field
    public ServerSocket getServerSocket() {
        return this.serversocket;
    }
}
