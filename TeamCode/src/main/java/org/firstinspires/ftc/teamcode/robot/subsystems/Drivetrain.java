package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
  private final double MAX_DRIVE_POWER = 1;

  private DcMotor frontLeft;
  private DcMotor frontRight;
  private DcMotor backLeft;
  private DcMotor backRight;

  public Drivetrain(HardwareMap hwMap) {
    frontLeft = hwMap.get(DcMotor.class, "frontLeft"); // 0
    frontRight = hwMap.get(DcMotor.class, "frontRight");
    backLeft = hwMap.get(DcMotor.class, "backLeft"); // 1
    backRight = hwMap.get(DcMotor.class, "backRight");

    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    backRight.setDirection(DcMotorSimple.Direction.FORWARD);

    frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }

  public double[] driveFluid(double drive, double strafe, double twist) {
    double[] powers = {
      (drive + strafe + twist),
      (drive - strafe - twist),
      (drive - strafe + twist),
      (drive + strafe - twist),
    };

    double max = Double.NEGATIVE_INFINITY;
    for (double p : powers) {
      double n = Math.abs(p);
      if (n > max) max = n;
    }

    if (max > 1) {
      for (int i = 0; i < 4; i++) {
        powers[i] /= max;
      }
    }

    setMotorPowers(powers);
    return powers;
  }

  private void setMotorPowers(double[] powers) {
    frontLeft.setPower(powers[0]);
    frontRight.setPower(powers[1]);
    backLeft.setPower(powers[2]);
    backRight.setPower(powers[3]);
  }
}

