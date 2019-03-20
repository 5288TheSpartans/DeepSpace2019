/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class SpeedModifierPresetCommand extends Command {

double deltaTime = 0;
double startingTime = 0;
double lastTime = 0;
double currentTime = 0;
double finalTime = 0;
double speedMultiplierValue = 0;

  public SpeedModifierPresetCommand(double speedMultiplier) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    speedMultiplierValue = speedMultiplier;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Set Speed Preset Activated!");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentTime = System.currentTimeMillis();
    deltaTime = currentTime - startingTime;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(deltaTime > 3) {
      RobotMap.driveSpeedMultiplier = speedMultiplierValue;
      System.out.println("Speed preset set!");
      return true;
    }
    else
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
