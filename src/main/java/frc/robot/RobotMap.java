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

  public static double armSpeedMultiplier = 1;
  public static double driveSpeedMultiplier = 1;
  public static double wristSpeedMultiplier = 1;
  public static double intakeSpeedMultiplier = 1;

  public static double defaultDriveSpeedMultiplier = 1;

  // DRIVETRAIN
  // PWM - Pulse Width Modulation Ports
  public static final int leftDriveMotor1 = 0;
  public static final int leftDriveMotor2 = 1;
  public static final int leftDriveMotor3 = 2;

  public static final int rightDriveMotor1 = 7;
  public static final int rightDriveMotor2 = 8;
  public static final int rightDriveMotor3 = 9;

  // wristMotor - Talon SRX
  public static final int wristMotor = 9;

  // intake motor
  public static final int intakeLeft = 4;
  public static final int intakeRight = 5;

  // D I/O - Digital Input/Output Ports
  public static final int leftDriveEncoder1 = 0; // white - channel A
  public static final int leftDriveEncoder2 = 1; // brown - channel B
  public static final int rightDriveEncoder1 = 2; // white - channel A
  public static final int rightDriveEncoder2 = 3; // brown - channel B
  public static final int bottomArmLimitSwitch = 9;
  public static final int bottomWristLimitSwitch = 8;

  // DEADZONES
  public static final double joystickDeadzone = 0.05;
  public static final double triggerDeadzone = 0.05;

  // ARM MOTORS
  public static final int armBaseMotor1 = 3;
  public static final int armBaseMotor2 = 4;
  public static final int armBaseMotor3 = 8;

  // ARM ENCODERS
  public static final int armEncoder1 = 0;
  public static final int armEncoder2 = 0;
  public static final int armEncoder3 = 0;

  // Pneumatics Control Module (PCM) ports
  public static final int leftIntakeSolenoid = 0;
  public static final int rightIntakeSolenoid = 1;

  // INTAKE SPEED CONSTANTS
  public static final double shootBallFast = 0.8;
  public static final double shootBallSlow = -0.5;

  // ARM ANGLE LEVEL CONSTANTS (needs angle values)
  public static final double groundLevelAngle = 0;
  public static final double cargoShipAngle = 0;
  public static final double rocketLevel1Angle = 0;
  public static final double rocketLevel2Angle = 0;
  public static final double rocketLevel3Angle = 0;

  // WRIST ANGLE LEVEL CONSTANTS (needs angle values)
  public static final double raisedWrist = 0;
  public static final double loweredWrist = 0;

  // ARM POWER CONSTANTS
  public static final double armPowerFast = 0.8;
  public static final double armPowerSlow = -0.3;

  // DRIVE STRAIGHT
  public static final double DriveStraightP = 0.00;
  public static final double DriveStraightI = 0;
  public static final double DriveStraightD = 0.00;
  public static final double DriveStraightFF = 0;

  // DISTANCE
  public static final double DistanceP = 0.035;
  public static final double DistanceI = 0;
  public static final double DistanceD = 0;
  public static final double DistanceFF = 0;

  // ARM RAISE
  public static final double ArmRaiseP = 0.001;
  public static final double ArmRaiseI = 0;
  public static final double ArmRaiseD = 0.;
  public static final double ArmRaiseFF = 0;

  // ARM LOWER
  public static final double ArmLowerP = 0;
  public static final double ArmLowerI = 0;
  public static final double ArmLowerD = 0;
  public static final double ArmLowerFF = 0;
  
  // ARM GRAVITY RESIST
  public static final double ArmGravityP = 0;
  public static final double ArmGravityI = 0;
  public static final double ArmGravityD = 0;
  public static final double ArmGravityFF = 0;

  // WRIST GRAVITY RESIST
  public static final double WristGravityP = 0;
  public static final double WristGravityI = 0;
  public static final double WristGravityD = 0;
  public static final double WristGravityFF = 0;

  // WRIST ROTATE
  public static final double WristRotateP = 0;
  public static final double WristRotateI = 0;
  public static final double WristRotateD = 0;
  public static final double WristRotateFF = 0;

  // TURN
  public static final double TurnP = 0.5;
  public static final double TurnI = 0.2;
  public static final double TurnD = 0.03;
  public static final double TurnFF = 0.2;

  // CAN device IDs
  public static final int PDPID = 1;
  public static final int PCMID = 0;

  // PCM device IDs
  public static final int intakeSolenoidChannel = 0;

  // SPEED CONSTANTS
  public static final double intakeSpeed = 0.2;
  public static final double tossBallSpeed = -0.2;
  public static final double blastBallSpeed = -0.7;

  // DRIVETRAIN SPEED PRESETS
  public static final double lowDriveSpeed = 0.8;
  public static final double moderateDriveSpeed = 1.2;
  public static final double highDriveSpeed = 1.4;


  

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static void setArmMultiplier(double multiplier) {
    armSpeedMultiplier = multiplier;
  }

  public static void setWristMultiplier(double multiplier) {
    wristSpeedMultiplier = multiplier;
  }

  public static void setDriveMultiplier(double multiplier) {
    wristSpeedMultiplier = multiplier;
  }

  public static void setIntakeMultiplier(double multiplier) {
    wristSpeedMultiplier = multiplier;
  }

  public static void IncreaseSpeedModifier() {
    driveSpeedMultiplier += 0.1;
}

  public void DecreaseModifierCommand() {
    driveSpeedMultiplier += 0.1;
}

  public void setSpeedMultiplier(double speedMultiplier) {
    driveSpeedMultiplier = speedMultiplier;
  }
}
