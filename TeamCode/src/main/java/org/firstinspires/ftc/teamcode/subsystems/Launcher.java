package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;

/**
 * Generic subsystem template.
 * Copy → rename → modify for any mechanism.
 */
public class Launcher {

    private final Robot robot;
    private final Telemetry telemetry;

    // ===== Your hardware goes here =====
    // Example:
    // private DcMotor motor;
    // private Servo servo;
    // private CRServo crServo;
    // private IMU imu;

    public Launcher(Robot robot, Telemetry telemetry, HardwareMap hardwareMap) {
        this.robot = robot;
        this.telemetry = telemetry;

        // ===== Initialize hardware here =====
        // motor = hardwareMap.get(DcMotor.class, "motorName");
        // servo = hardwareMap.get(Servo.class, "servoName");
        // imu   = hardwareMap.get(IMU.class, "imu");

        // ===== Configure hardware =====
        // motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // ====================================================
    // =============== HIGH-LEVEL API =======================
    // ====================================================

    /** Example of a high-level action */
    public void runExample(double power) {
        // motor.setPower(power);
    }

    /** Example: Stop all motion for this subsystem */
    public void stop() {
        // if (motor != null) motor.setPower(0);
        // if (servo != null) servo.setPosition(servo.getPosition());
    }

    }

