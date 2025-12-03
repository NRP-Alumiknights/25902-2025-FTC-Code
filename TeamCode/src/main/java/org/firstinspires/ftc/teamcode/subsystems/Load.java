package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

/**
 * Generic subsystem template that only uses hardware inside Robot.java.
 * Copy → rename → customize.
 */
public class Load {

    protected final Robot robot;
    protected final Telemetry telemetry;

    public Load(Robot robot, Telemetry telemetry) {
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
    public void LoadBot(double power) {
        // Example: using a motor
        // robot.launcher.setPower(power);

        // Or a CRServo
        robot.intakeL.setPower(-power/2.0);
        robot.intakeR.setPower(power);
    }
    public void LoadTurret(double power) {
        robot.loader.setPower(power);
        robot.loader2.setPower(power);
        robot.loader3.setPower(power);
    }

    /** Stop everything related to this subsystem */
    public void stopIntake() {
        // robot.launcher.setPower(0);
        robot.intakeL.setPower(0);
        robot.intakeR.setPower(0);
    }
    public void stopLoader(){
        robot.loader.setPower(0);
        robot.loader2.setPower(0);
        robot.loader3.setPower(0);
    }


    }

