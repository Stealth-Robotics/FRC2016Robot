package org.usfirst.frc4089.FRC2016Robot.Util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Utilities {
	public static double AutoDelay()
	{
		return SmartDashboard.getNumber("TurnTime");
	}
}
