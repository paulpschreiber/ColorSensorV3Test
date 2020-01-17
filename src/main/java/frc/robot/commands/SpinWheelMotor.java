package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.WheelSpin;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * An example command that uses an example subsystem.
 */
public class SpinWheelMotor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private WheelSpin wheelSpinSubsystem = new WheelSpin();

    private ColorMatch colorChecker = new ColorMatch();
    private ColorSensor colorSensorSubsystem = Robot.COLORSENSOR;
    
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  public SpinWheelMotor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(wheelSpinSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[0]);
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[1]);
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[2]);
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[3]);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (colorChecker.matchClosestColor(colorSensorSubsystem.getColor()).color.equals(Constants.WHEEL_COLORS[3])){
      wheelSpinSubsystem.stopSpin();
    } else {
      wheelSpinSubsystem.startSpin();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
