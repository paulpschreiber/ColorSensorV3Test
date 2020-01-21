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
public class GoToColor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private WheelSpin wheelSpinSubsystem = new WheelSpin();

    private ColorMatch colorChecker = new ColorMatch();
    private ColorSensor colorSensorSubsystem = Robot.COLORSENSOR;
    private String targetColor;
    private boolean completed;
    
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  
  public GoToColor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(wheelSpinSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
      targetColor = wheelSpinSubsystem.getTargetColor(); // Reads from game data
      completed = false;
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[0]);
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[1]);
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[2]);
      colorChecker.addColorMatch(Constants.WHEEL_COLORS[3]);
      String direction = "c";
      int colorPos = -2767;

      // For finding the color sensor's position on the wheel
      for (int i = 0; i < 4; i++) {
          if (Constants.WHEEL_COLORS[i].equals(colorChecker.matchClosestColor(colorSensorSubsystem.getColor()).color)) {
            colorPos = i;
          }
      }
      
      // Takes the current position and looks up which direction (Left or Right) to go based on the constants class
      switch (targetColor.charAt(0)) {
          case 'Y':
            direction = Constants.WHEEL_POSITIONS[colorPos][0];
            break;
          case 'R':
            direction = Constants.WHEEL_POSITIONS[colorPos][1];
            break;
          case 'G':
            direction = Constants.WHEEL_POSITIONS[colorPos][2];
            break;
          case 'B':
            direction = Constants.WHEEL_POSITIONS[colorPos][3];
            break;
          default:
            completed = true;
      }
      wheelSpinSubsystem.setDirection(direction);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Checks for target color and stops if true
    if (colorSensorSubsystem.getColorName(colorChecker.matchClosestColor(colorSensorSubsystem.getColor()).color).equals(targetColor)) {
        wheelSpinSubsystem.stopSpin();
        completed = true;
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
    return completed;
  }
}
