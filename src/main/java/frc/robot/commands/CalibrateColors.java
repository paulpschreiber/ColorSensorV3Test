package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class CalibrateColors extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private boolean isMatched = false;
    private ColorSensor colorSensorSubsystem = Robot.COLORSENSOR;
    private ColorMatch colorMatchSystem = new ColorMatch();
    private Color perfectRed = ColorMatch.makeColor(1.0, 0.0, 0.0), 
            perfectBlue = ColorMatch.makeColor(0.0, 0.5, 0.5),   
            perfectGreen = ColorMatch.makeColor(0.0, 1.0, 0.0), 
            perfectYellow = ColorMatch.makeColor(0.5, 0.5, 0.0),
            realYellow = ColorMatch.makeColor(0.31005859375, 0.56884765625, 0.120849609375),
            realRed = ColorMatch.makeColor(0.52197265625, 0.347900390625, 0.1298828125),
            realGreen = ColorMatch.makeColor(0.15771484375, 0.5888671875, 0.25341796875),
            realBlue = ColorMatch.makeColor(0.12255859375, 0.431884765625, 0.445556640625);

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
    colorMatchSystem.addColorMatch(realRed);
    colorMatchSystem.addColorMatch(realGreen);
    colorMatchSystem.addColorMatch(realBlue);
    colorMatchSystem.addColorMatch(realYellow);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
      String colorName = "Default";
      Color currentColor = colorSensorSubsystem.getColor();
      ColorMatchResult result = colorMatchSystem.matchClosestColor(currentColor);
      if (result.color.equals(realRed)) {
            colorName = "Red";
      } else if (result.color.equals(realBlue)) {
            colorName = "Blue";
      } else if (result.color.equals(realGreen)) {
            colorName = "Green";
      } else if (result.color.equals(realYellow)) {
            colorName = "Yellow";
      }
      System.out.println("Color: " + colorName + "; Confidence: " + result.confidence);
      colorSensorSubsystem.printColorSensor();
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
