package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Robot {
    public DcMotor rightFront, leftFront, rightBack, leftBack, launcher;
    public CRServo intakeL, intakeR, loader, loaderWheel;
    public IMU imu;

    public HardwareMap hw;

    public Robot(HardwareMap hardwareMap) {}

    public void init(HardwareMap hardwareMap) {
        this.hw = hardwareMap;

        // Motors — names must match your Hub configuration
        rightFront = initMotor("RightFront", true);
        leftFront  = initMotor("LeftFront", false);
        rightBack  = initMotor("RightBack", true);
        leftBack   = initMotor("LeftBack", false);

        // launcher motor (if used as flywheel)
        launcher = initMotor("Launcher", true);

        // CRServos
        intakeL = hw.crservo.get("IntakeL");
        intakeR = hw.crservo.get("IntakeR");
        loader  = hw.crservo.get("Loader");
        loaderWheel = hw.crservo.get("LoaderWheel");

        // IMU (name "imu")
        imu = hw.get(IMU.class, "imu");
        try {
            imu.initialize(new IMU.Parameters( new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP
            )));
        } catch (Exception e) {
            // ignore if IMU not present or initialization fails during unit tests
        }

        // Default to brake for safety
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private DcMotor initMotor(String name, boolean forwardDirection) {
        DcMotor m = hw.get(DcMotor.class, name);
        if (!forwardDirection) m.setDirection(DcMotorSimple.Direction.REVERSE);
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return m;
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        if (leftFront != null) leftFront.setZeroPowerBehavior(behavior);
        if (leftBack  != null) leftBack.setZeroPowerBehavior(behavior);
        if (rightFront!= null) rightFront.setZeroPowerBehavior(behavior);
        if (rightBack != null) rightBack.setZeroPowerBehavior(behavior);
        if (launcher != null) launcher.setZeroPowerBehavior(behavior);
    }

    public void resetEncoders() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
