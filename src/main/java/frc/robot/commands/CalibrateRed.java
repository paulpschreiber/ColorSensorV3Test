package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class CalibrateRed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private ColorSensor colorSensorSubsystem = Robot.COLORSENSOR;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  public CalibrateRed() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSensorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
      Constants.WHEEL_COLORS[0] = colorSensorSubsystem.getColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
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
    return true;
  }
}
