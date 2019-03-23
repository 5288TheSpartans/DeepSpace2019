
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
import frc.robot.RobotMap;
import frc.robot.accessories.SpartanPID;

public class DriveStraight extends Command {

  /*
   * Use PID to ensure this equation holds true from encoder values: basePower =
   * command instantiation input L - R = 0 or (L + R)/2 = L = R PID INPUT = (L + R
   * /2) -(L or R, doesnt matter as long as its an absolute value) PID TARGET = 0
   * PID output = Gain Motor output Left = basePower + Gain Motor output Right =
   * basePower - Gain
   */

  double m_basePower = 0.0;
  double error = 0;
  double gain = 0;
  SpartanPID straightPID;

  public DriveStraight(double basePower) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);

    m_basePower = basePower;
    straightPID = new SpartanPID(RobotMap.DriveStraightP, RobotMap.DriveStraightI, RobotMap.DriveStraightD,
        RobotMap.DriveStraightFF); //
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    straightPID = new SpartanPID(RobotMap.DriveStraightP, RobotMap.DriveStraightI, RobotMap.DriveStraightD,
        RobotMap.DriveStraightFF);
    straightPID.setTarget(0);
    System.out.println(m_basePower);
    Robot.drivetrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {

    straightPID = new SpartanPID(SmartDashboard.getNumber("Drive Straight P", RobotMap.DriveStraightP),
    SmartDashboard.getNumber("Drive Straight I", RobotMap.DriveStraightI),
    SmartDashboard.getNumber("Drive Straight D", RobotMap.DriveStraightD), RobotMap.DriveStraightFF);

    // Calculate how much one side is spinning more than the other
    error = Robot.drivetrain.getLeftDistanceInches() - Robot.drivetrain.getRightDistanceInches();
    // Print outs
    System.out.println("Error: " + error);
    System.out.println("Encoder values(Left,Right): " + Robot.drivetrain.getLeftDistanceInches() + ", "
        + Robot.drivetrain.getRightDistanceInches());

    // Update PID with the new error
    straightPID.update(error);

    gain = straightPID.getOutput();
    Robot.drivetrain.setLeftPower(m_basePower - gain);
    Robot.drivetrain.setRightPower(m_basePower + gain);

  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}