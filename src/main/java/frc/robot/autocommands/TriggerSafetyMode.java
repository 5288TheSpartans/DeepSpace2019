/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TriggerSafetyMode extends Command {
  public TriggerSafetyMode() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.safetyMode = !Robot.safetyMode;
    if(Robot.safetyMode) {
      System.out.println("Safety mode initiated.");
      RobotMap.driveSpeedMultiplier = 0.25;
      RobotMap.armSpeedMultiplier = 0.4;
      RobotMap.wristSpeedMultiplier = 0.5;
    }
    else {
      System.out.println("Safety mode disabled.");
      RobotMap.driveSpeedMultiplier = 1.0;
      RobotMap.armSpeedMultiplier = 1.0;
      RobotMap.wristSpeedMultiplier = 1.0;

    }

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
