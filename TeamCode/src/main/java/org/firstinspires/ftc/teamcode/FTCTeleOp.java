package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name="Main TeleOp")
public class FTCTeleOp extends OpMode {
    private double deadZone = 0.0;

    private Robot robot;

   @Override
   public void init() {
       robot = new Robot(this.hardwareMap);

       telemetry.addData(">", "Initialized");
       telemetry.update();
   }

    @Override
    public void loop() {
       Gamepad g = gamepad2;

       double gpLeftStickY = g.left_stick_y;
       double gpLeftStickX = g.left_stick_x;
       double gpRightStickX = g.right_stick_x;
       double gpDPadLeft = g.dpad_left;
       double gpDPadRight = g.dpad_right;

       gpLeftStickY = Math.signum(gpLeftStickY) * (Math.abs(gpLeftStickY) * (1 - deadZone) + deadZone);
       gpLeftStickX = Math.signum(gpLeftStickX) * (Math.abs(gpLeftStickX) * (1 - deadZone) + deadZone);
       gpRightStickX = Math.signum(gpRightStickX) * (Math.abs(gpRightStickX) * (1 - deadZone) + deadZone);

       if (gpDPadLeft) deadZone -= 0.01;
       if (gpDPadRight) deadZone += 0.01;

       double[] powers = robot.drivetrain.driveFluid(gpLeftStickY, gpLeftStickX, gpRightStickX);

       //telemetry.addData("left y", gpLeftStickY);
       //telemetry.addData("left x", gpLeftStickX);
       //telemetry.addData("right x", gpRightStickX);
       //telemetry.addData("front left power", powers[0]);
       //telemetry.addData("front right power", powers[1]);
       //telemetry.addData("back left power", powers[2]);
       //telemetry.addData("back right power", powers[3]);
       telemetry.addData("dead zone", deadZone);
       telemetry.update();
    }
}
