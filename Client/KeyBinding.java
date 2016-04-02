import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.KeyStroke.*;
import javax.swing.event.*;
import javax.swing.Action;
import java.awt.*;
import java.awt.event.*;

public class KeyBinding extends JFrame {


  private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
  public static Socket connection;
  public static DataOutputStream outStream;

  static JTextArea controls = new JTextArea(10, 40);

  public KeyBinding() {
    super("KeyBinding");
    setSize(200, 200);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    controls.setEditable(false);
    controls.append("w:\tforward\n");
    controls.append("a:\tleft\n");
    controls.append("d:\tright\n");
    controls.append("s:\tstop\n");
    controls.append("x:\tback\n");
    controls.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "forward");
    controls.getInputMap(IFW).put(KeyStroke.getKeyStroke("released W"), "stop");
    controls.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "left");
    controls.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "right");
    controls.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), "backward");
    controls.getActionMap().put("forward", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          outStream.writeUTF("w");
        } catch (Exception E) {
          System.err.println("Error with stop: " + e);
        }
      }
    });
    controls.getActionMap().put("stop", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          outStream.writeUTF("s");
        } catch (Exception E) {
          System.err.println("Error with stop: " + e);
        }
      }
    });
    this.add(controls);
    this.setVisible(true);
  }



  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Insert IP address & port number as arguments");
      System.out.println("Exiting now");
      System.exit(0);
    }
    Socket connection = connectToEV3(args[0], Integer.parseInt(args[1]));
    DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
    new KeyBinding();
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

