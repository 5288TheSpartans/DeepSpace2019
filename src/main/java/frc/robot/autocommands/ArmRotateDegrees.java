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
  final double armPowerMinimum = 0;
  final double armPowerLimit = 0.6;
  SpartanPID armPID;
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
    Robot.isArmPresetRunning = true;

    startingAngle = Robot.arm.getRotationAngle();
    currentAngle = startingAngle;
    if(startingAngle < angleToTurnTo) {
    armPID = new SpartanPID(SmartDashboard.getNumber("Arm Raise P", RobotMap.ArmRaiseP),
        SmartDashboard.getNumber("Arm Raise I", RobotMap.ArmRaiseI),
        SmartDashboard.getNumber("Arm Raise D", RobotMap.ArmRaiseD), RobotMap.ArmRaiseFF);
    } else {
      armPID = new SpartanPID(SmartDashboard.getNumber("Arm Lower P", RobotMap.ArmLowerP),
      SmartDashboard.getNumber("Arm Lower I", RobotMap.ArmLowerI),
      SmartDashboard.getNumber("Arm Lower D", RobotMap.ArmLowerD), RobotMap.ArmLowerFF);


    }


    armPID.setTarget(angleToTurnTo);
    // armLowerPID.setTarget(angleToTurnTo);
    // System.out.println(m_basePower);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentAngle = Robot.arm.getRotationAngle();

    // update armRotatePID with the current angle of the arm
    armPID.update(currentAngle);

    armOutput = armPID.getOutput();



    if (Math.abs(armOutput) > armPowerLimit && armOutput > 0) {
      System.out.println("Arm set to limit.");
      armOutput = armPowerLimit;
    } else if (Math.abs(armOutput) > armPowerLimit && armOutput < 0) {
      System.out.println("Arm set limit.");
      armOutput = -armPowerLimit;
    }

    if(angleToTurnTo < currentAngle && angleToTurnTo < 140) {
      armOutput = armOutput*0.6; // 13 sixteenths of 0.6
    }
    

/*    if (Math.abs(armOutput) < armPowerMinimum && armOutput > 0) {
      System.out.println("Arm set to minimum.");
      armOutput = armPowerMinimum;
    } else if (Math.abs(armOutput) < armPowerMinimum && armOutput < 0) {
      System.out.println("Arm set to minimum.");
      armOutput = -armPowerMinimum;
    }
    */
    if ((angleToTurnTo - 3) <= Robot.arm.getRotationAngle() && Robot.arm.getRotationAngle() <= (angleToTurnTo + 3)) {
      Robot.isArmPresetRunning = false;
      armOutput = Robot.arm.getGravityFightingValue();
    }

    Robot.arm.setArmPower(-armOutput);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return true if we're at (or within range) of the desired angle
    if ((angleToTurnTo - 3) <= Robot.arm.getRotationAngle() && Robot.arm.getRotationAngle() <= (angleToTurnTo + 3)) {
      Robot.isArmPresetRunning = false;
      System.out.println("ArmRotateDegrees finished.");
      return true;
    }
    // return true if triggers receive input
    else if (Robot.m_oi.getSecondaryControllerLeftTrigger() > RobotMap.triggerDeadzone
        || Robot.m_oi.getSecondaryControllerRightTrigger() > RobotMap.triggerDeadzone) {
        Robot.isArmPresetRunning = false;
      System.out.println("ArmRotateDegrees finished.");
      return true;
    }
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.isArmPresetRunning = false;
    Robot.arm.setArmPower(Robot.arm.getGravityFightingValue());
    System.out.println("ArmRotateDegrees finished. at " + currentAngle);  

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.isArmPresetRunning = false;
    Robot.arm.setArmPower(Robot.arm.getGravityFightingValue());
  }
}
