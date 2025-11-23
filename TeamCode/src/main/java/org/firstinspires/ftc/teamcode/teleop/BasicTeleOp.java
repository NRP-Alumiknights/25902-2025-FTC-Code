package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name = "Basic TeleOp", group = "Test")
public class BasicTeleOp extends OpMode {

    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.init();
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double r = gamepad1.right_stick_x;

        // simple mecanum math
        double lf = y + x + r;
        double rf = y - x - r;
        double lb = y - x + r;
        double rb = y + x - r;

        robot.lf.setPower(lf);
        robot.rf.setPower(rf);
        robot.lb.setPower(lb);
        robot.rb.setPower(rb);

        telemetry.addData("lf", lf);
        telemetry.addData("rf", rf);
        telemetry.addData("lb", lb);
        telemetry.addData("rb", rb);
        telemetry.update();
    }
}
