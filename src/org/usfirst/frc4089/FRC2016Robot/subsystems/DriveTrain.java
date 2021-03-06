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
import org.usfirst.frc4089.FRC2016Robot.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DigitalInput leftLinePseudoLimit = RobotMap.driveTrainLeftLinePseudoLimit;
    private final DigitalInput rightLinePseudoLimit = RobotMap.driveTrainRightLinePseudoLimit;
    private final CANTalon mDrive1 = RobotMap.driveTrainMDrive1;
    private final CANTalon mDrive2 = RobotMap.driveTrainMDrive2;
    private final CANTalon mDrive3 = RobotMap.driveTrainMDrive3;
    private final CANTalon mDrive4 = RobotMap.driveTrainMDrive4;
    private final RobotDrive rDrive = RobotMap.driveTrainRDrive;
    private final Solenoid driveShifter = RobotMap.driveTrainDriveShifter;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new UserDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void Drive(Joystick j)
    {
    	Drive(j.getZ(), j.getY());
    }
    
    public void Drive(double turn, double pow)
    {
    	rDrive.arcadeDrive(pow, turn);
    }
    
    public boolean DriveToLine()
    {
    	double l = 0.75, r = 0.75;
    	ShiftLow();
    	if(IsLeftLimitTriggered()) l = 0;
    	if(IsRightLimitTriggered()) r = 0;
    	rDrive.setLeftRightMotorOutputs(l, r);
    	return l == 0 && r == 0;
    }
    
    public void encoderReset()
    {
    	mDrive1.setEncPosition(0);
    	mDrive3.setEncPosition(0);
    }
    
    public boolean DriveToDist(double distance)
    {
    	return DriveToDist(distance, distance);
    }
    
    public boolean DriveToDist(double left, double right)
    {
    	ShiftLow();
    	double leftDist = mDrive1.getEncPosition();
    	double rightDist = mDrive3.getEncPosition();
    	
    	double leftPow = 0.75, rightPow = 0.75;
    	if(leftDist >= left) leftPow = 0;
    	if(rightDist >= right) rightPow = 0;
    	rDrive.setLeftRightMotorOutputs(leftPow, rightPow);
    	return leftPow == 0 && rightPow == 0;
    }
    
    public void Rotate(double rotPow)
    {
    	ShiftLow();
    	Drive(rotPow, 0);
    }
    
    public void ToggleShifters()
    {
    	boolean shift = !driveShifter.get();
    	driveShifter.set(shift);
    }
    
    public void ShiftLow()
    {
    	driveShifter.set(false);
    }
    
    public void ShiftHigh()
    {
    	driveShifter.set(true);
    }
    
    public boolean IsLeftLimitTriggered()
    {
    	return !leftLinePseudoLimit.get();
    }
    
    public boolean IsRightLimitTriggered()
    {
    	return !rightLinePseudoLimit.get();
    }
}

