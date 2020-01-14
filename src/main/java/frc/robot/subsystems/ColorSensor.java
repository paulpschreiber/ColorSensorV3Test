package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.commands.PrintColor;

import java.util.Set;
import java.util.function.DoubleSupplier;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import org.strykeforce.thirdcoast.telemetry.TelemetryService;
import org.strykeforce.thirdcoast.telemetry.item.Measurable;
import org.strykeforce.thirdcoast.telemetry.item.Measure;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor extends SubsystemBase implements Measurable{
    private TelemetryService telemetryService;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private ColorMatch colorMatcher = new ColorMatch();
    private Color colorUpdated;
  /**
   * Creates a new ExampleSubsystem.
   */
  public ColorSensor() {
    colorMatcher.addColorMatch(Color.kYellow);
    colorMatcher.addColorMatch(Color.kBlue);
    colorMatcher.addColorMatch(Color.kRed);
    colorMatcher.addColorMatch(Color.kGreen);
    telemetryService = Robot.TELEMETRY;
    telemetryService.stop();
    telemetryService.register(this);
    telemetryService.start();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(new PrintColor());
  }

  public void printColorSensor() {
      colorUpdated = colorSensor.getColor();
      System.out.println("RGB: (R: " + colorUpdated.red + ", G:" + colorUpdated.green + ", B: " + colorUpdated.blue + ")");
  }
  
  public void colorID() {
    Color currentColor = colorSensor.getColor();
    ColorMatchResult result = colorMatcher.matchClosestColor(currentColor);
    System.out.println(result);
  }

  public Color getColor() {
    return colorSensor.getColor();
  }

  @Override
  public Set<Measure> getMeasures() {
    // TODO Auto-generated method stub
    return Set.of(
      new Measure("Red", "Red"),
      new Measure("Green", "Green"),
      new Measure("Blue", "Blue")
    );
  }

  @Override
  public DoubleSupplier measurementFor(Measure arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getName()) {
      case "Red":
        return ()->colorUpdated.red;  
      case "Green":
        return ()->colorUpdated.green;
      case "Blue":
        return ()->colorUpdated.blue;
      default:
        return ()->2767.0;
    }
  }

  @Override
  public int compareTo(Measurable arg0) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return "ColorSensor";
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return "ColorSensor";
  }

  @Override
  public int getDeviceId() {
    // TODO Auto-generated method stub
    return 0;
  }
}
