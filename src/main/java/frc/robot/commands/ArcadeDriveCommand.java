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
  public ArcadeDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("INITIALIZING ARCADE DRIVE COMMAND");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if((Robot.m_oi.getXboxLeftTriggerY() + Robot.m_oi.getXboxRightTriggerX()) < RobotMap.joystickDeadzone ) {
      

    }

    else{
      Robot.drivetrain.setLeftPower(Robot.m_oi.getXboxLeftTriggerY() + Robot.m_oi.getXboxRightTriggerX());
      Robot.drivetrain.setRightPower(Robot.m_oi.getXboxLeftTriggerY() + Robot.m_oi.getXboxRightTriggerX());
      Robot.drivetrain.updateOutputs();
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
