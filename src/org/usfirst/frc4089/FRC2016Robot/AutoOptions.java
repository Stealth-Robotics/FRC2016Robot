package org.usfirst.frc4089.FRC2016Robot;

import org.usfirst.frc4089.FRC2016Robot.commands.AutoDriveOverLowObstacle3_4;
import org.usfirst.frc4089.FRC2016Robot.commands.AutoLowBar1;

import edu.wpi.first.wpilibj.command.Command;

public class AutoOptions {
	public static final String[] Options = new String[] {
    		"Low Bar 1",
    		"Moat/Rampart 3",
    		"Moat/Rampart 4",
    		"Rock Wall/Rough Terrain 3",
    		"Rock Wall/Rough Terrain 4",
    		"Moat/Rampart 2",
    		"Rock Wall/Rough Terrain 2",
    		"ChevyChase 3",
    		"ChevyChase 4",
    		"ChevyChase 2",
    		"Moat/Rampart 5",
    		"Rock Wall/Rough Terrain 5",
    		"ChevyChase 5",
    		"Any Other (Disabled)"};
	
	//get each individual command, no duplicates
	private static Command getCommand(int i)
	{
		Command[] allCommands = new Command[] {
			new AutoLowBar1(),
			new AutoDriveOverLowObstacle3_4(false),
			new AutoDriveOverLowObstacle3_4(true),
			null, //driveOverLowObstacle2
			null, //driveOverChevyChase3
			null, //driveOverChevyChase4
			null, //driveOverChevyChase2
			null, //driveOverLowObstacle5
			null, //driveOverChevyChase5
			null //disabled
		};
		return allCommands[i];
	}
	
	//can't be constants because breaks on init
	//done this way in order to avoid duplicate commands taking extra memory
	public static Command[] getAssociatedCommands()
	{
		Command[] AssociatedCommands = new Command[] {
			//Add commands
				getCommand(0),
				getCommand(1),
				getCommand(2),
				getCommand(1),
				getCommand(2),
				getCommand(3),
				getCommand(3),
				getCommand(4),
				getCommand(5),
				getCommand(6),
				getCommand(7),
				getCommand(7),
				getCommand(8),
				getCommand(9)
		};
		return AssociatedCommands;
	}
	
	public Command getAutoCommandFromString(String[] chosen)
	{
		Command[] AssociatedCommands = getAssociatedCommands();
		if(Options.length != AssociatedCommands.length) return null;
		for(int i = 0; i < Options.length; i++)
		{
			if(Options[i].equals(chosen)){
				return AssociatedCommands[i];
			}
		}
		return null; //for now
	}
}
