/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DrivetrainSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private double leftPower, rightPower;
  private double numberOfTicks = 4096;

  private Encoder leftEncoder = new Encoder(RobotMap.leftDriveEncoder1,RobotMap.leftDriveEncoder2,false, EncodingType.k4X);
  private Encoder rightEncoder = new Encoder(RobotMap.rightDriveEncoder1,RobotMap.rightDriveEncoder2,true, EncodingType.k4X);

  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();


  private VictorSP leftDriveMotor1 = new VictorSP(0);
  private VictorSP leftDriveMotor2 = new VictorSP(0);
  private VictorSP leftDriveMotor3 = new VictorSP(0);

  private VictorSP rightDriveMotor1 = new VictorSP(0);
  private VictorSP rightDriveMotor2 = new VictorSP(0);
  private VictorSP rightDriveMotor3 = new VictorSP(0);



  public DrivetrainSubsystem() {
    leftEncoder.setDistancePerPulse((6*Math.PI) /numberOfTicks);
    rightEncoder.setDistancePerPulse((6*Math.PI) /numberOfTicks);

    leftEncoder.setMaxPeriod(5);
		rightEncoder.setMaxPeriod(5);
		leftEncoder.setMinRate(0);
		rightEncoder.setMinRate(0);
		leftEncoder.setSamplesToAverage(1);
		rightEncoder.setSamplesToAverage(1);

		gyro.calibrate();



  }

  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  // Set power to the LEFT side of the drivetrain.
  public void setLeftPower(double power) {
    leftPower = power;

  }
  // Set power to the RIGHT side of the drivetrain.
  public void setRightPower(double power) {
    rightPower = power;
  }

  public void updateOutputs() {

    leftDriveMotor1.set(leftPower);
    leftDriveMotor2.set(leftPower);
    leftDriveMotor3.set(leftPower);

    rightDriveMotor1.set(rightPower);
    rightDriveMotor2.set(rightPower);
    rightDriveMotor3.set(rightPower);
  }
  public double getLeftDistanceInches() {
    return leftEncoder.getDistance();
  }
  public double getRightDistanceInches() {
    return rightEncoder.getDistance();
  }

  public double getGyroAngle() {
		return gyro.getAngle();
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

}
