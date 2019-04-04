/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.AnalogWristCommand;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private double encoderUnit = 4096;
  private double gearRatio = 183.33333333;
  private double angleToTickRatio = -21.2;
  private double resetValue = 73;
  private double lowerAngleLimit = 180;
  private double topAngleLimit = 170;
  private double wristPower = 0;
  private TalonSRX wristMotor;
  private DigitalInput bottomLimitSwitch;
  public Encoder wristEncoder;
  private double angleTickScaleFactor = 0.00013453518;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new AnalogWristCommand());
  }

  // connecting the Wristmotor to a wristmoror subsystem.

  public WristSubsystem() {
    
  wristEncoder = new Encoder(RobotMap.wristEncoder1, RobotMap.wristEncoder2, false,
  EncodingType.k4X);
    wristMotor = new TalonSRX(RobotMap.wristMotor);
    bottomLimitSwitch = new DigitalInput(RobotMap.bottomWristLimitSwitch);
    wristMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0,0);
    wristMotor.setSensorPhase(false);

  }

  public double getWristDistanceTicks() {
    return wristEncoder.getRaw();

  }

   // get the current angle of the wrist
  public double getRotationAngle() {
    updateLimitSwitch();
    return (wristEncoder.getRaw())*angleTickScaleFactor + RobotMap.wristResetAngle;
  }

  public boolean isWristAtTop() {
    // if (getRotationAngle() >= topAngleLimit)
    // return true;
    return getLimitSwitch();
  }
  public void updateLimitSwitch() {
    if(getLimitSwitch()) {
      resetEncoders();
    }
    
  }
  public boolean getLimitSwitch() {
    return bottomLimitSwitch.get();
  }
  public boolean isWristAtBottom() {
    if (getRotationAngle() >= lowerAngleLimit)
    return true;
    else return false;
    
  }

  public void setWristPower(double power) {
    // if (getRotationAngle() >= topAngleLimit || getRotationAngle() <=
    // lowerAngleLimit)
    // wristPower = 0;
    // else
    if(getLimitSwitch() && power < 0) wristPower = 0;
    else wristPower = power;
  }

  public void updateOutput() {
    if(getLimitSwitch() & wristPower < 0 || isWristAtBottom() & wristPower > 0) wristMotor.set(ControlMode.PercentOutput, 0);
    else wristMotor.set(ControlMode.PercentOutput, wristPower*RobotMap.wristSpeedMultiplier);
    
  }

  public void resetEncoders() {
   wristEncoder.reset();
  }
}
