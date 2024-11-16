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
    double leftStickY1 = gamepad1.left_stick_y;
    double leftStickX1 = gamepad1.left_stick_x;
    double rightStickX1 = gamepad1.right_stick_x;
    boolean xButton1 = gamepad1.x;

    double leftTrigger2 = gamepad2.left_trigger;
    double rightTrigger2 = gamepad2.right_trigger;
    boolean aButton2 = gamepad2.a;
    boolean xButton2 = gamepad2.x;

    if (aButton2) {
      double angle = Math.atan2(leftStickY1, leftStickX1);
      leftStickX1 = Math.cos(angle);
      leftStickY1 = Math.sin(angle);
      if (rightStickX1 != 0) rightStickX1 = Math.signum(rightStickX1) * 1;
    } else {
      leftStickX1 = Math.pow(leftStickX1, 3);
      leftStickY1 = Math.pow(leftStickY1, 3);
      rightStickX1 = Math.pow(rightStickX1, 3);

      leftStickY1 = Math.signum(leftStickY1) * (Math.abs(leftStickY1) * (1 - deadZone) + deadZone);
      leftStickX1 = Math.signum(leftStickX1) * (Math.abs(leftStickX1) * (1 - deadZone) + deadZone);
      rightStickX1 =
          Math.signum(rightStickX1) * (Math.abs(rightStickX1) * (1 - deadZone) + deadZone);
    }

    double[] powers = robot.drivetrain.driveFluid(leftStickY1, leftStickX1, rightStickX1);

    if (leftTrigger2 != 0 && rightTrigger2 == 0) {
      robot.arm.setSlidePower(-leftTrigger2);
    }
    if (rightTrigger2 != 0 && leftTrigger2 == 0) {
      robot.arm.setSlidePower(rightTrigger2);
    }
    if (rightTrigger2 == 0 && leftTrigger2 == 0) {
      robot.arm.setSlidePower(0);
    }

    // telemetry.addData("left y", leftStickY1);
    // telemetry.addData("left x", leftStickX1);
    // telemetry.addData("right x", rightStickX1);
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
