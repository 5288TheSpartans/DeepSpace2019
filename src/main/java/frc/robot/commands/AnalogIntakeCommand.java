/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AnalogIntakeCommand extends Command {
  double rightJoyY = 0.0;
  public AnalogIntakeCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initialzing AnalogIntakeCommand.");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // For LogitechController class use Robot.m_oi.secondaryController.getRightStickY()
    // get the left joystick Y value
    rightJoyY = Robot.m_oi.getSecondaryControllerRightStickY();
    if (rightJoyY > -RobotMap.joystickDeadzone && rightJoyY < RobotMap.joystickDeadzone) {
      rightJoyY = 0;
    }

    Robot.intake.setIntakePower(rightJoyY);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("AnalogIntakeCommand interrupted.");
    Robot.intake.setIntakePower(0.0);
  }
}