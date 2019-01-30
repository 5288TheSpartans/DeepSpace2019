/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.OI;

public class ArcadeDriveCommand extends Command {

  double leftJoyY, rightJoyX;
  public ArcadeDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("INITIALIZING ARCADE DRIVE COMMAND");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    leftJoyY = Robot.m_oi.getXboxLeftTriggerY();
    rightJoyX = Robot.m_oi.getXboxRightTriggerX();
    // If it IS within the deadzone
    if(leftJoyY > -RobotMap.joystickDeadzone && leftJoyY < RobotMap.joystickDeadzone ) {
      leftJoyY = 0;
    }
    if(rightJoyX > -RobotMap.joystickDeadzone && rightJoyX < RobotMap.joystickDeadzone) {
      rightJoyX = 0;
    }
    Robot.drivetrain.setLeftPower(leftJoyY - rightJoyX);
    Robot.drivetrain.setRightPower(leftJoyY + rightJoyX);


  

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
  }
}
