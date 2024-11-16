package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
    double leftStickY = gamepad1.left_stick_y;
    double leftStickX = gamepad1.left_stick_x;
    double rightStickX = gamepad1.right_stick_x;
    boolean xButton1 = gamepad1.x;

    double leftTrigger = gamepad2.left_trigger;
    double rightTrigger = gamepad2.right_trigger;
    boolean aButton = gamepad2.a;
    boolean xButton2 = gamepad2.x;

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

    // telemetry.addData("left y", leftStickY);
    // telemetry.addData("left x", leftStickX);
    // telemetry.addData("right x", rightStickX);
    // telemetry.addData("front left power", powers[0]);
    // telemetry.addData("front right power", powers[1]);
    // telemetry.addData("back left power", powers[2]);
    // telemetry.addData("back right power", powers[3]);
    if (xButton1) {
      telemetry.addData("gamepad: ", "1111111111");
    }
    if (xButton2) {
      telemetry.addData("gamepad: ", "2222222222");
    }

    telemetry.update();
  }
}
