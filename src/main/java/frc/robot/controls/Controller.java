package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CalibrateRed;
import frc.robot.commands.CalibrateGreen;
import frc.robot.commands.CalibrateBlue;
import frc.robot.commands.CalibrateYellow;
import frc.robot.commands.GoToColor;
import frc.robot.commands.IdentifyColor;
import frc.robot.commands.PrintColor;
import frc.robot.commands.SpinWheelMotor;

public class Controller {
    Joystick joystick = new Joystick(2);

    public Controller() {
        new JoystickButton(joystick, 5).whenPressed(new PrintColor());
        new JoystickButton(joystick, 6).toggleWhenPressed(new SpinWheelMotor());
        new JoystickButton(joystick, 7).whenPressed(new IdentifyColor());
        new JoystickButton(joystick, 8).whenPressed(new GoToColor());
        new JoystickButton(joystick, 3).whenPressed(new CalibrateRed());
        new JoystickButton(joystick, 2).whenPressed(new CalibrateGreen());
        new JoystickButton(joystick, 1).whenPressed(new CalibrateBlue());
        new JoystickButton(joystick, 4).whenPressed(new CalibrateYellow());
    }
}

