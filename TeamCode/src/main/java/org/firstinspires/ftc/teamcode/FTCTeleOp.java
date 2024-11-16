package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Main TeleOp")
public class FTCTeleOp extends OpMode {
  private Robot robot;

  private double deadZone = 0.0;

  @Override
  public void init() {
    robot = new Robot(this.hardwareMap);

    telemetry.addData(">", "Initialized");
    telemetry.update();
  }

  @Override
  public void loop() {
    Gamepad g = gamepad2;

    double leftStickY = g.left_stick_y;
    double leftStickX = g.left_stick_x;
    double rightStickX = g.right_stick_x;
    double leftTrigger = g.left_trigger;
    double rightTrigger = g.right_trigger;
    boolean dpadLeft = g.dpad_left;
    boolean dpadRight = g.dpad_right;
    boolean aButton = g.a;

    if (aButton) {
      double angle = Math.atan2(leftStickY, leftStickX);
      leftStickX = Math.cos(angle);
      leftStickY = Math.sin(angle);
      if (rightStickX != 0) rightStickX = Math.signum(rightStickX) * 1;
    } else {
      leftStickX = Math.pow(leftStickX, 3);
      leftStickY = Math.pow(leftStickY, 3);
      rightStickX = Math.pow(rightStickX, 3);

      leftStickY = Math.signum(leftStickY) * (Math.abs(leftStickY) * (1 - deadZone) + deadZone);
      leftStickX = Math.signum(leftStickX) * (Math.abs(leftStickX) * (1 - deadZone) + deadZone);
      rightStickX = Math.signum(rightStickX) * (Math.abs(rightStickX) * (1 - deadZone) + deadZone);
    }

    double[] powers = robot.drivetrain.driveFluid(leftStickY, leftStickX, rightStickX);

    if (leftTrigger != 0 && rightTrigger == 0) {
      robot.arm.setPower(-leftTrigger);
    }
    if (rightTrigger != 0 && leftTrigger == 0) {
      robot.arm.setPower(rightTrigger);
    }
    if (rightTrigger == 0 && leftTrigger == 0) {
      robot.arm.setPower(0);
    }

    telemetry.addData("left y", leftStickY);
    telemetry.addData("left x", leftStickX);
    telemetry.addData("right x", rightStickX);
    telemetry.addData("front left power", powers[0]);
    telemetry.addData("front right power", powers[1]);
    telemetry.addData("back left power", powers[2]);
    telemetry.addData("back right power", powers[3]);
    telemetry.addData("arm power", robot.arm.motor.getCurrentPosition());
    telemetry.update();
  }
}
