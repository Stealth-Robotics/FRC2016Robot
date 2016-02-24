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

import org.usfirst.frc4089.FRC2016Robot.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shifter;
    public JoystickButton visionAlign;
    public JoystickButton trigger;
    public JoystickButton extendSkyPoleBtn;
    public JoystickButton climbUpBtn;
    public JoystickButton climbDownBtn;
    public JoystickButton shooterRaiser;
    public JoystickButton shooterLowerer;
    public JoystickButton spin;
    public Joystick driveJS;
    public JoystickButton collectorExtender;
    public JoystickButton collectorRetractor;
    public JoystickButton collecterInRoller;
    public JoystickButton collectorOutRoller;
    public JoystickButton joystickButton1;
    public Joystick utilJS;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        utilJS = new Joystick(1);
        
        joystickButton1 = new JoystickButton(utilJS, 11);
        joystickButton1.whenPressed(new PusherGoToAngle(180.0));
        collectorOutRoller = new JoystickButton(utilJS, 4);
        collectorOutRoller.whileHeld(new CollectorWheelOut());
        collecterInRoller = new JoystickButton(utilJS, 1);
        collecterInRoller.whileHeld(new CollectorWheelIn());
        collectorRetractor = new JoystickButton(utilJS, 2);
        collectorRetractor.whenPressed(new CollectorRetract(true));
        collectorExtender = new JoystickButton(utilJS, 3);
        collectorExtender.whenPressed(new CollectorExtend());
        driveJS = new Joystick(0);
        
        spin = new JoystickButton(driveJS, 8);
        spin.whileHeld(new Shoot());
        shooterLowerer = new JoystickButton(driveJS, 12);
        shooterLowerer.whenPressed(new ShooterLower());
        shooterRaiser = new JoystickButton(driveJS, 10);
        shooterRaiser.whenPressed(new ShooterRaise());
        climbDownBtn = new JoystickButton(driveJS, 11);
        climbDownBtn.whileHeld(new ClimbDown());
        climbUpBtn = new JoystickButton(driveJS, 9);
        climbUpBtn.whileHeld(new ClimbUp());
        extendSkyPoleBtn = new JoystickButton(driveJS, 7);
        extendSkyPoleBtn.whenPressed(new ClimberExtend());
        trigger = new JoystickButton(driveJS, 1);
        trigger.whenPressed(new AutoShoot());
        visionAlign = new JoystickButton(driveJS, 3);
        visionAlign.whenPressed(new AlignToVision());
        shifter = new JoystickButton(driveJS, 2);
        shifter.whenPressed(new Shift());


        // SmartDashboard Buttons
        SmartDashboard.putData("Rotate: Rotate45", new Rotate(45));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveJS() {
        return driveJS;
    }

    public Joystick getUtilJS() {
        return utilJS;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

