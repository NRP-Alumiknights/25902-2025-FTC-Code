package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.drive.Drive;

@TeleOp(name = "TeleOp25", group = "ITD")
public class TeleOp25 extends LinearOpMode {

    Robot robot;
    Drive drive;

    @Override
    public void runOpMode() {

        // Initialize robot hardware
        robot = new Robot(hardwareMap);
        drive = new Drive(robot, telemetry);

        double drivefb = 0.0;
        double strafe = 0.0;
        double rotate = 0.0;

        waitForStart();

        while (opModeIsActive()) {

            drivefb = -gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            rotate = gamepad1.right_stick_x;
            // Drive using your mecanum method
            drive.setMecanum(
                    drivefb,   // drive (forward/back)
                    strafe,    // strafe (left/right)
                    rotate,   // rotate (turn)
                    1.0                       // scale
            );


            // Turbo / Slow mode


            //drive.update();  // OPTIONAL if your drive system uses any tracking
            telemetry.update();
        }
    }
}
