import java.net.Socket;
import java.net.ServerSocket;

public class EV3Client {

 public static void main (String[] args) throws Exception {
    Socket s = new Socket("10.0.1.1", 1111);
    System.out.println("Client should be connected?");

  }
}
