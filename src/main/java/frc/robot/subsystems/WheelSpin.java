package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;

public class WheelSpin extends SubsystemBase {

    private TalonSRX wheelMotor = new TalonSRX(6);

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private ColorMatch colorMatcher = new ColorMatch();
  /**
   * Creates a new ExampleSubsystem.
   */
  public WheelSpin() {
    colorMatcher.addColorMatch(Constants.WHEEL_COLORS[0]);
    colorMatcher.addColorMatch(Constants.WHEEL_COLORS[1]);
    colorMatcher.addColorMatch(Constants.WHEEL_COLORS[2]);
    colorMatcher.addColorMatch(Constants.WHEEL_COLORS[3]);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void startSpin() 
  {
        wheelMotor.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopSpin() {
      wheelMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void setDirection(String d) {
    double motorPower = 0.0;
    if (d.equals("l")) {
      motorPower = -0.5;
    } else {
      motorPower = 0.5;
    }
    wheelMotor.set(ControlMode.PercentOutput, motorPower);
  }

  public ColorSensorV3 getColorSensor() {
      return colorSensor;
  }

  public String getTargetColor() {
    return DriverStation.getInstance().getGameSpecificMessage();
  }

  
}
