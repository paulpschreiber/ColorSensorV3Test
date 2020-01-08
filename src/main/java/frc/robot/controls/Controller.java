package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.PrintColor;

public class Controller {
    Joystick joystick = new Joystick(2);

    public Controller() {
        new JoystickButton(joystick, 2).whenPressed(new PrintColor());
    }
}

