package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class CalibrateColors extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private boolean isMatched = false;
    private ColorSensor colorSensorSubsystem = Robot.COLORSENSOR;
    private ColorMatch colorMatchSystem = new ColorMatch();
    private Color8Bit perfectRed = new Color8Bit(255, 0, 0), 
            perfectBlue = new Color8Bit(0, 255, 255),   
            perfectGreen = new Color8Bit(0, 255, 0), 
            perfectYellow = new Color8Bit(255, 255, 0);

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  public CalibrateColors() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSensorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    colorMatchSystem.addColorMatch(new Color(perfectRed));
    colorMatchSystem.addColorMatch(new Color(perfectGreen));
    colorMatchSystem.addColorMatch(new Color(perfectBlue));
    colorMatchSystem.addColorMatch(new Color(perfectYellow));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
      String colorName = "Default";
      Color currentColor = colorSensorSubsystem.getColor();
      ColorMatchResult result = colorMatchSystem.matchClosestColor(currentColor);
      if (result.color.equals(new Color(perfectRed))) {
            colorName = "Red";
      } else if (result.color.equals(new Color(perfectBlue))) {
            colorName = "Blue";
      } else if (result.color.equals(new Color(perfectGreen))) {
            colorName = "Green";
      } else if (result.color.equals(new Color(perfectYellow))) {
            colorName = "Yellow";
      }
      System.out.println("Color: " + colorName + "; Confidence: " + result.confidence);
      isMatched = true;
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
    return isMatched;
  }
}
