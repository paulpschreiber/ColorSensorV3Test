package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.WheelSpin;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class SpinWheelMotor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private WheelSpin wheelSpinSubsystem = new WheelSpin();

    private boolean matchesColor = false;
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
      colorChecker.addColorMatch(Color.kYellow);
      colorChecker.addColorMatch(Color.kGreen);
      colorChecker.addColorMatch(Color.kBlue);
      colorChecker.addColorMatch(Color.kRed);
      wheelSpinSubsystem.startSpin();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    if (colorChecker.matchClosestColor(colorSensorSubsystem.getColor()).color.equals(Color.kYellow)) {
        matchesColor = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
      wheelSpinSubsystem.stopSpin();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return matchesColor;
  }
}