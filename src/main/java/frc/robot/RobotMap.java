/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // PWM
  // DRIVETRAIN
  // PWM - Pulse Width Modulation Ports
  public static final int leftDriveMotor1 = 9;
  public static final int leftDriveMotor2 = 8;
  public static final int leftDriveMotor3 = 0;

  public static final int rightDriveMotor1 = 7;
  public static final int rightDriveMotor2 = 6;
  public static final int rightDriveMotor3 = 1;

  // IMPORTANT!! CHANGE THE Port.
  // also wrist motor
  public static final int wristMotor = 0;
  // intake motor 
  // IMPORTANT, CHANGE THE PORT
  public static final int intakeLeft = 1;
  public static final int intakeRight = 0;

  // D I/O - Digital Input/Output Ports
  public static final int leftDriveEncoder1 = 0;
  public static final int leftDriveEncoder2 = 1;
  public static final int rightDriveEncoder1 = 2;
  public static final int rightDriveEncoder2 = 3;

  // DEADZONES
  public static final double joystickDeadzone = 0.1;

  // CAN
  // ARM
  public static final int armBaseMotor1 = 2;
  public static final int armBaseMotor2 = 3;
  public static final int armBaseMotor3 = 4;

  public static final int armEncoder1 = 0;
  public static final int armEncoder2 = 0;
  public static final int armEncoder3 = 0;

  // PID CONSTANTS

  // DRIVE STRAIGHT
  public static final double DriveStraightP = 0.002;
  public static final double DriveStraightI = 0;
  public static final double DriveStraightD = 0.002;
  public static final double DriveStraightFF = 0;

  // DISTANCE

  public static final double DistanceP = 0;
  public static final double DistanceI = 0;
  public static final double DistanceD = 0;
  public static final double DistanceFF = 0;

  //ARM ROTATE
  
  public static final double ArmRotateP = 0;
  public static final double ArmRotateI = 0;
  public static final double ArmRotateD = 0;
  public static final double ArmRotateFF = 0;

  // TURN
  public static final double TurnP = 0.5;
  public static final double TurnI = 0.2;
  public static final double TurnD = 0.03;
  public static final double TurnFF = 0.2;

  
  

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

}
