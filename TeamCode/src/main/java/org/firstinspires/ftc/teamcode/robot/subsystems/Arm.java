package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
  public DcMotor motor;

  public Arm(HardwareMap hwMap) {
    motor = hwMap.get(DcMotor.class, "arm");

    motor.setDirection(DcMotor.Direction.FORWARD);
    motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }

  public void setPower(double power) {
    motor.setPower(power);
  }
}
