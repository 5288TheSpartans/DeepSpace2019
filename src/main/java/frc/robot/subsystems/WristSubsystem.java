/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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
  private double lowerAngleLimit = -10;
  private double topAngleLimit = 170;
  private double wristPower = 0;
  private TalonSRX wristMotor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new AnalogWristCommand());
  }

  // connecting the Wristmotor to a wristmoror subsystem.

  public WristSubsystem() {
    wristMotor = new TalonSRX(RobotMap.wristMotor);
    wristMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0,
        0);
    wristMotor.setSensorPhase(false);

  }

  public double getWristDistanceTicks() {
    return wristMotor.getSelectedSensorPosition();
  }

  public double getRotationAngle() { // get the rotation angle return
    return (((wristMotor.getSelectedSensorPosition()/encoderUnit)/(gearRatio)))*360;
  }

  public boolean isWristAtTop() {
    // if (getRotationAngle() >= topAngleLimit)
    // return true;
    return false;
  }

  public boolean isWristAtBottom() {
    // if (getRotationAngle() <= lowerAngleLimit)
    // return true;
    return false;
  }

  public void setWristPower(double power) {

    // if (getRotationAngle() >= topAngleLimit || getRotationAngle() <=
    // lowerAngleLimit)
    // wristPower = 0;
    // else
    wristPower = power;
  }

  public void updateOutput() {
    wristMotor.set(ControlMode.PercentOutput, wristPower);
  }

  public void resetEncoders() {
    // reset
  }
}
