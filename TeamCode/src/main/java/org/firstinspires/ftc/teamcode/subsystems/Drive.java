package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public class Drive {
    private final Robot robot;

    public Drive(Robot robot, Telemetry telemetry) {
        this.robot = robot;
    }

    /** Send mecanum-style powers; inputs are raw joystick values */
    public void setMecanum(double drive, double strafe, double rotate, double scale) {
        // drive = forward/back (-1..1)
        // strafe = left/right (-1..1)
        // rotate = rotation (-1..1)
        double lf = drive + strafe + rotate;
        double rf = drive - strafe - rotate;
        double lb = drive - strafe + rotate;
        double rb = drive + strafe - rotate;

        // normalize
        double max = Math.max(Math.abs(lf), Math.max(Math.abs(rf), Math.max(Math.abs(lb), Math.abs(rb))));
        if (max > 1.0) {
            lf /= max; rf /= max; lb /= max; rb /= max;
        }

        robot.leftFront.setPower(lf * scale);
        robot.rightFront.setPower(rf * scale);
        robot.leftBack.setPower(lb * scale);
        robot.rightBack.setPower(rb * scale);
    }

    public void allWheelPower(double p1, double p2, double p3, double p4) {
        runWithoutEncoders();
        robot.leftFront.setPower(p1);
        robot.rightFront.setPower(p2);
        robot.leftBack.setPower(p3);
        robot.rightBack.setPower(p4);
    }

    public void stop() {
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);
    }

    public void runToPositionMode() {
        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void runWithoutEncoders() {
        robot.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
    }
}
