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

public class LowerArmCommand extends Command {
  public double armPower = 0;
  // low power = -0.3
  // high power = 0.8
  public LowerArmCommand(double power) {
    /*if((Robot.m_oi.primaryController.getLeftAnalogTrigger()) > RobotMap.triggerDeadzone)
      armPower = Robot.m_oi.primaryController.getLeftAnalogTrigger();
    else
      armPower = power;
*/
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
    armPower = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing LowerArmCommand");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.arm.setArmPower(armPower);
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
    Robot.arm.setArmPower(0.0);
  }
}
