package frc.robot.accessories;

//import com.sun.tools.javac.tree.JCtree.JCTypeParameter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LogitechController extends Joystick {
	
	public JoystickButton xButton,yButton,aButton,bButton,
	rightBumper,leftBumper,startButton,selectButton
	,leftStickButton, rightStickButton;
	
	public LogitechController(int port) {
		super(port);
		xButton = new JoystickButton(this, 3);	
		yButton = new JoystickButton(this, 4);
		aButton = new JoystickButton(this, 1);
		bButton = new JoystickButton(this, 2);
		rightBumper = new JoystickButton(this, 6);
		leftBumper = new JoystickButton(this, 5);
		startButton = new JoystickButton(this, 8);
		selectButton = new JoystickButton(this, 7);
		leftStickButton = new JoystickButton(this, 9);
		rightStickButton = new JoystickButton(this, 10);
	}

	public double getLeftStickX() {
	//	return this.getRawAxis(0);
		return this.getX(Hand.kLeft);
	}

	public double getLeftStickY() {
		//return this.getRawAxis(1);
		return this.getY(Hand.kLeft);
	}

	public double getRightStickX() { 
	//	return this.getRawAxis(4);
		return this.getX(Hand.kRight);
	}

	public double getRightStickY() {
	//	return this.getRawAxis(5);
		return this.getY(Hand.kRight);
	}

	public double getLeftAnalogTrigger() {
		return this.getRawAxis(2);
	}

	public double getRightAnalogTrigger() {
		return this.getRawAxis(3);
	}
	public double getDPadValue() {
		try {
		return	this.getPOV();
		} catch(NullPointerException e) {
			return -1;
		}
	}
	
}

