/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RaiseArmCommand extends Command {
  public double armPower = 0.0;

  public RaiseArmCommand(double power) {
    requires(Robot.arm);
    if((Robot.m_oi.primaryController.getRightAnalogTrigger()) != 0)
      armPower = Robot.m_oi.primaryController.getRightAnalogTrigger();
    else
      armPower = power;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing RaiseArmCommandLow");
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
