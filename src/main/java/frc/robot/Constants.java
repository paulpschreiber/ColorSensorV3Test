/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static Color[] WHEEL_COLORS = new Color[4]; // Yellow, Red, Green, Blue
    public static String[][] WHEEL_POSITIONS = {
        {"c", "r", "r", "l"}, // Yellow; Order of strings goes to Target: Y, R, G, B
        {"l", "c", "r", "r"}, // Red
        {"r", "l", "c", "r"}, // Green
        {"r", "r", "l", "c"}}; // Blue
}
