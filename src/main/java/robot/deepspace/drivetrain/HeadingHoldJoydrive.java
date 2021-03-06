package robot.deepspace.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import robot.deepspace.OI;

/** Command to drive for/back with joysick but hold heading */
public class HeadingHoldJoydrive extends Command
{
    private final DriveTrain drivetrain;
    private double heading;
    private boolean do_stop; 


    public HeadingHoldJoydrive(final DriveTrain drivetrain)
    {
        this.drivetrain = drivetrain;
        requires(drivetrain);
    }

    @Override
    public void initialize()
    {
        // Lock onto current heading when command starts
        heading = drivetrain.getHeading();
        do_stop = true;
    }

    @Override
    protected void execute()
    {
        drivetrain.setSpeed(OI.getSpeed());

        // Allow slow adjustment of PID setpoint
        // via left/right joystick axis
        double turn = OI.getTurn();
        if (Math.abs(turn) > 0.03)
            heading += 1.5*turn;
        drivetrain.setHeading(heading);
    }

    public void cancelbutdontstop()
    {
        do_stop = false;
        cancel();
    }

    @Override
    protected boolean isFinished()
    {
        // Keep running until cancelled
        return false;
    }

    @Override
    protected void end()
    {
        // When command is cancelled, stop the drivetrain and cancel PID
        if (do_stop)
            drivetrain.setSpeed(0);
        drivetrain.setHeading(Double.NaN);
    }
}
