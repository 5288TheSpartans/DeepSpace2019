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

public class AnalogArmCommand extends Command {
  double leftTrigger = 0, rightTrigger = 0;
  public AnalogArmCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing AnalogArmCommand.");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // FOR LOGITECHCONTROLLER/XBOXCONTROLLER2 CLASS
    // Robot.arm.setArmPower(-Robot.m_oi.secondaryController.getLeftAnalogTrigger() + Robot.m_oi.secondaryController.getRightAnalogTrigger());
    
    leftTrigger = Robot.m_oi.getSecondaryControllerLeftTrigger();
    rightTrigger = Robot.m_oi.getSecondaryControllerRightTrigger();
    if(leftTrigger < RobotMap.triggerDeadzone) leftTrigger = 0;
    if(rightTrigger < RobotMap.triggerDeadzone) rightTrigger = 0;

    // Left for lowering, right for raising
    Robot.arm.setArmPower(leftTrigger -rightTrigger);

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
    System.out.println("AnalogArmCommand interrupted.");
    Robot.arm.setArmPower(0.0);
  }
}
