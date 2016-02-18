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
import org.usfirst.frc4089.FRC2016Robot.Util.ClassifiedScore;
import org.usfirst.frc4089.FRC2016Robot.Util.ScoreCollection;
import org.usfirst.frc4089.FRC2016Robot.Util.VisionProcessing;

/**
 *
 */
public class AlignToVision extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	boolean _isAligned;
	double elapsedTime;
	double reCalTime;
	int nRecals;
	ClassifiedScore best;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AlignToVision() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        _isAligned = false;
        elapsedTime = 0;
        reCalTime = 0;
        nRecals = -1;
        best = null;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.ShiftLow();
    	_isAligned = false;
    	elapsedTime = 0;
    	reCalTime = 0;
    	nRecals = -1;//ignores the first
    	best = null;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elapsedTime = timeSinceInitialized() - elapsedTime;
    	reCalTime -= elapsedTime;
    	if(reCalTime <= 0)
    	{
    		//right now, we usually get about 4fps for some unknown reason, so
    		//recalibrate every 1/4 second. track number of recalibrations so we can limit if needed
    		nRecals++;
			ScoreCollection scores = VisionProcessing.scores;
			if(scores == null) return;
			best = scores.getBestTarget();
			if(best == null) return;
			if(Math.abs(best.AimingPos.x) < 0.05 || nRecals >= 12)
			{
				_isAligned = true;
				return;
			}
			reCalTime = 0.25;
    	}
    	double turnPow = best.AimingPos.x;//-1 to +1. how convenient.
    	Robot.driveTrain.Drive(turnPow, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _isAligned;
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
