/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autoCommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.autocommands.DriveStraightDistance;
import frc.robot.autocommands.SpotTurnDegrees;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PneumaticsActivate;
public class Level1RightToFrontShuttle extends CommandGroup {
  /**
   * Objective: Place hatch on front right shuttle,
   */

  public Level1RightToFrontShuttle() {

    addSequential(new DriveStraightDistance(170.52));
    addSequential(new PneumaticsActivate());
    addSequential(new SpotTurnDegrees(90));
    addSequential(new DriveStraightDistance(48.2));
    addSequential(new SpotTurnDegrees(90));
    addSequential(new DriveStraightDistance(218));
    addSequential(new IntakeCommand(RobotMap.intakeSpeed));
    addSequential(new SpotTurnDegrees(-90));
    addSequential(new DriveStraightDistance(1));
    addSequential(new SpotTurnDegrees(-90));
    addSequential(new DriveStraightDistance(172));
    addSequential(new IntakeCommand(RobotMap.shootBallSlow));
    
}
}
