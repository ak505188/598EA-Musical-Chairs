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
    leftWheel.synchronizeWith(new EV3LargeRegulatedMotor[] {rightWheel});
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
    leftWheel.startSynchronization();
    leftWheel.setSpeed(leftWheel.getMaxSpeed());
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    leftWheel.backward();
    rightWheel.backward();
    leftWheel.endSynchronization();
  }

  public static void stopDriving() {
    leftWheel.startSynchronization();
    leftWheel.stop();
    rightWheel.stop();
    leftWheel.endSynchronization();
  }

  public static void driveBackward() {
    leftWheel.startSynchronization();
    leftWheel.setSpeed(rightWheel.getMaxSpeed());
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    leftWheel.forward();
    rightWheel.forward();
    leftWheel.endSynchronization();
  }

  public static void driveLeft() {
    leftWheel.startSynchronization();
    leftWheel.setSpeed((float)(leftWheel.getMaxSpeed() / 6.5 ));
    leftWheel.backward();
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    rightWheel.backward();
    leftWheel.endSynchronization();
  }

  public static void driveRight() {
    leftWheel.startSynchronization();
    rightWheel.setSpeed((float)(rightWheel.getMaxSpeed() / 6.5));
    rightWheel.backward();
    leftWheel.setSpeed(leftWheel.getMaxSpeed());
    leftWheel.backward();
    leftWheel.endSynchronization();
  }

}
