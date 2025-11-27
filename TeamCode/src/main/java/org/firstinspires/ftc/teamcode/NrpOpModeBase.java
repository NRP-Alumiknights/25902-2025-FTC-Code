package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Drive;

public abstract class NrpOpModeBase extends LinearOpMode {
    protected Robot robot;
    protected Drive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        // subclasses should call initControls() from their init() if they override runOpMode,
        // but keeping the base for convenience for legacy code
    }

    /** call from your OpMode.init() */
    public void initControls() {
        robot = new Robot(hardwareMap);
        robot.init(hardwareMap);
        drive = new Drive(robot, telemetry);
    }
}
