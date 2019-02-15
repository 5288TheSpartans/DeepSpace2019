/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autoCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autocommands.ArmRotateDegrees;
import frc.robot.autocommands.DriveStraightDistance;
import frc.robot.autocommands.SpotTurnDegrees;
import frc.robot.commands.PneumaticsActivate;

public class Level1LeftToFrontShuttle extends CommandGroup {
  /**
   * Preinstallations: (3) Cargo [front shuttles (2), side shuttle left or right
   * (1)], Robot Hatch Panel Start Position: Level (1) Slightly left from middle
   * platform. Objective: Efficiency Install 2 Hatch Panels.
   */
  public Level1LeftToFrontShuttle() {

    addSequential(new DriveStraightDistance(133));
    // Place Hatch on Left Front Shuttle
    addSequential(new PneumaticsActivate());
    addSequential(new SpotTurnDegrees(-90));
    addSequential(new DriveStraightDistance(15));
    addSequential(new SpotTurnDegrees(-60));
    addSequential(new DriveStraightDistance(211.9));
    addSequential(new SpotTurnDegrees(-30));
    addSequential(new DriveStraightDistance(5));
    // Load Hatch Panel
    addSequential(new SpotTurnDegrees(180));
    addSequential(new DriveStraightDistance(5));
    addSequential(new SpotTurnDegrees(30));
    addSequential(new DriveStraightDistance(211.9));
    addSequential(new SpotTurnDegrees(30));
    addSequential(new DriveStraightDistance(20));
    addSequential(new SpotTurnDegrees(-90));
    addSequential(new SpotTurnDegrees(30));
    addSequential(new PneumaticsActivate());
  }
}
