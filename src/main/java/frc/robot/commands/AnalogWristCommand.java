/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.accessories.SpartanPID;

public class AnalogWristCommand extends Command {
  double leftJoyY = 0, error = 0, lastWristAngle = 0;
  SpartanPID wristGravityPID;

  public AnalogWristCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing AnalogWristCommand.");
    wristGravityPID = new SpartanPID(RobotMap.WristGravityP, RobotMap.WristGravityI, RobotMap.WristGravityD,
        RobotMap.WristGravityFF);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // For LogitechController class use
    // Robot.m_oi.secondaryController.getLeftStickY()
    // get the left joystick Y value
    wristGravityPID = new SpartanPID(SmartDashboard.getNumber("Wrist Gravity P", RobotMap.WristGravityP),
        SmartDashboard.getNumber("Wrist Gravity I", RobotMap.WristGravityI),
        SmartDashboard.getNumber("Wrist Gravity D", RobotMap.WristGravityD), RobotMap.WristGravityFF);
    error = Robot.wrist.getRotationAngle() - lastWristAngle;
    lastWristAngle = Robot.wrist.getRotationAngle();
    leftJoyY = Robot.m_oi.getSecondaryControllerLeftStickY();

    // When in joystick deadzone or no input
    if (leftJoyY > -RobotMap.joystickDeadzone && leftJoyY < RobotMap.joystickDeadzone) {

      // Sets the WristPower accordingly depending on how far the current error is to
      // 0.
      wristGravityPID.update(error);
      Robot.wrist.setWristPower(wristGravityPID.getOutput());

      // If not in joystick deadzone, use Joystick values
    } else {
      Robot.wrist.setWristPower(-leftJoyY);
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
    System.out.println("AnalogWristCommand interrupted.");
    Robot.wrist.setWristPower(0.0);

  }
}
