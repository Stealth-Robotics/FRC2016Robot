package org.usfirst.frc4089.FRC2016Robot;

import org.usfirst.frc4089.FRC2016Robot.commands.AutoCrossLowDefenseNew;
import org.usfirst.frc4089.FRC2016Robot.commands.AutoCrossPortcullis;
import org.usfirst.frc4089.FRC2016Robot.commands.HighGoalAuto;
import org.usfirst.frc4089.FRC2016Robot.commands.HighGoalAutoMiddle;
import org.usfirst.frc4089.FRC2016Robot.commands.LowGoalAuto;

import edu.wpi.first.wpilibj.command.Command;

public class AutoOptions {
	public static final String[] Options = new String[] {
			"Cross Low Defense",
			"Portcullis",
			"Low Goal Shot",
			"High Goal Shot (Low bar)",
			"High Goal Shot (Stationary Middle)"};
	
	//get each individual command, no duplicates
	private static Command getCommand(int i)
	{
		Command[] allCommands = new Command[] {
			new AutoCrossLowDefenseNew(),
			new AutoCrossPortcullis(),
			new LowGoalAuto(),
			new HighGoalAuto(),
			new HighGoalAutoMiddle()
		};
		return allCommands[i];
	}
	
	public static Command getAutoCommandFromString(String chosen)
	{
		for(int i = 0; i < Options.length; i++)
		{
			if(Options[i].equals(chosen)){
				return getCommand(i);
			}
		}
		return null; //for now
	}
}
