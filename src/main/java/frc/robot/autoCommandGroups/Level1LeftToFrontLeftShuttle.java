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

public class Level1LeftToFrontLeftShuttle extends CommandGroup {
  /**
   * Objective:
   */
  public Level1LeftToFrontLeftShuttle() {
    // drive straight for 133..
    addSequential(new DriveStraightDistance(133));
    // Place Hatch on Left Front Shuttle
    addSequential(new PneumaticsActivate());
    // turn left
    addSequential(new SpotTurnDegrees(-90));
    // drive straight
    addSequential(new DriveStraightDistance(15));
    // turn on the spot left
    addSequential(new SpotTurnDegrees(-60));
    // drive straight
    addSequential(new DriveStraightDistance(211.9));
    // spot turn left
    addSequential(new SpotTurnDegrees(-30));
    // driving straight
    addSequential(new DriveStraightDistance(5));

    // Load Hatch Panel

    // turn on the spot right
    addSequential(new SpotTurnDegrees(180));
    // driving straight
    addSequential(new DriveStraightDistance(5));
    // turning on the spot right
    addSequential(new SpotTurnDegrees(30));
    // driving straight
    addSequential(new DriveStraightDistance(211.9));
    // spot turn right
    addSequential(new SpotTurnDegrees(30));
    // drive straight
    addSequential(new DriveStraightDistance(20));
    // spot turn left
    addSequential(new SpotTurnDegrees(-90));
    // spot turn, right
    addSequential(new SpotTurnDegrees(30));
    addSequential(new PneumaticsActivate());
    // HI was here
  }
}
