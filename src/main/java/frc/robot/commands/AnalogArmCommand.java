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
import java.lang.Math;

public class AnalogArmCommand extends Command {
  double leftTrigger = 0, rightTrigger = 0, error = 0, lastArmAngle = 0;
  SpartanPID armGravityPID;

  public AnalogArmCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing AnalogArmCommand.");
    armGravityPID = new SpartanPID(RobotMap.ArmGravityP, RobotMap.ArmGravityI, RobotMap.ArmGravityD,
        RobotMap.ArmGravityFF);

    armGravityPID.setTarget(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //armGravityPID = new SpartanPID(SmartDashboard.getNumber("Arm Gravity P", RobotMap.ArmGravityP), SmartDashboard.getNumber("Arm Gravity I", RobotMap.ArmGravityI), SmartDashboard.getNumber("Arm Gravity D", RobotMap.ArmGravityD), RobotMap.ArmGravityFF);
    // FOR LOGITECHCONTROLLER/XBOXCONTROLLER2 CLASS
    // Robot.arm.setArmPower(-Robot.m_oi.secondaryController.getLeftAnalogTrigger()
    // + Robot.m_oi.secondaryController.getRightAnalogTrigger());
  
    leftTrigger = Robot.m_oi.getSecondaryControllerLeftTrigger();
    rightTrigger = Robot.m_oi.getSecondaryControllerRightTrigger();

    error = Robot.arm.getRotationAngle() - lastArmAngle;
    lastArmAngle = Robot.arm.getRotationAngle();
    //System.out.println("Error: " + error );
    //System.out.println(armGravityPID.getPIDConstants());
   // System.out.println("Left trigger: " + leftTrigger);
   // System.out.println("Right trigger: " + rightTrigger);

    if (leftTrigger < RobotMap.triggerDeadzone)
     leftTrigger = 0;
    if (rightTrigger < RobotMap.triggerDeadzone)
     rightTrigger = 0;

    if (leftTrigger < RobotMap.triggerDeadzone && rightTrigger < RobotMap.triggerDeadzone) {

      Robot.arm.setArmPower(Robot.arm.getGravityFightingValue());

    } else {
      // Left for lowering, right for raising
      Robot.arm.setArmPower(leftTrigger - rightTrigger);

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
    System.out.println("AnalogArmCommand interrupted.");
    Robot.arm.setArmPower(0.0);
  }
}
