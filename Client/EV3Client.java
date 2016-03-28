import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.util.Scanner;

public class EV3Client {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Insert IP address & port number as arguments");
      System.out.println("Exiting now");
      System.exit(0);
    }
    Socket connection = connectToEV3(args[0], Integer.parseInt(args[1]));
    DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
    Scanner in = new Scanner(System.in);
    while (true) {
      String action = in.next();
      System.out.println(action);
      outStream.writeUTF(action);
    }
  }

  public static Socket connectToEV3(String ipAddress, int portNumber) {
    try {
      Socket s = new Socket(ipAddress, portNumber);
      System.out.println("Client connected successfully.");
      return s;
    } catch (Exception e) {
      System.err.println("Error: " + e);
      System.err.println("Exiting now.");
      System.exit(0);
      return null;
    }
  }

}

