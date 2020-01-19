package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class IdentifyColor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private boolean isMatched = false;
    private ColorSensor colorSensorSubsystem = Robot.COLORSENSOR;
    private ColorMatch colorMatchSystem = new ColorMatch();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  public IdentifyColor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSensorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    //Constant color array is formatted 0-3 as Yellow, Red, Green, Blue
    colorMatchSystem.addColorMatch(Constants.WHEEL_COLORS[0]);
    colorMatchSystem.addColorMatch(Constants.WHEEL_COLORS[1]);
    colorMatchSystem.addColorMatch(Constants.WHEEL_COLORS[2]);
    colorMatchSystem.addColorMatch(Constants.WHEEL_COLORS[3]);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
      String colorName = "Default";
      Color currentColor = colorSensorSubsystem.getColor();
      ColorMatchResult result = colorMatchSystem.matchClosestColor(currentColor);
      if (result.color.equals(Constants.WHEEL_COLORS[1])) {
            colorName = "Red";
      } else if (result.color.equals(Constants.WHEEL_COLORS[3])) {
            colorName = "Blue";
      } else if (result.color.equals(Constants.WHEEL_COLORS[2])) {
            colorName = "Green";
      } else if (result.color.equals(Constants.WHEEL_COLORS[0])) {
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
