package frc.robot.accessories;


//import com.sun.tools.javac.tree.JCtree.JCTypeParameter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class LogitechController {
	int m_port = 0;
	public LogitechController(int port) {
		m_port = port;
	}
	public Joystick controller = new Joystick(m_port);
	
	public JoystickButton xButton = new JoystickButton(controller, 3);	
	public JoystickButton yButton = new JoystickButton(controller, 4);
	public JoystickButton aButton = new JoystickButton(controller, 1);
	public JoystickButton bButton = new JoystickButton(controller, 2);
	public JoystickButton rightBumper = new JoystickButton(controller, 6);
	public JoystickButton leftBumper = new JoystickButton(controller, 5);
	public JoystickButton startButton = new JoystickButton(controller, 8);
	public JoystickButton selectButton = new JoystickButton(controller, 7);
	public JoystickButton leftStickButton = new JoystickButton(controller, 9);
	public JoystickButton rightStickButton = new JoystickButton(controller, 10);
	

	public double getLeftStickX() {
	//	return this.getRawAxis(0);
		return controller.getX(Hand.kLeft);
	}

	public double getLeftStickY() {
		//return this.getRawAxis(1);
		return controller.getY(Hand.kLeft);
	}

	public double getRightStickX() { 
	//	return this.getRawAxis(4);
		return controller.getX(Hand.kRight);
	}

	public double getRightStickY() {
	//	return this.getRawAxis(5);
		return controller.getY(Hand.kRight);
	}

	public double getLeftAnalogTrigger() {
		return controller.getRawAxis(2);
	}

	public double getRightAnalogTrigger() {
		return controller.getRawAxis(3);
	}
	public double getDPadValue() {
		try {
		return	controller.getPOV();
		} catch(NullPointerException e) {
			return -1;
		}
	}
	
}

