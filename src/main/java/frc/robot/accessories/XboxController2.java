package frc.robot.accessories;


//import com.sun.tools.javac.tree.JCtree.JCTypeParameter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class XboxController2 {
	int m_port = 0;
	public XboxController2(int port) {
		m_port = port;
	}
	public XboxController xbox = new XboxController(m_port);
	
	public JoystickButton xButton = new JoystickButton(xbox, 3);	
	public JoystickButton yButton = new JoystickButton(xbox, 4);
	public JoystickButton aButton = new JoystickButton(xbox, 1);
	public JoystickButton bButton = new JoystickButton(xbox, 2);
	public JoystickButton rightBumper = new JoystickButton(xbox, 6);
	public JoystickButton leftBumper = new JoystickButton(xbox, 5);
	public JoystickButton startButton = new JoystickButton(xbox, 8);
	public JoystickButton selectButton = new JoystickButton(xbox, 7);
	public JoystickButton leftStickButton = new JoystickButton(xbox, 9);
	public JoystickButton rightStickButton = new JoystickButton(xbox, 10);
	public JoystickButton rightTriggerButton = new JoystickButton(xbox, 11);
	public JoystickButton leftTriggerButton = new JoystickButton(xbox, 12);
	



	public double getLeftStickX() {
	//	return this.getRawAxis(0);
		return xbox.getX(Hand.kLeft);
	}

	public double getLeftStickY() {
		//return this.getRawAxis(1);
		return xbox.getX(Hand.kRight);
	}

	public double getRightStickX() { 
	//	return this.getRawAxis(4);
		return xbox.getX(Hand.kRight);
	}

	public double getRightStickY() {
	//	return this.getRawAxis(5);
		return xbox.getY(Hand.kRight);
	}

	public double getLeftAnalogTrigger() {
		return xbox.getTriggerAxis(Hand.kLeft);
	}

	public double getRightAnalogTrigger() {
		return xbox.getTriggerAxis(Hand.kRight);
	}

	
}

