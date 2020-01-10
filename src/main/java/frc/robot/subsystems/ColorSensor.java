package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor extends SubsystemBase {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private ColorMatch colorMatcher = new ColorMatch();
  /**
   * Creates a new ExampleSubsystem.
   */
  public ColorSensor() {
    colorMatcher.addColorMatch(Color.kYellow);
    colorMatcher.addColorMatch(Color.kBlue);
    colorMatcher.addColorMatch(Color.kRed);
    colorMatcher.addColorMatch(Color.kGreen);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void printColorSensor() {
      Color color = colorSensor.getColor();
      System.out.println("RGB: (R: " + color.red + ", G:" + color.green + ", B: " + color.blue + ")");
  }
  
  public void colorID() {
    Color currentColor = colorSensor.getColor();
    ColorMatchResult result = colorMatcher.matchClosestColor(currentColor);
    System.out.println(result);
  }

  public Color getColor() {
    return colorSensor.getColor();
  }
}
