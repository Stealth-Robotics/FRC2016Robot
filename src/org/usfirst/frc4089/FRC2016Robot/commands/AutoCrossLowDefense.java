package org.usfirst.frc4089.FRC2016Robot.commands;

import org.usfirst.frc4089.FRC2016Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCrossLowDefense extends Command {

	double waitTime = 10.0;
    public AutoCrossLowDefense() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Timer.delay(waitTime);
    	Robot.driveTrain.ShiftLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.Drive(0, -1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > 2.45 + waitTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.Drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}