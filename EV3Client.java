import java.net.Socket;
import java.net.ServerSocket;

public class EV3Client {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Insert IP address & port number as arguments");
      System.out.println("Exiting now");
      System.exit(0);
    }
    connectToEV3(args[0], Integer.parseInt(args[1]));
  }

  public static void connectToEV3(String ipAddress, int portNumber) {
    try {
      Socket s = new Socket(ipAddress, portNumber);
      System.out.println("Client connected successfully.");
    } catch (Exception e) {
      System.err.println("Error: " + e);
      System.err.println("Exiting now.");
      System.exit(0);
    }
  }

}

