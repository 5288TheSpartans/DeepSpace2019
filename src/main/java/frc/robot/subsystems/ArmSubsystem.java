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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax armMotor1 = new CANSparkMax(RobotMap.armBaseMotor1,MotorType.kBrushless);
  private CANSparkMax armMotor2 = new CANSparkMax(RobotMap.armBaseMotor2,MotorType.kBrushless);
  private CANSparkMax armMotor3 = new CANSparkMax(RobotMap.armBaseMotor3,MotorType.kBrushless);

  private CANEncoder armEncoder1 = armMotor1.getEncoder();
  private CANEncoder armEncoder2 = armMotor2.getEncoder();
  private CANEncoder armEncoder3 = armMotor3.getEncoder();

  // The arm's limit angles (in degrees)
  private final double armBottomLimit = -5;
  private final double armTopLimit = 110;

  private double currentArmPower = 0;

  private boolean isOverride = false;




  @Override
  public void initDefaultCommand() {   
    // Set the default command for a subsystem here.
    
  }
  public double getDistanceTicks() {
    
    return (armEncoder1.getPosition() + armEncoder2.getPosition() + armEncoder3.getPosition())/3;
  }
  // get the current angle of the arm.
  public double getRotationAngle() {
    return (getDistanceTicks()/360)%360;
  }
  
  
  public boolean isArmAtBottom() {
    if(getRotationAngle() <= armBottomLimit) return true;
    else return false;

  }

  public boolean isArmAtTop() {
    if(getRotationAngle() >= armTopLimit) return true;
    else return false;
  }

  public void setArmPower(double power) {
    // if the arm is at the top and you're trying to push it further, do nothing
    // if the arm is at the bottom and you're trying to push it further, do nothing
    if((isArmAtTop() & power > 0) || (isArmAtBottom() & power < 0))
       System.out.println("At limits. Do nothing.");
    else {
      currentArmPower = power;
    }

    System.out.println("isArmAtBottom: " + isArmAtBottom());
    System.out.println("IsArmAtTop: " + isArmAtTop());
    System.out.println("Power to set: " + power);
  }

  public void setArmPowerOverride( double power) {
    if(isOverride) currentArmPower = power;
  }

  public void updateOutputs() {
    armMotor1.set(currentArmPower);
    armMotor2.set(currentArmPower);
    armMotor3.set(currentArmPower);

  }

  



}

  