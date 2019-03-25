/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autocommands.DoNothingTime;
import frc.robot.autocommands.DriveStraight;
import frc.robot.autocommands.SpotTurnDegrees;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.WristSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DrivetrainSubsystem drivetrain;
  public static OI m_oi;
  // public static CompressorSubsystem compressor;
  public static ArmSubsystem arm;
  public static WristSubsystem wrist;
  public static NetworkTableInstance inst;
  public static NetworkTable table;
  public static IntakeSubsystem intake;
  public static PneumaticsSubsystem pneumatics;
  public static NetworkTableEntry angleToTurn, distEntry;
  double distance;
  public static Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    // Instantiate subsystems
    drivetrain = new DrivetrainSubsystem();
    pneumatics = new PneumaticsSubsystem();
    arm = new ArmSubsystem();
    wrist = new WristSubsystem();
    intake = new IntakeSubsystem();
    m_oi = new OI();

    arm.setResetValue();
    wrist.resetEncoders();
    pneumatics.mainCompressor.start();

    // Choose auto
    m_chooser.addOption("Default Nothing", new DoNothingTime(1000));
    SmartDashboard.putData("Auto mode", m_chooser);

    // Put constants on SmartDashboard for PID tuning
    SmartDashboard.putNumber("Arm Raise P", RobotMap.ArmRaiseP);
    SmartDashboard.putNumber("Arm Raise I", RobotMap.ArmRaiseI);
    SmartDashboard.putNumber("Arm Raise D", RobotMap.ArmRaiseD);

//    SmartDashboard.putNumber("Arm Lower P", RobotMap.ArmLowerP);
//    SmartDashboard.putNumber("Arm Lower I", RobotMap.ArmLowerI);
//    SmartDashboard.putNumber("Arm Lower D", RobotMap.ArmLowerD);

    SmartDashboard.putNumber("Wrist P", RobotMap.WristRotateP);
    SmartDashboard.putNumber("Wrist I", RobotMap.WristRotateI);
    SmartDashboard.putNumber("Wrist D", RobotMap.WristRotateD);

    SmartDashboard.putNumber("Wrist Gravity P", RobotMap.WristGravityP);
    SmartDashboard.putNumber("Wrist Gravity I", RobotMap.WristGravityI);
    SmartDashboard.putNumber("Wrist Gravity D", RobotMap.WristGravityD);

    SmartDashboard.putNumber("Drive Straight P", RobotMap.DriveStraightD);
    SmartDashboard.putNumber("Drive Straight I", RobotMap.DriveStraightI);
    SmartDashboard.putNumber("Drive Straight D", RobotMap.DriveStraightD);

    SmartDashboard.putNumber("Arm Speed Multiplier", RobotMap.armSpeedMultiplier);
    SmartDashboard.putNumber("Wrist Speed Multiplier", RobotMap.wristSpeedMultiplier);
    SmartDashboard.putNumber("Intake Speed Multiplier", RobotMap.intakeSpeedMultiplier);
    SmartDashboard.putNumber("Drive Speed Multiplier", RobotMap.driveSpeedMultiplier);
    
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    distance = SmartDashboard.getNumber("YES!", -1);
    drivetrain.updateOutputs();
    arm.updateOutputs();
    wrist.updateOutput();

    updateSmartDashboard();
    RobotMap.setArmMultiplier(SmartDashboard.getNumber("Arm Speed Multiplier", 1.0));
    RobotMap.setWristMultiplier(SmartDashboard.getNumber("Wrist Speed Multiplier", 1.0));
    RobotMap.setIntakeMultiplier(SmartDashboard.getNumber("Intake Speed Multiplier", 1.0));
    RobotMap.setDriveMultiplier(SmartDashboard.getNumber("Drive Speed Multiplier", 1.0));

    // arm.updateOutputs();
    // System.out.println(inst.isConnected());
    // System.out.println(distEntry.getNumber(-1).doubleValue());

    /*
     * System.out.println("D-Pad Value: " +
     * Robot.m_oi.primaryController.getDPadValue());
     * System.out.println("Arm encoder 1: " + Robot.arm.getArmMotor1Pos());
     * System.out.println("Arm encoder 2: " + Robot.arm.getArmMotor2Pos());
     * System.out.println("Drive encoder 1 (inches): " +
     * drivetrain.getLeftDistanceInches());
     * System.out.println("Drive encoder 2 (inches):" +
     * drivetrain.getRightDistanceInches());
     */

    /*
     * System.out.println("Mod 1 and DPad Down: " + m_oi.mod1AndDPadDown.get());
     * System.out.println("Secondary controller POV: " +
     * m_oi.secondaryController.getPOV());
     */
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    // pneumatics.mainCompressor.start();

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private void updateSmartDashboard() {
    SmartDashboard.putNumber("D-Pad Value: ", Robot.m_oi.primaryController.getPOV());
//    SmartDashboard.putNumber("Jetson Recorded-Distance", distance);
//    SmartDashboard.putNumber("Arm encoder 1: ", Robot.arm.getArmMotor1Pos());
//    SmartDashboard.putNumber("Arm encoder 2: ", Robot.arm.getArmMotor2Pos());
    SmartDashboard.putNumber("Arm angle: ", Robot.arm.getRotationAngle());
    SmartDashboard.putNumber("Wrist angle: ", Robot.wrist.getRotationAngle());
    SmartDashboard.putNumber("Drive encoder 1 (inches): ", drivetrain.getLeftDistanceInches());
    SmartDashboard.putNumber("Drive encoder 2 (inches):", drivetrain.getRightDistanceInches());
    SmartDashboard.putNumber("Wrist Distance", wrist.getWristDistanceTicks());
//    SmartDashboard.putNumber("Arm Distance", arm.getDistanceTicks());
//    SmartDashboard.putNumber("Secondary LeftJoyY", m_oi.getSecondaryControllerLeftStickY());
//    SmartDashboard.putNumber("Secondary RightStickX", m_oi.getSecondaryControllerRightStickX());
    SmartDashboard.putBoolean("Arm Limit Switch", arm.getLimitSwitch());
    SmartDashboard.putBoolean("Wrist Limit Switch", wrist.getLimitSwitch());
    SmartDashboard.putBoolean("Intake Solenoid", pneumatics.getIsExtended());
    drivetrain.putEncoderValues();
  }
  private void updateResets() {
    

  }
}
