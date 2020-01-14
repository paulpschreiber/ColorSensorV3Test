package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class WheelSpin extends SubsystemBase {

    private TalonSRX wheelMotor = new TalonSRX(6);

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private ColorMatch colorMatcher = new ColorMatch();
  /**
   * Creates a new ExampleSubsystem.
   */
  public WheelSpin() {
    colorMatcher.addColorMatch(Color.kYellow);
    colorMatcher.addColorMatch(Color.kBlue);
    colorMatcher.addColorMatch(Color.kRed);
    colorMatcher.addColorMatch(Color.kGreen);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void startSpin() {
        wheelMotor.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopSpin() {
      wheelMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public ColorSensorV3 getColorSensor() {
      return colorSensor;
  }

  
}
