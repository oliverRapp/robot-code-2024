package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drivetrain;

public class Robot {
  public Drivetrain drivetrain;
  public Arm arm;

  public Robot(HardwareMap hwMap) {
    drivetrain = new Drivetrain(hwMap);
    arm = new Arm(hwMap);
  }
}
