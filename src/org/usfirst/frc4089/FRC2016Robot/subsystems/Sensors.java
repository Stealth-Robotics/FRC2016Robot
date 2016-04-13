// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4089.FRC2016Robot.subsystems;

import org.usfirst.frc4089.FRC2016Robot.RobotMap;
import org.usfirst.frc4089.FRC2016Robot.Util.NSSensors;
import org.usfirst.frc4089.FRC2016Robot.commands.*;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;


/**
 *
 */
public class Sensors extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogGyro analogGyro1 = RobotMap.sensorsAnalogGyro1;
    private final DigitalInput digitalAccel = RobotMap.sensorsDigitalAccel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    private final Accelerometer acc = NSSensors.builtInAcc;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public double[] getAcc()
    {
    	return new double[] { acc.getX(), acc.getY(), acc.getZ() };
    }
    
    public void resetGyro()
    {
    	analogGyro1.reset();
    }
    
    public void calGyro()
    {
    	analogGyro1.calibrate();
    }
    
    public double getAngle()
    {
    	return analogGyro1.getAngle() % 360;
    }
}

