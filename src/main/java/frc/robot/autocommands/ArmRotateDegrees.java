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

public class ArmRotateDegrees extends Command {

  double m_basePower = 0.0;
  double error = 0;
  double armOutput = 0.0;
  double angleToTurnTo;
  double startingAngle;
  double currentAngle;
  final double armPowerMinimum = 0.2;
  final double armPowerLimit = 0.7;
  SpartanPID armRaisePID;
  SpartanPID armLowerPID;

  public ArmRotateDegrees(double angle) {

    requires(Robot.arm);
    angleToTurnTo = angle;

    // m_basePower = basePower;
    // armRotatePID = new
    // SpartanPID(RobotMap.ArmRotateP,RobotMap.ArmRotateI,RobotMap.ArmRotateD,RobotMap.ArmRotateFF);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startingAngle = Robot.arm.getRotationAngle();
    currentAngle = Robot.arm.getRotationAngle();
    armRaisePID = new SpartanPID(SmartDashboard.getNumber("Arm Raise P", RobotMap.ArmRaiseP),  SmartDashboard.getNumber("Arm Raise I", RobotMap.ArmRaiseI),  SmartDashboard.getNumber("Arm Raise D", RobotMap.ArmRaiseD), RobotMap.ArmRaiseFF);
   // armLowerPID = new SpartanPID(RobotMap.ArmLowerP, RobotMap.ArmLowerI,RobotMap.ArmLowerD,RobotMap.ArmLowerD);
    
    armRaisePID.setTarget(angleToTurnTo);
   // armLowerPID.setTarget(angleToTurnTo);
    // System.out.println(m_basePower);


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.arm.getRotationAngle();
    
    // update armRotatePID with the current angle of the arm
    armRaisePID.update(currentAngle);

    armOutput = armRaisePID.getOutput();


    if(Math.abs(armOutput) > armPowerLimit && armOutput > 0) {
      System.out.println("Arm set to limit.");
      armOutput = armPowerLimit;
    }
    else if(Math.abs(armOutput) > armPowerLimit && armOutput < 0) {
      System.out.println("Arm set to limit.");
      armOutput = -armPowerLimit;
    }
    
    if(Math.abs(armOutput) < armPowerMinimum && armOutput > 0) {
      System.out.println("Arm set to minimum.");
      armOutput = armPowerMinimum;
    }
    else if(Math.abs(armOutput) < armPowerMinimum && armOutput < 0) {
      System.out.println("Arm set to minimum.");
      armOutput = -armPowerMinimum;
    }
    Robot.arm.setArmPower(-armOutput + Robot.arm.getGravityFightingValue());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return true if we're at (or within range) of the desired angle
    if ((angleToTurnTo - 3) <= Robot.arm.getRotationAngle() && Robot.arm.getRotationAngle() <= (angleToTurnTo + 3))
      return true;
    // return true if triggers receive input
    else if (Robot.m_oi.getSecondaryControllerLeftTrigger() > RobotMap.triggerDeadzone || Robot.m_oi.getSecondaryControllerRightTrigger() > RobotMap.triggerDeadzone)
      return true;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.setArmPower(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {

  }
}
