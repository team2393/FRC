package robot.demos;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.BasicRobot;
import robot.parts.PDPController;

/** Demo of turning output of Pneumatic Control Module on/off */
public class PneumaticDemo extends BasicRobot
{
    private final int PCM_ID = 0;
    private final Solenoid sol1 = new Solenoid(PCM_ID, 1);
    private final Solenoid sol3 = new Solenoid(PCM_ID, 3);
    Joystick joystick = new Joystick(0);

    @Override
    public void robotInit()
    {
        super.robotInit();

        // Allow control via dashboard
        SmartDashboard.putData("Sol1", sol1);
        SmartDashboard.putData("Sol2", sol3);
    }

    @Override
    public void teleopPeriodic()
    {
        final boolean on = joystick.getRawButton(PDPController.X_BUTTON);
        sol1.set(on);
        sol3.set(on);
    }

    @Override
    public void autonomousPeriodic()
    {
        // Turn solenoid on/off every 2 seconds
        if (((System.currentTimeMillis() / 2000) % 2) == 1)
            sol1.set(true);
        else
            sol1.set(false);
    }
}