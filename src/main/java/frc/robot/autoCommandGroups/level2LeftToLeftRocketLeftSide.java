/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autoCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.autocommands.ArmRotateDegrees;
import frc.robot.autocommands.DriveStraightDistance;
import frc.robot.autocommands.SpotTurnDegrees;
import frc.robot.commands.PneumaticsActivate;

public class level2LeftToLeftRocketLeftSide extends CommandGroup {
  /**
   * Add your docs here.
   */
  public level2LeftToLeftRocketLeftSide() {

    addSequential(new DriveStraightDistance(100));
    addSequential(new SpotTurnDegrees(-60));
    addSequential(new DriveStraightDistance(80));
    addSequential(new PneumaticsActivate());
    addSequential(new DriveStraightDistance(-80));
    addSequential(new SpotTurnDegrees(-120));
    addSequential(new DriveStraightDistance(201.13));
    addSequential(new PneumaticsActivate());
    addSequential(new DriveStraightDistance(201.13));
    addParallel(new SpotTurnDegrees(-205));
    addParallel(new ArmRotateDegrees(RobotMap.RocketLevel2));
    addSequential(new PneumaticsActivate());
  }
}
