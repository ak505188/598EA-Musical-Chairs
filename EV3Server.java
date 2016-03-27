import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;

public class EV3Server {
  public static EV3LargeRegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.A);
  public static EV3LargeRegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.D);

  public static void main (String[] args) throws Exception {
    System.out.println("Server ready for connection.");
    ServerSocket serv = new ServerSocket(1111);
    // Waits for client connection
    Socket s = serv.accept();
    System.out.println("Server connected successfully.");
    DataInputStream inStream = new DataInputStream(s.getInputStream());
    // Motor.A.rotateTo(90);
    while (true) {
      String action = inStream.readUTF();
      System.out.println(action);
      if (action.equals("w")) {
        driveForward();
      } else if (action.equals("s")) {
        stopDriving();
      } else if (action.equals("a")) {
        driveLeft();
      } else if (action.equals("d")) {
        driveRight();
      } else if (action.equals("x")) {
        driveBackward();
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

  public static void driveForward() {
    leftWheel.setSpeed(leftWheel.getMaxSpeed());
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    leftWheel.backward();
    rightWheel.backward();
  }

  public static void stopDriving() {
    leftWheel.stop();
    rightWheel.stop();
  }

  public static void driveBackward() {
    leftWheel.forward();
    rightWheel.forward();
  }

  public static void driveLeft() {
    leftWheel.setSpeed((float)(leftWheel.getMaxSpeed() / 5.0 ));
    leftWheel.backward();
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    rightWheel.backward();
  }

  public static void driveRight() {
    rightWheel.setSpeed((float)(rightWheel.getMaxSpeed() / 5.0));
    rightWheel.backward();
    leftWheel.setSpeed(leftWheel.getMaxSpeed());
    leftWheel.backward();
  }

}
