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
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * Add your docs here.
 */
public class ModifierCombo extends Button {

    JoystickButton m_modifierButton;
    Button m_activator;

    public ModifierCombo(JoystickButton modifierButton, POVButton activator) {
        m_modifierButton = modifierButton;
        m_activator = activator;

    }
    public ModifierCombo(JoystickButton modifierButton, JoystickButton activator) {
        m_modifierButton = modifierButton;
        m_activator = activator;

    }

    public boolean get() {
        return m_modifierButton.get() && m_activator.get();
    }

}
