/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autoCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autocommands.ArmRotateDegrees;
import frc.robot.autocommands.WristRotateDegrees;

public class ModifierButtonCombination extends CommandGroup {
  /**
   * OBJECTIVE: CARGO This command group is called while the program still detects
   * that modifier 2 (left bumper) is held.
   */

  // Takes in the values to rotate to and does this in parallel
  public ModifierButtonCombination(double armAngle, double wristAngle) {
    System.out.println("ModifierButtonCombination called. ArmAngle: " + armAngle + " WristAngle: " + wristAngle);
    addParallel(new ArmRotateDegrees(armAngle));
    addParallel(new WristRotateDegrees(wristAngle));

    // TO DO: Wrist commands need testing. (For now default is
    // RobotMap.raisedWrist())

    // DPad up
    // if((Robot.m_oi.primaryController.getDPadValue()) == 0) {
    // addParallel(new ArmRotateDegrees(RobotMap.groundLevelAngle));
    // addParallel(new WristRotateDegrees(RobotMap.raisedWrist));
    // arm and wrist to loading station (hatch)
    // }

    // DPad down
    // else if((Robot.m_oi.primaryController.getDPadValue()) == 180) {
    // addParallel(new ArmRotateDegrees(RobotMap.groundLevelAngle));
    // addParallel(new WristRotateDegrees(RobotMap.raisedWrist));
    // arm and wrist to ground to pick up (hatch)
    // }

    /*
     * // A Button if(Robot.m_oi.primaryController.xbox.getAButton()) {
     * addParallel(new ArmRotateDegrees(RobotMap.rocketLevel1Angle));
     * addParallel(new WristRotateDegrees(RobotMap.raisedWrist)); //arm and wrist
     * level 1 rocket pos (hatch) }
     * 
     * // X Button else if(Robot.m_oi.primaryController.xbox.getXButton()) {
     * addParallel(new ArmRotateDegrees(RobotMap.rocketLevel2Angle));
     * addParallel(new WristRotateDegrees(RobotMap.raisedWrist)); //arm and wrist
     * level 2 rocket pos (hatch) }
     * 
     * // Y Button else if(Robot.m_oi.primaryController.xbox.getYButton()) {
     * addParallel(new ArmRotateDegrees(RobotMap.rocketLevel3Angle));
     * addParallel(new WristRotateDegrees(RobotMap.raisedWrist)); //arm and wrist
     * level 3 rocket pos (hatch) }
     * 
     * // B Button else if(Robot.m_oi.primaryController.xbox.getBButton()) {
     * addParallel(new ArmRotateDegrees(RobotMap.cargoShipAngle)); addParallel(new
     * WristRotateDegrees(RobotMap.raisedWrist)); //arm and wrist cargo ship pos
     * (hatch) }
     */
  }
}
