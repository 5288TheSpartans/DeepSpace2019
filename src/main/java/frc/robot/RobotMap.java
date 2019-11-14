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

  // PWM
  // DRIVETRAIN
  // PWM - Pulse Width Modulation Ports
  public static final int leftDriveMotor1 = 7;
  public static final int leftDriveMotor2 = 8;

  public static final int rightDriveMotor1 = 0;
  public static final int rightDriveMotor2 = 1;

  // wristMotor - Talon SRX
  public static final int wristMotor = 9;

  // intake motor
  public static final int intakeLeft = 4;
  public static final int intakeRight = 5;

  // D I/O - Digital Input/Output Ports
  public static final int leftDriveEncoder1 = 0; // white - channel A
  public static final int leftDriveEncoder2 = 1; // brown - channel B
  public static final int rightDriveEncoder1 = 8; // white - channel A
  public static final int rightDriveEncoder2 = 9; // brown - channel B


  // DEADZONES
  public static final double joystickDeadzone = 0.05;

  // DRIVE STRAIGHT
  public static final double DriveStraightP = 0.00;
  public static final double DriveStraightI = 0;
  public static final double DriveStraightD = 0.00;
  public static final double DriveStraightFF = 0;

  // TURN
  public static final double TurnP = 0.5;
  public static final double TurnI = 0.2;
  public static final double TurnD = 0.03;
  public static final double TurnFF = 0.2;

  // CAN device IDs
  public static final int PDPID = 1;
  public static final int PCMID = 0;



}
