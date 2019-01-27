package robot;

import edu.wpi.first.wpilibj.RobotBase;
import robot.deepspace.DeepspaceRobot;
import robot.demos.GyroRobot;

/** The java 'main' class */
public final class Main
{
  public static void main(String... args)
  {
    // Change this to select which robot code to run
    RobotBase.startRobot(GyroRobot::new);
  }
}
