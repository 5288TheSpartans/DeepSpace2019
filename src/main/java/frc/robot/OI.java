/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.WristCommand;

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

  //public LogitechController primaryController = new LogitechController(0);
  //public XboxController2 secondaryController = new XboxController2(1);

  public Joystick primaryController = new Joystick(0);
  public Joystick secondaryController = new Joystick(1);

  private JoystickButton 
  xboxA = new JoystickButton(primaryController,1),//Coloured buttons
  xboxB = new JoystickButton(primaryController,2),//Coloured buttons
  xboxY = new JoystickButton(primaryController,4),//Coloured buttons
  xboxX = new JoystickButton(primaryController,3),//Coloured buttons
  xboxLB = new JoystickButton(primaryController,5),//Left Bumper
  xboxRB = new JoystickButton(primaryController,6),//Right Bumper
  xboxStart = new JoystickButton(primaryController,8),//XboxStart buttons
  xboxBack = new JoystickButton(primaryController,7),//XboxBack buttons
  xboxLStickButton = new JoystickButton(primaryController, 9),
  xboxRStickButton = new JoystickButton(primaryController, 10);

  private JoystickButton 
  logitechA = new JoystickButton(secondaryController,1),//Coloured buttons
  logitechB = new JoystickButton(secondaryController,2),//Coloured buttons
  logitechY = new JoystickButton(secondaryController,4),//Coloured buttons
  logitechX = new JoystickButton(secondaryController,3),//Coloured buttons
  logitechLB = new JoystickButton(secondaryController,5),//Left Bumper
  logitechRB = new JoystickButton(secondaryController,6),//Right Bumper
  logitechStart = new JoystickButton(secondaryController,8),//XboxStart buttons
  logitechBack = new JoystickButton(secondaryController,7),//XboxBack buttons
  logitechLStickButton = new JoystickButton(secondaryController, 9),
  logitechRStickButton = new JoystickButton(secondaryController, 10);

  public OI() {

    // modifier button combinations

    // setting up the intake
    // primaryController.xButton.toggleWhenPressed(new IntakeBallCommand(0.3));

    // setting up shooting the ball
    // primaryController.bButton.toggleWhenPressed(new ShootBallCommand(-0.5));

    // modifier 1 (ball)
    // primaryController.rightBumper.whileHeld(new Modifier1ButtonCombination());

    // modifier 2 (hatch)
    // primaryController.leftBumper.whileHeld(new Modifier2ButtonCombination());

    // IF USING XboxController2 AND LogitechController CLASSES, USE THIS CODE
    /*secondaryController.yButton.toggleWhenPressed(new IntakeCommand(0.3));
    secondaryController.aButton.toggleWhenPressed(new IntakeCommand(-0.5));
    secondaryController.xButton.whileHeld(new WristCommand(1.00));
    secondaryController.bButton.whileHeld(new WristCommand(-1.00));
*/
    logitechY.toggleWhenPressed(new IntakeCommand(0.3));
    logitechA.toggleWhenPressed(new IntakeCommand(-0.5));
    logitechX.whileHeld(new WristCommand(1.00));
    logitechB.whileHeld(new WristCommand(-1.00));

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

  public double getPrimaryControllerLeftStickY() {
    return primaryController.getY(Hand.kLeft);
    //return primaryController.getLeftStickY();
  }

  public double getPrimaryControllerRightStickX() {
    return primaryController.getX(Hand.kRight);
   // return primaryController.getRightStickX();
  }

  public double getSecondaryControllerLeftStickY() {
    return secondaryController.getY(Hand.kLeft);
  //    return secondaryController.getLeftStickY();
  }

  public double getSecondaryControllerRightStickY() {
    return secondaryController.getY(Hand.kRight);
  //    return secondaryController.getLeftStickY();
  }

  public double getSecondaryControllerRightStickX() {
    return secondaryController.getX(Hand.kRight);
    //return secondaryController.getRightStickX();
  }
  public double getSecondaryControllerLeftTrigger() {
    return secondaryController.getRawAxis(5);
  }
  public double getSecondaryControllerRightTrigger() {
    return secondaryController.getRawAxis(6);
  }

  /*
  USED FOR XboxController2 and LogitechController CLASSES
  INSTEAD OF USING THIS, JUST USE Robot.m_oi.secondaryController.getRightBumperStatus() DIRECTLY
  public boolean getSecondaryRightModifierStatus() {
  
    return secondaryController.getRightBumperStatus();
  }

  public boolean getSecondaryLeftModifierStatus() {
    return secondaryController.getLeftBumperStatus();
  }
  */

}
