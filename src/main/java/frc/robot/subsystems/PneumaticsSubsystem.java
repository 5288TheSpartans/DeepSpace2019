/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class PneumaticsSubsystem extends Subsystem {
  private DoubleSolenoid intakeSolenoid;
  private Compressor mainCompressor;

  private enum intakeSolenoidState {
    solenoidOut, solenoidIn;
  }

  intakeSolenoidState doubleIntakeSolenoid = intakeSolenoidState.solenoidIn;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public PneumaticsSubsystem() {
    mainCompressor = new Compressor();
    mainCompressor.start();
    intakeSolenoid = new DoubleSolenoid(RobotMap.leftIntakeSolenoid, RobotMap.rightIntakeSolenoid);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void flipSolenoid() {
    if (doubleIntakeSolenoid == intakeSolenoidState.solenoidIn) {
      intakeSolenoid.set(Value.kForward);
      doubleIntakeSolenoid = intakeSolenoidState.solenoidOut;
    } else {
      intakeSolenoid.set(Value.kReverse);
      doubleIntakeSolenoid = intakeSolenoidState.solenoidIn;
    }
  }

}
