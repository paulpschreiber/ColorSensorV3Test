package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateColors;
import frc.robot.commands.PrintColor;
import frc.robot.commands.SpinWheelMotor;

public class Controller {
    Joystick joystick = new Joystick(2);

    public Controller() {
        new JoystickButton(joystick, 2).whenPressed(new PrintColor());
        new JoystickButton(joystick, 1).toggleWhenPressed(new SpinWheelMotor());
        new JoystickButton(joystick, 3).whenPressed(new CalibrateColors());
    }
}

