/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autoCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autocommands.DriveStraightDistance;
import frc.robot.autocommands.SpotTurnDegrees;
import frc.robot.commands.PneumaticsActivate;


public class Level2RightToRightRocketRightSide extends CommandGroup {
  /**
   * Preinstallations: (3) Cargo [front shuttles (2), side shuttle left or right
   * (1)], Robot Hatch Panel Start Position: Level (1) Slightly left from middle
   * platform. Objective: Efficiency Install 2 Hatch Panels in Sandstorm period.
   */
  public Level2RightToRightRocketRightSide() {
    addSequential(new DriveStraightDistance(71.1501));
    addSequential(new SpotTurnDegrees(40.654));
    addSequential(new DriveStraightDistance(141.7506));

    
  }
}
