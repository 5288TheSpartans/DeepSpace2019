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

public class WristRotateDegrees extends Command {
  double m_basePower = 0.0;
  double error = 0;
  double wristOutput = 0.0;
  double angleToTurnTo;
  SpartanPID wristRotatePID;

  public WristRotateDegrees(double angle) {

    requires(Robot.wrist);
    angleToTurnTo = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    wristRotatePID = new SpartanPID(RobotMap.WristRotateP,RobotMap.WristRotateI,RobotMap.WristRotateD,
    RobotMap.WristRotateFF);
    wristRotatePID.setTarget(angleToTurnTo);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    wristRotatePID = new SpartanPID(SmartDashboard.getNumber("Wrist P", RobotMap.WristRotateP),
    SmartDashboard.getNumber("Wrist I", RobotMap.WristRotateI),
    SmartDashboard.getNumber("Wrist D", RobotMap.WristRotateD), RobotMap.WristRotateFF);

    wristRotatePID.update(Robot.wrist.getRotationAngle() - Robot.wrist.getRotationAngle());

    wristOutput = wristRotatePID.getOutput();

    Robot.wrist.setWristPower(wristOutput);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
   /* // return true when at (or in range of) the specified angle
    if ((angleToTurnTo - 2) <= Robot.wrist.getRotationAngle() && Robot.wrist.getRotationAngle() <= (angleToTurnTo + 2))
      return true;
    // return true when the wrist controller joystick receives input outside of its deadzones
    else if(!(Robot.m_oi.getSecondaryControllerLeftStickY() >= -RobotMap.joystickDeadzone && Robot.m_oi.getPrimaryControllerLeftStickY() <= RobotMap.joystickDeadzone) )
      return true;
    else
      return false;
    */
    }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.wrist.setWristPower(0.0);
  }


  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
