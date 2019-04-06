/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import frc.robot.RobotMap;
import frc.robot.commands.AnalogArmCommand;

/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private double encoderUnit = 4096;
  private double gearRatio = 93.33;
  public double armSpeedMultiplier = 1.0;
  private double tickToAngleRatio = 3.323102083756007;
  // The arm's limit angles (in degrees)
  private double armBottomLimit = 30;
  private double armTopLimit = 210;

  private double limitResetDegrees = 0;

  private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.bottomArmLimitSwitch);

  private CANSparkMax armMotor1 = new CANSparkMax(RobotMap.armBaseMotor1, MotorType.kBrushless);
  private CANSparkMax armMotor2 = new CANSparkMax(RobotMap.armBaseMotor2, MotorType.kBrushless);
  // private CANSparkMax armMotor3 = new CANSparkMax(RobotMap.armBaseMotor3,
  // MotorType.kBrushless);

  private CANEncoder armEncoder1 = armMotor1.getEncoder();
  private CANEncoder armEncoder2 = armMotor2.getEncoder();
  // private CANEncoder armEncoder3 = armMotor3.getEncoder();

  private double currentArmPower = 0;

  private boolean isOverride = false;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new AnalogArmCommand());
  }

  public void setResetValue() {
    limitResetDegrees = -1*((getDistanceTicks()/108)*-360)  + RobotMap.armResetAngle; 
  }

  public double getDistanceTicks() {
    return ((armEncoder1.getPosition() + armEncoder2.getPosition()) / 2);
  }

  // get the current angle of the arm.
  public double getRotationAngle() {
    return getDistanceTicks()*tickToAngleRatio;
    //return (getDistanceTicks()/108)*-360 + limitResetDegrees;
    //return ((getDistanceTicks() / encoderUnit)* (1/gearRatio)) * 360;
  }
  public void updateBottomLimit() {
    if(getLimitSwitch()) setResetValue();
  }
  public boolean isArmAtBottom() {
     updateBottomLimit(); 
     if ((getRotationAngle() >= armBottomLimit -3 && getRotationAngle() <= armBottomLimit + 3) || getLimitSwitch()) return true; else return false;
  }

  public boolean isArmAtTop() {
     /*
     * if (getRotationAngle() >= armTopLimit) return true; else return false;
     */ return false;
  }

  public boolean getLimitSwitch() {
    return bottomLimitSwitch.get();
  }

  public boolean canMove() {
    if(Robot.wrist.getRotationAngle() <= RobotMap.wristResetAngle) return false;
    else return false;
  }

  public void setArmPower(double power) {
    // if the arm is at the top and you're trying to push it further, do nothing
    // if the arm is at the bottom and you're trying to push it further, do nothing
    
     if (isArmAtBottom() & power > 0){
     currentArmPower = 0;
     }
    else { 
       currentArmPower = power;
     }
    // System.out.println("isArmAtBottom: " + isArmAtBottom());
    // System.out.println("IsArmAtTop: " + isArmAtTop());
    // System.out.println("Power to set: " + power);
    // System.out.println("Arm angle: " + getRotationAngle());
  }

  public void setArmPowerOverride(double power) {
    if (isOverride)
      currentArmPower = power;
  }
  public boolean canArmMoveToOrigin() {
    return false;
  }

  public void updateOutputs() {
    updateBottomLimit();
    if(!Robot.isArmPresetRunning & (Robot.m_oi.getSecondaryControllerRightTrigger() < RobotMap.triggerDeadzone)& (Robot.m_oi.getSecondaryControllerLeftTrigger() < RobotMap.triggerDeadzone)) {
      currentArmPower = getGravityFightingValue();
    }
    if((getLimitSwitch() & currentArmPower > 0) || (isArmAtTop() & currentArmPower < 0))  {
      currentArmPower = 0;
    }
    SmartDashboard.putNumber("Arm Output",currentArmPower*RobotMap.armSpeedMultiplier);
    SmartDashboard.putNumber("Gravity fighting",!Robot.isArmPresetRunning & (Robot.m_oi.getSecondaryControllerRightTrigger() < RobotMap.triggerDeadzone)& (Robot.m_oi.getSecondaryControllerLeftTrigger() < RobotMap.triggerDeadzone));
    armMotor1.set(currentArmPower*RobotMap.armSpeedMultiplier);
    armMotor2.set(currentArmPower*RobotMap.armSpeedMultiplier);
    // armMotor3.set(currentArmPower*RobotMap.armSpeedMultiplier);
  }
  public double getGravityFightingValue() {
    if (getRotationAngle() <= RobotMap.armResetAngle +1) return 0;
    else return -0.1*Math.sin(getRotationAngle());
  }
  
}
