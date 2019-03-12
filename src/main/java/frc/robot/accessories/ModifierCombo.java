//HHHHH ITS MY  CHILD, THE MODIFIERS REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.accessories;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class ModifierCombo extends Button {

    Button modifierButton;
    Button activator;

    public ModifierCombo(Button modifierButton, Button activator) {
        this.modifierButton = modifierButton;

    }

    @Override
    public boolean get() {
        return modifierButton.get() && activator.get();
    }

}
