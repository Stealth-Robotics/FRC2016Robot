// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4089.FRC2016Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4089.FRC2016Robot.Robot;

/**
 *
 */
public class CollectorRetract extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private boolean m_shouldStage;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    
    private boolean startedStaged;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public CollectorRetract(boolean shouldStage) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_shouldStage = shouldStage;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        startedStaged = false;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.collector);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startedStaged = Robot.collector.getCollectorStage();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.collector.retractCollector(1.0);
    	Robot.collector.runCollectorWheelsIn(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean colStage = Robot.collector.getCollectorStage();
    	boolean colIn = Robot.collector.getCollectorIn();
    	boolean stagedCondition = !startedStaged && m_shouldStage && colStage;
    	boolean retractedCondition = (startedStaged || !m_shouldStage) && colIn;
        return stagedCondition || retractedCondition;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.collector.stopCollector();
    	Robot.collector.stopCollectorWheels();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
