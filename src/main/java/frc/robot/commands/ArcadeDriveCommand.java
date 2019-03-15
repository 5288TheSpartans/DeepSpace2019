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
import frc.robot.accessories.SpartanPID;

public class ArcadeDriveCommand extends Command {

  double leftJoyY = 0, rightJoyX = 0, error = 0, gain = 0;
  SpartanPID straightPID;

  public ArcadeDriveCommand() {
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing ArcadeDriveCommand.");
    straightPID = new SpartanPID(RobotMap.DriveStraightP, RobotMap.DriveStraightI, RobotMap.DriveStraightD,
        RobotMap.DriveStraightFF);
    Robot.drivetrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // Calculate how much one side is spinning more than the other
    error = Robot.drivetrain.getLeftDistanceInches() - Robot.drivetrain.getRightDistanceInches();

    leftJoyY = Robot.m_oi.getPrimaryControllerLeftStickY();
    rightJoyX = Robot.m_oi.getPrimaryControllerRightStickX();

    // If it IS within the deadzone
    if (leftJoyY > -RobotMap.joystickDeadzone && leftJoyY < RobotMap.joystickDeadzone) {
      leftJoyY = 0;
    }
    if (rightJoyX > -RobotMap.joystickDeadzone && rightJoyX < RobotMap.joystickDeadzone) {
      rightJoyX = 0;
    }
    // If the right joystick (i.e: the turning axis) is not 0, then use Arcade drive
    // normally.
    // Otherwise, the robot should be going straight. Therefore, use
    // DriveStraightPID to go straight.
    // But if both joystick values are within the deadzones, they will both be 0.
    // Apply no power in that case.
    if (rightJoyX != 0) {
      Robot.drivetrain.setLeftPower(leftJoyY - rightJoyX);
      Robot.drivetrain.setRightPower(leftJoyY + rightJoyX);
    }
    // right joystick is in the deadzone, left joystick is not; apply PID to keep
    // the robot going straight
    else if (rightJoyX == 0 && leftJoyY != 0) {
      straightPID.update(error);
      gain = straightPID.getOutput();
      // REMOVE ONCE PID IS TUNED
      gain = 0;
      Robot.drivetrain.setLeftPower(leftJoyY - gain);
      Robot.drivetrain.setRightPower(leftJoyY + gain);
    }
    // both joystick values are within the deadzone
    else {
      Robot.drivetrain.setLeftPower(0.0);
      Robot.drivetrain.setRightPower(0.0);
    }
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
