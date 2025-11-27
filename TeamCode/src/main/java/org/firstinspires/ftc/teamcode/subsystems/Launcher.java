package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

/**
 * Flywheel launcher subsystem using encoder → RPM closed-loop.
 * ONLY uses hardware in Robot.java (robot.launcher).
 */
public class Launcher {

    private final Robot robot;
    private final Telemetry telemetry;
    private final DcMotorEx flywheel;

    // PIDF
    private double kP = 0.0008;
    private double kI = 0.0000;
    private double kD = 0.0002;
    private double kF = 0.0;

    private double targetRPM = 0;
    private double rpmTolerance = 50;

    private final ElapsedTime timer = new ElapsedTime();
    private int lastPosition = 0;

    public Launcher(Robot robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;

        this.flywheel = (DcMotorEx) robot.launcher;

        flywheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        flywheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        timer.reset();
    }

    // =============================
    //         PUBLIC API
    // =============================

    public void setRPM(double rpm) {
        targetRPM = rpm;
    }

    public void update() {
        double currentRPM = getRPM();
        double error = targetRPM - currentRPM;

        double dt = timer.seconds();
        timer.reset();

        double derivative = (error) / dt;

        double output = (kP * error) + (kD * derivative) + (kF * targetRPM);

        // clamp
        output = Math.max(0, Math.min(output, 1));
        flywheel.setPower(output);

        log(currentRPM, output);
    }

    public void stop() {
        targetRPM = 0;
        flywheel.setPower(0);
    }

    public boolean readyToFire() {
        return Math.abs(getRPM() - targetRPM) <= rpmTolerance;
    }

    // =============================
    //          RPM CALC
    // =============================

    private double getRPM() {
        int pos = flywheel.getCurrentPosition();
        double dt = timer.seconds();

        int delta = pos - lastPosition;
        lastPosition = pos;

        double ticksPerRev = 28.0;

        double revs = delta / ticksPerRev;
        double rps = revs / dt;

        return rps * 60.0;
    }

    // =============================
    //        TELEMETRY
    // =============================

    private void log(double currentRPM, double out) {
        telemetry.addData("Launcher Target", targetRPM);
        telemetry.addData("Launcher RPM", currentRPM);
        telemetry.addData("Launcher PowerOut", out);
        telemetry.addData("Ready", readyToFire());
    }
}
