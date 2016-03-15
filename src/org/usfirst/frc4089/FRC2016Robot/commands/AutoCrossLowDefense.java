package org.usfirst.frc4089.FRC2016Robot.commands;

import org.usfirst.frc4089.FRC2016Robot.Robot;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.Timer;
=======
>>>>>>> origin/master
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCrossLowDefense extends Command {

<<<<<<< HEAD
	double waitTime = 6.0;
=======
>>>>>>> origin/master
    public AutoCrossLowDefense() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
<<<<<<< HEAD
    	Timer.delay(waitTime);
=======
>>>>>>> origin/master
    	Robot.driveTrain.ShiftLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
<<<<<<< HEAD
        //Delay ourDelay = new Delay();
        //ourDelay.wait(5000);
=======
>>>>>>> origin/master
    	Robot.driveTrain.Drive(0, -1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
<<<<<<< HEAD
        return timeSinceInitialized() > 1.45 + waitTime;
=======
        return timeSinceInitialized() > 1.15;
>>>>>>> origin/master
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.Drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
<<<<<<< HEAD
    }
}
=======
    	end();
    }
}
>>>>>>> origin/master
