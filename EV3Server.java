import java.net.Socket;
import java.net.ServerSocket;

import lejos.hardware.Sound;

public class EV3Server {

 public static void main (String[] args) throws Exception {
    ServerSocket serv = new ServerSocket(1111);
    // Waits for client connection
    Socket s = serv.accept();
    System.out.println("Server connected successfully.");

  }
}
