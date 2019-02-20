/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
import frc.robot.accessories.XboxController;
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
  private XboxController primaryController = new XboxController(0);

  // private Joystick secondaryController = new Joystick(1);
  public OI() {

    // setting up the raise arm and lowering arm(change value if needed)
    primaryController.rightTriggerButton.whileHeld(new RaiseArmCommand(-0.1));
    primaryController.leftTriggerButton.whileHeld(new LowerArmCommand(0.1));

    // using the newmatics for pushing.
    primaryController.selectButton.whenPressed(new PneumaticsActivate());

    // setting up the wrist
    primaryController.leftBumper.whileHeld(new RaiseWristCommand(0.2));
    primaryController.rightBumper.whileHeld(new LowerWristCommand(-0.2));

    // setting up the intake
    primaryController.xButton.toggleWhenPressed(new IntakeBallCommand(0.3));

    // setting up the shooting the ball
    primaryController.bButton.toggleWhenPressed(new ShootBallCommand(-0.5));


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

  public double getXboxLeftTriggerY() {
    // return xbox.getY(Hand.kLeft);
    // return primaryController.getRawAxis(1);
    return primaryController.getLeftStickY();

  }

  public double getXboxRightTriggerX() {
    // return xbox.getX(Hand.kRight);
    // return primaryController.getRawAxis(4);
    return primaryController.getRightStickX();
  }
}
