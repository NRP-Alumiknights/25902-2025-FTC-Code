package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Load;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

@TeleOp(name = "TeleOp25", group = "ITD")
public class TeleOp25 extends LinearOpMode {

    Robot robot;
    Drive drive;
    Launcher launcher;
    Load loader;
    Turret turret;

    @Override
    public void runOpMode() {

        // Initialize robot hardware
        robot = new Robot(hardwareMap);
        drive = new Drive(robot, telemetry);
        launcher = new Launcher(robot, telemetry);
        loader = new Load(robot, telemetry);
        turret = new Turret(robot, telemetry);


        int rpm = 3000;
        boolean lastUp = false;
        boolean lastDown = false;

        waitForStart();

        while (opModeIsActive()) {

            // 1. Driving
            drive.setMecanum(
                    -gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    1.0
            );

            // 2. RPM adjustment with edge detection
            boolean up = gamepad2.dpad_up;
            boolean down = gamepad2.dpad_down;

            if (up && !lastUp) {        // pressed this loop
                rpm += 500;
            }
            if (down && !lastDown) {
                rpm -= 500;
            }

            // store previous state
            lastUp = up;
            lastDown = down;

            // clamp
            if (rpm < 0) rpm = 0;
            if (rpm > 6000) rpm = 6000;

            // 3. Apply RPM + maintain speed
            launcher.setRPM(rpm);
            launcher.update();   // << REQUIRED

            telemetry.addData("RPM Target", rpm);
            telemetry.addData("Ready", launcher.readyToFire());

            //4. Intake systems
            if (gamepad2.left_trigger > 0.1)
            {
                loader.LoadBot(1);
            }

            //5. Load Systems
            if (gamepad2.right_trigger > 0.1)
            {
                loader.LoadTurret(1);
            }
            if (gamepad2.right_trigger < 0.1 && gamepad2.left_trigger < 0.1 && !gamepad2.x)
            {
              loader.stopLoader();
              loader.stopIntake();
            }

            //6. Flush system.
            if (gamepad2.x)
            {
                loader.LoadBot(-1);
                loader.LoadTurret(-1);
            }
            // 7. Turret control
            if (Math.abs(gamepad2.left_stick_x) > 0.1)
            {
               turret.rotateTurret(gamepad2.left_stick_x);
            }
            else
            {
                turret.stop();
            }
            telemetry.update();

        }
    }
}
