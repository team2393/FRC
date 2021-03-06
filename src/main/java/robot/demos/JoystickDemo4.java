package robot.demos;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import robot.BasicRobot;
import robot.demos.commands.DriveByJoystick;
import robot.demos.commands.HoldHeading;
import robot.demos.subsystems.DriveSubsystem;
import robot.parts.ContinuousRotationServo;

public class JoystickDemo4 extends BasicRobot
{
    // Wrap the motors into a DriveSubsystem
    final DifferentialDrive drive = new DifferentialDrive(new ContinuousRotationServo(0), new ContinuousRotationServo(1));
    private DriveSubsystem drive_subsys = new DriveSubsystem(drive);
    
    // Use gryro
    private Gyro gyro = new ADXRS450_Gyro();
    
    // Joystick
    private Joystick joystick = new Joystick(0);
    
    // Commands
    private Command drive_by_stick = new DriveByJoystick(drive_subsys, joystick);
    private Command hold_heading = new HoldHeading(drive_subsys, gyro);
     
    @Override
    public void robotInit()
    {
        super.robotInit();
        System.out.println("In teleop mode, drive by Joystick");
        System.out.println("In autonomous mode, hold heading");
        
        // Ignore small joystick moves
        drive.setDeadband(0.1);
    }
    
    @Override
    public void robotPeriodic()
    {
        // Run scheduler in any mode
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {
        // In teleop, start the command for driving by joystick
        drive_by_stick.start();
    }

    @Override
    public void autonomousInit()
    {
        // In auto, start the command holding the heading
        hold_heading.start();
    }
}
