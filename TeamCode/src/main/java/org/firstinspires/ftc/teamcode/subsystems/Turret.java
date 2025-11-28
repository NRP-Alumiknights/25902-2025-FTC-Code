package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

/**
 * Generic subsystem template that only uses hardware inside Robot.java.
 * Copy → rename → customize.
 */
public class Turret {

    protected final Robot robot;
    protected final Telemetry telemetry;

    public Turret(Robot robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;

        // You may configure robot hardware for your subsystem here:
        // robot.launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // robot.intakeL.setDirection(CRServo.Direction.FORWARD);
    }

    // =======================================================
    //                PUBLIC API ACTION METHODS
    // =======================================================

    /** Example—replace with real behavior */
    public void rotateTurret(double power) {
        // Example: using a motor
        // robot.launcher.setPower(power);

        // Or a CRServo
         robot.turret.setPower(power);
        // robot.intakeR.setPower(power);
    }

    /** Stop everything related to this subsystem */
    public void stop() {
        // robot.launcher.setPower(0);
         robot.turret.setPower(0);
        // robot.intakeR.setPower(0);
    }

    // =======================================================
    //                HELPER FUNCTIONS
    // =======================================================

    /** Example mode switcher */
    public void runWithoutEncoder() {
        // robot.launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /** Optional telemetry */
    public void log() {
        // telemetry.addData("LauncherRPM", currentRPM);
        // telemetry.addData("IntakePower", robot.intakeL.getPower());
    }
}
