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


}

  