/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.accessories.LogitechController;
//import edu.wpi.first.wpilibj.Joystick;
import frc.robot.accessories.XboxController2;
import frc.robot.autoCommandGroups.Modifier1ButtonCombination;
import frc.robot.autoCommandGroups.Modifier2ButtonCombination;
import frc.robot.commands.PneumaticsActivate;

import frc.robot.commands.IntakeBallCommand;
import frc.robot.commands.ShootBallCommand;

import frc.robot.commands.LowerArmCommand;
import frc.robot.commands.RaiseArmCommand;


import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.TankDriveCommand;


import frc.robot.commands.RaiseWristCommand;
import frc.robot.commands.LowerWristCommand;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  //public XboxController2 primaryController = new XboxController2(0);
  //public XboxController2 secondaryController = new XboxController2(1);
    public LogitechController primaryController = new LogitechController(0);
    public XboxController2 secondaryController = new XboxController2(1);
  public OI() {
    
    // setting up the raise arm and lowering arm(change value if needed)
    //primaryController.rightTriggerButton.whileHeld(new RaiseArmCommand(-0.1));
    //primaryController.leftTriggerButton.whileHeld(new LowerArmCommand(0.1));

    // getting ball
    //primaryController.rightBumper.whileHeld(new IntakeBallCommand(0.8));

    // getting or placing hatch
    //primaryController.leftBumper.whenPressed(new PneumaticsActivate());

    // raising and lowering wrist
    //primaryController.

    // modifier button combinations

    // setting up the intake
    //primaryController.xButton.toggleWhenPressed(new IntakeBallCommand(0.3));

    // setting up shooting the ball
    //primaryController.bButton.toggleWhenPressed(new ShootBallCommand(-0.5));

    // modifier 1 (ball)
    //primaryController.rightBumper.whileHeld(new Modifier1ButtonCombination());

    // modifier 2 (hatch)
    //primaryController.leftBumper.whileHeld(new Modifier2ButtonCombination());

    
    secondaryController.yButton.toggleWhenPressed(new IntakeBallCommand(0.3));
    secondaryController.aButton.toggleWhenPressed(new ShootBallCommand(-0.5));
    secondaryController.xButton.whileHeld(new RaiseWristCommand(1.00));
    secondaryController.bButton.whileHeld(new LowerWristCommand(-1.00));

  }

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

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public double getXboxLeftStickY() {
    // return xbox.getY(Hand.kLeft);
    // return primaryController.getRawAxis(1);
    System.out.println("XBOX LEFT-Y: "+  primaryController.getLeftStickY());
    return primaryController.getLeftStickY();
  }

  public double getXboxRightStickX() {
    // return xbox.getX(Hand.kRight);
    // return primaryController.getRawAxis(4);
    System.out.println("XBOX RIGHT-X: " + primaryController.getRightStickX());
    return primaryController.getRightStickX();
  }
  public double getSecondaryControllerLeftStickY() {
    System.out.println("SECONDARY LEFT-Y: "+ secondaryController.getLeftStickY());
    return secondaryController.getLeftStickY();
  }
  public double getSecondaryControllerRightStickX() {
    System.out.println("SECONDARY RIGHT-X: " +secondaryController.getRightStickX());
    return secondaryController.getRightStickX();
  }
  // For "Modifiers", try adding "triggers" to your OI, instead of buttons.
  // Check here:  
 /* public boolean getRightModifierStatus() {
    return primaryController.getRightBumperStatus();
  }
  
	public boolean getLeftModifierStatus() {
    return primaryController.getLeftBumperStatus();
  }
*/
}

