/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ChaseObject extends Command {
  double m_basePower = 0.15;
  private double turnGain = 0.05;
  private double distToObject = 0;
  private double xToObject = 0;
  private long deltaTime = 0;
	private long startTime = 0;
	private long currentTime = 0;
  public ChaseObject(double basePower) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_basePower = basePower;
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startTime = System.currentTimeMillis();
		currentTime = startTime;
    distToObject = Robot.distToObject;
    xToObject = Robot.xToObject;
    System.out.println("Initializing ChaseObject Command.");
    System.out.println("Speed: " + m_basePower);
    System.out.println("Initial distance to object: " + distToObject);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentTime = System.currentTimeMillis();
		deltaTime = currentTime - startTime;
    distToObject = Robot.distToObject;
    xToObject = Robot.xToObject;
    Robot.drivetrain.setLeftPower(m_basePower + turnGain*xToObject);
    Robot.drivetrain.setRightPower(m_basePower + turnGain*xToObject);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(distToObject < 10) return true;
    else if (deltaTime >= 10000) {
			System.out.println("ChaseObject command cancelled due to timeout (10 seconds).");
			return true;
		}
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.setLeftPower(0);
    Robot.drivetrain.setRightPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
