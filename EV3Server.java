import java.net.Socket;
import java.net.ServerSocket;

public class EV3Server {

 public static void main (String[] args) throws Exception {
    ServerSocket serv = new ServerSocket(1111);
    Socket s = serv.accept();
    System.out.println("Server should be waiting right now.");

  }
}
