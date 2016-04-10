import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import java.io.EOFException;

public class EV3Server {
  public static EV3LargeRegulatedMotor rightWheel = new EV3LargeRegulatedMotor(MotorPort.A);
  public static EV3LargeRegulatedMotor leftWheel = new EV3LargeRegulatedMotor(MotorPort.D);
  public static EV3LargeRegulatedMotor hammer = new EV3LargeRegulatedMotor(MotorPort.C);
  private static int hammerPos = 0;


  public static void main (String[] args) throws Exception {
    leftWheel.synchronizeWith(new EV3LargeRegulatedMotor[] {rightWheel});
    hammer.rotate(-195);
    System.out.println("Server ready for connection.");
    ServerSocket serv = new ServerSocket(1111);
    // Waits for client connection
    Socket s = serv.accept();
    System.out.println("Server connected successfully.");
    DataInputStream inStream = new DataInputStream(s.getInputStream());
    // Motor.A.rotateTo(90);
    while (true) {
      String action = null;
      // Used to exit gracefully when client exits
      try {
        action = inStream.readUTF();
      } catch(EOFException e) {
        System.err.println("Lost connection.");
        System.exit(1);
      }
      // System.out.println(action);
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
      } else if (action.equals("h")) {
        hammerTime();
      } else if (action.equals("hammerup")) {
        hammerUp();
      } else if (action.equals("hammerdown")) {
        hammerDown();
      } else if (action.equals("hammerstop")) {
        hammerStop();
      } else {
        Sound.beep();
      }
    }
  }

  public static void hammerTime() {
    hammer.setSpeed((hammer.getMaxSpeed() / 2));
    if (hammerPos == 0) {
        hammer.rotate(195, true);
        hammerPos = 1;
    } else {
        hammer.rotate(-195, true);
        hammerPos = 0;
    }
  }

  public static void hammerUp() {
    hammer.backward();
  }

  public static void hammerDown() {
    hammer.forward();
  }

  public static void hammerStop() {
    hammer.stop();
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
    // leftWheel.setSpeed((float)(leftWheel.getMaxSpeed() / 6.5 ));
    leftWheel.setSpeed(leftWheel.getMaxSpeed());
    leftWheel.forward();
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    rightWheel.backward();
    // System.out.println("Left wheel speed: " + leftWheel.getSpeed() + "Right wheel speed: " + rightWheel.getSpeed());
    leftWheel.endSynchronization();
  }

  public static void driveRight() {
    leftWheel.startSynchronization();
    // rightWheel.setSpeed((float)(rightWheel.getMaxSpeed() / 6.5));
    rightWheel.setSpeed(rightWheel.getMaxSpeed());
    rightWheel.forward();
    leftWheel.setSpeed(leftWheel.getMaxSpeed());
    leftWheel.backward();
    // System.out.println("Left wheel speed: " + leftWheel.getSpeed() + "Right wheel speed: " + rightWheel.getSpeed());
    leftWheel.endSynchronization();
  }

}
