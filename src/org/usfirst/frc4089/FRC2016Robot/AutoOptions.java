package org.usfirst.frc4089.FRC2016Robot;

import org.usfirst.frc4089.FRC2016Robot.commands.AutoCrossLowDefense;
import org.usfirst.frc4089.FRC2016Robot.commands.AutoDriveOverLowObstacle3_4;
import org.usfirst.frc4089.FRC2016Robot.commands.AutoLowBar1;
import org.usfirst.frc4089.FRC2016Robot.commands.HighGoalAuto;
import org.usfirst.frc4089.FRC2016Robot.commands.LowGoalAuto;

import edu.wpi.first.wpilibj.command.Command;

public class AutoOptions {
	public static final String[] Options = new String[] {
			"Cross Low Defense",
			"Low Goal Shot",
			"High Goal Shot"};
	
	//get each individual command, no duplicates
	private static Command getCommand(int i)
	{
		Command[] allCommands = new Command[] {
			new AutoCrossLowDefense(),
			new LowGoalAuto(),
			new HighGoalAuto()
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
				getCommand(2)
		};
		return AssociatedCommands;
	}
	
	public static Command getAutoCommandFromString(String chosen)
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
