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
import org.usfirst.frc4089.FRC2016Robot.NonStandardSensors.NSSensors;
import org.usfirst.frc4089.FRC2016Robot.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class SpinShooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon leftSpin = RobotMap.spinShooterLeftSpin;
    private final CANTalon rightSpin = RobotMap.spinShooterRightSpin;
    private final RobotDrive shotDrive = RobotMap.spinShooterShotDrive;
    private final DoubleSolenoid spinShooterElevate = RobotMap.spinShooterSpinShooterElevate;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private final Counter lsCounter = NSSensors.leftSpinRateCounter;
    private final Counter rsCounter = NSSensors.rightSpinRateCounter;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void Spin(double power)
    {
    	Spin(power, power);
    }
    
    public void Spin(double left, double right)
    {
    	shotDrive.setLeftRightMotorOutputs(left, right);
    }
    
    public double getLeftRPMs()
    {
    	return lsCounter.getRate() * 60;
    }
    
    public double getRightRPMs()
    {
    	return rsCounter.getRate() * 60;
    }
    
    public double getAvgRPMs()
    {
    	return (getLeftRPMs() + getRightRPMs()) / 2.0;
    }
}

