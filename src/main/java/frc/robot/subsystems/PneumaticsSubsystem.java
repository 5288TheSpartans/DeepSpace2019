/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class PneumaticsSubsystem extends Subsystem {
  private Solenoid intakeSolenoid;
  public Compressor mainCompressor;

  private boolean isExtended = false;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public PneumaticsSubsystem() {
    mainCompressor = new Compressor(RobotMap.PCMID);
    mainCompressor.start();
    System.out.println("Initializing PneumaticsSubsystem.");
    intakeSolenoid = new Solenoid(RobotMap.PCMID,RobotMap.intakeSolenoidChannel);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void flipSolenoid() {
    isExtended = !isExtended;
    intakeSolenoid.set(isExtended);
   
  }
  public boolean getIsExtended() {
    return isExtended;
  }

}
