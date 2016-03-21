// Uncomment this if you are building in Eclipse
// package catapult;

import java.io.File;
import java.io.FileNotFoundException;

import lejos.robotics.*;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.hardware.motor.*;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Sound;
import lejos.hardware.Sounds;
import lejos.hardware.motor.Motor;
import lejos.remote.ev3.RemoteEV3;

public class BTConnect {

  public static void main(String[] args) {
    RemoteEV3 battleBot = null;
    try {
      battleBot = new RemoteEV3("10.0.1.1");
      battleBot.setDefault();
    } catch (Exception e) {
      System.err.println("Error: " + e);
      System.exit(1);
    }

    Sound.beep();
    RMIRegulatedMotor wheels = battleBot.createRegulatedMotor("B", 'L');

    // Infinite loop for running bot
    while(true) {
      if (Button.ESCAPE.isDown()) {
        try {
          wheels.stop(true);
        } catch (Exception e) {
          System.err.println("Error: " + e);
        }
        System.exit(1);
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
