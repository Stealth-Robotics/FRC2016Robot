package org.usfirst.frc4089.FRC2016Robot.commands;

import org.usfirst.frc4089.FRC2016Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFingerDown extends Command {

	boolean m_quit;
    public MoveFingerDown(boolean quit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.collector);
    	
    	m_quit = quit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.collector.setFingerPos(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_quit;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
