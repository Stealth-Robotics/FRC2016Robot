// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4089.FRC2016Robot;

import org.usfirst.frc4089.FRC2016Robot.Util.NSSensors;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DoubleSolenoid driveTrainLeftDriveShifter;
    public static DoubleSolenoid driveTrainRightDriveShifter;
    public static DigitalInput driveTrainLeftLinePseudoLimit;
    public static DigitalInput driveTrainRightLinePseudoLimit;
    public static CANTalon driveTrainMDrive1;
    public static CANTalon driveTrainMDrive2;
    public static CANTalon driveTrainMDrive3;
    public static CANTalon driveTrainMDrive4;
    public static RobotDrive driveTrainRDrive;
    public static CANTalon whipClimberWinchMotor;
    public static DoubleSolenoid whipClimberDSLatch;
    public static CANTalon spinShooterLeftSpin;
    public static CANTalon spinShooterRightSpin;
    public static RobotDrive spinShooterShotDrive;
    public static DoubleSolenoid spinShooterSpinShooterElevate;
    public static DoubleSolenoid catapultShooterLPiston;
    public static DoubleSolenoid catapultShooterRPiston;
    public static DigitalInput barClimbLSTop;
    public static DigitalInput barClimbLSBot;
    public static CANTalon barClimbWinchMotor;
    public static DoubleSolenoid barClimbDSRaiseLower;
    public static CANTalon collectorMCollector;
    public static AnalogGyro sensorsAnalogGyro1;
    public static DigitalInput sensorsDigitalAccel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainLeftDriveShifter = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("DriveTrain", "LeftDriveShifter", driveTrainLeftDriveShifter);
        
        driveTrainRightDriveShifter = new DoubleSolenoid(0, 2, 3);
        LiveWindow.addActuator("DriveTrain", "RightDriveShifter", driveTrainRightDriveShifter);
        
        driveTrainLeftLinePseudoLimit = new DigitalInput(2);
        LiveWindow.addSensor("DriveTrain", "LeftLinePseudoLimit", driveTrainLeftLinePseudoLimit);
        
        driveTrainRightLinePseudoLimit = new DigitalInput(3);
        LiveWindow.addSensor("DriveTrain", "RightLinePseudoLimit", driveTrainRightLinePseudoLimit);
        
        driveTrainMDrive1 = new CANTalon(1);
        LiveWindow.addActuator("DriveTrain", "MDrive1", driveTrainMDrive1);
        
        driveTrainMDrive2 = new CANTalon(2);
        LiveWindow.addActuator("DriveTrain", "MDrive2", driveTrainMDrive2);
        
        driveTrainMDrive3 = new CANTalon(3);
        LiveWindow.addActuator("DriveTrain", "MDrive3", driveTrainMDrive3);
        
        driveTrainMDrive4 = new CANTalon(4);
        LiveWindow.addActuator("DriveTrain", "MDrive4", driveTrainMDrive4);
        
        driveTrainRDrive = new RobotDrive(driveTrainMDrive1, driveTrainMDrive2,
              driveTrainMDrive3, driveTrainMDrive4);
        
        driveTrainRDrive.setSafetyEnabled(true);
        driveTrainRDrive.setExpiration(0.1);
        driveTrainRDrive.setSensitivity(0.5);
        driveTrainRDrive.setMaxOutput(1.0);

        whipClimberWinchMotor = new CANTalon(7);
        LiveWindow.addActuator("WhipClimber", "WinchMotor", whipClimberWinchMotor);
        
        whipClimberDSLatch = new DoubleSolenoid(1, 4, 5);
        LiveWindow.addActuator("WhipClimber", "DSLatch", whipClimberDSLatch);
        
        spinShooterLeftSpin = new CANTalon(5);
        LiveWindow.addActuator("SpinShooter", "LeftSpin", spinShooterLeftSpin);
        
        spinShooterRightSpin = new CANTalon(6);
        LiveWindow.addActuator("SpinShooter", "RightSpin", spinShooterRightSpin);
        
        spinShooterShotDrive = new RobotDrive(spinShooterLeftSpin, spinShooterRightSpin);
        
        spinShooterShotDrive.setSafetyEnabled(true);
        spinShooterShotDrive.setExpiration(0.1);
        spinShooterShotDrive.setSensitivity(0.5);
        spinShooterShotDrive.setMaxOutput(1.0);

        spinShooterSpinShooterElevate = new DoubleSolenoid(1, 0, 1);
        LiveWindow.addActuator("SpinShooter", "SpinShooterElevate", spinShooterSpinShooterElevate);
        
        catapultShooterLPiston = new DoubleSolenoid(0, 4, 5);
        LiveWindow.addActuator("CatapultShooter", "LPiston", catapultShooterLPiston);
        
        catapultShooterRPiston = new DoubleSolenoid(0, 6, 7);
        LiveWindow.addActuator("CatapultShooter", "RPiston", catapultShooterRPiston);
        
        barClimbLSTop = new DigitalInput(4);
        LiveWindow.addSensor("BarClimb", "LSTop", barClimbLSTop);
        
        barClimbLSBot = new DigitalInput(5);
        LiveWindow.addSensor("BarClimb", "LSBot", barClimbLSBot);
        
        barClimbWinchMotor = new CANTalon(8);
        LiveWindow.addActuator("BarClimb", "WinchMotor", barClimbWinchMotor);
        
        barClimbDSRaiseLower = new DoubleSolenoid(1, 2, 3);
        LiveWindow.addActuator("BarClimb", "DSRaiseLower", barClimbDSRaiseLower);
        
        collectorMCollector = new CANTalon(9);
        LiveWindow.addActuator("Collector", "MCollector", collectorMCollector);
        
        sensorsAnalogGyro1 = new AnalogGyro(0);
        LiveWindow.addSensor("Sensors", "AnalogGyro1", sensorsAnalogGyro1);
        sensorsAnalogGyro1.setSensitivity(0.007);
        sensorsDigitalAccel = new DigitalInput(6);
        LiveWindow.addSensor("Sensors", "DigitalAccel", sensorsDigitalAccel);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        NSSensors.init();
    }
}
