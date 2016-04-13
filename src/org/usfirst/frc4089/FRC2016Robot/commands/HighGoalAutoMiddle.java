package org.usfirst.frc4089.FRC2016Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HighGoalAutoMiddle extends CommandGroup {
    
    public  HighGoalAutoMiddle() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new FeedBallToBumper());
    	addParallel(new DriveFwdIndef(1.0));
    	addSequential(new Delay(2.25));
    	addParallel(new DriveFwdIndef(0.5));
    	addSequential(new Delay(1.25));
    	addParallel(new DriveRevIndef(0.35));
    	addSequential(new Delay(1.5));
    	addSequential(new MurderWheels());
    	addSequential(new CollectorExtend());
    	addSequential(new CollectorRetract(true));
    	addSequential(new Delay(1.0));
    	addSequential(new AutoShoot());
    }
}
