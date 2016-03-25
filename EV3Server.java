import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;

public class EV3Server {

  public static void main (String[] args) throws Exception {
    ServerSocket serv = new ServerSocket(1111);
    // Waits for client connection
    Socket s = serv.accept();
    System.out.println("Server connected successfully.");
    DataInputStream inStream = new DataInputStream(s.getInputStream());
    Motor.A.rotateTo(90);
    Motor.B.setSpeed(Motor.B.getMaxSpeed());
    while (true) {
      String action = inStream.readUTF();
      System.out.println(action);
      if (action.equals("a")) {
        hammerTime();
      } else if (action.equals("b")) {
        Motor.B.forward();
      } else if (action.equals("c")) {
        Motor.B.stop();
      } else if (action.equals("d")) {
        Motor.B.backward();
      } else {
        Sound.beep();
      }
    }
  }

  public static void hammerTime() {
    Motor.A.setSpeed(Motor.A.getMaxSpeed());
    for (int i = 0; i < 3; i++) {
      Motor.A.rotateTo(-80);
      Motor.A.rotateTo(0);
    }
  }
}
