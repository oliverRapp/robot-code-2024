package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
  private final double PIVOT_UP_POSITION = 0.0;
  private final double PIVOT_DOWN_POSITION = 0.5;
  private final double GRIPPER_MOVEMENT = 0.01;

  public DcMotor motor;
  public Servo pivotServo;
  public Servo gripperServo;

  public void Arm(HardwareMap hwMap) {
    motor = hwMap.get(DcMotor.class, "arm");

    motor.setDirection(DcMotor.Direction.FORWARD);
    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    pivotServo = hwMap.get(Servo.class, "pivot");
    gripperServo = hwMap.get(Servo.class, "gripper");

    pivotServo.setDirection(Servo.Direction.FORWARD);
    gripperServo.setDirection(Servo.Direction.FORWARD);
  }

  public void setSlidePower(double power) {
    motor.setPower(power);
  }

  public void pivotUp() {
    pivotServo.setPosition(PIVOT_UP_POSITION);
  }

  public void pivotDown() {
    pivotServo.setPosition(PIVOT_DOWN_POSITION);
  }
}
