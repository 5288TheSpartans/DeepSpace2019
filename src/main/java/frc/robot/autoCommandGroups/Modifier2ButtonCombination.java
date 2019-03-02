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
    
    //TO DO: Find out how to add parallel method to commands and add wrist commands.

    // DPad up
    //if((Robot.m_oi.primaryController.getDPadValue()) == 0)
      // Command: arm and wrist to loading station (hatch)
    
    // DPad down
    //if((Robot.m_oi.primaryController.getDPadValue()) == 180)
      // Command: arm and wrist to ground to pick up (hatch)
    
    // A Button 
    Robot.m_oi.primaryController.aButton.whenPressed(new ArmRotateDegrees(RobotMap.rocketLevel1Angle));
    // Arm and wrist level 1 rocket pos (hatch)

    // X Button 
    Robot.m_oi.primaryController.xButton.whenPressed(new ArmRotateDegrees(RobotMap.rocketLevel2Angle));
    // Arm and wrist level 2 rocket pos (hatch)

    // Y Button 
    Robot.m_oi.primaryController.yButton.whenPressed(new ArmRotateDegrees(RobotMap.rocketLevel3Angle));
    // Arm and wrist level 3 rocket pos (hatch)

    // B Button 
    Robot.m_oi.primaryController.bButton.whenPressed(new ArmRotateDegrees(RobotMap.cargoShipAngle));
    // Arm and wrist cargo ship pos (hatch)
  }
}
