/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autoCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.accessories.XboxController2;
import frc.robot.autocommands.ArmRotateDegrees;
import frc.robot.RobotMap;

public class Modifier2ButtonCombination extends CommandGroup {
  /**
   * OBJECTIVE: CARGO
   * This command group is called while the program still detects that modifier 2 (left bumper)
   * is held.
   */
  public Modifier2ButtonCombination() {
    
    //TO DO: Add wrist commands.

    // DPad up
    if((Robot.m_oi.primaryController.getDPadValue()) == 0)
      addParallel(new ArmRotateDegrees(RobotMap.groundLevelAngle));
      //addParallel(new WristCommand);
      // Command: arm and wrist to loading station (hatch)
    
    // DPad down
    if((Robot.m_oi.primaryController.getDPadValue()) == 180)
      addParallel(new ArmRotateDegrees(RobotMap.groundLevelAngle));
      //addParallel(new WristCommand);
      // Command: arm and wrist to ground to pick up (hatch)
    
    // A Button 
    if(Robot.m_oi.primaryController.xbox.getAButton())
      addParallel(new ArmRotateDegrees(RobotMap.rocketLevel1Angle));
      //addParallel(new WristCommand);
    // Arm and wrist level 1 rocket pos (hatch)

    // X Button 
    if(Robot.m_oi.primaryController.xbox.getXButton())
      addParallel(new ArmRotateDegrees(RobotMap.rocketLevel2Angle));
      //addParallel(new WristCommand);

    // Y Button 
    if(Robot.m_oi.primaryController.xbox.getYButton())
      addParallel(new ArmRotateDegrees(RobotMap.rocketLevel3Angle));
      //addParallel(new WristCommand);
    // Arm and wrist level 3 rocket pos (hatch)

    // B Button 
    if(Robot.m_oi.primaryController.xbox.getBButton())
      addParallel(new ArmRotateDegrees(RobotMap.cargoShipAngle));
      //addParallel(new WristCommand);
  }
}
