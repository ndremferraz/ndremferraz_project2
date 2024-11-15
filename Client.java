import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    // creates a Socket instance, initializes when constructor is called
    private Socket clientsocket;

    public Client(String localAddress, int portnumber) {
        try {
            this.clientsocket = new Socket(localAddress, portnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handshake() {
        try {

            PrintWriter print = new PrintWriter(this.clientsocket.getOutputStream());
            print.println("12345");
            print.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {

            this.clientsocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getter methos for private field
    public Socket getSocket() {
        return clientsocket;
    }

}