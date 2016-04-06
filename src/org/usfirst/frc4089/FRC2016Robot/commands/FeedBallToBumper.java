package org.usfirst.frc4089.FRC2016Robot.commands;

import org.usfirst.frc4089.FRC2016Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeedBallToBumper extends Command {

    public FeedBallToBumper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.collector.extendCollector(1);
    	Robot.collector.runCollectorWheelsOut(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > 2.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.collector.stopCollector();
    	Robot.collector.stopCollectorWheels();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
