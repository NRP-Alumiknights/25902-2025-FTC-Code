package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Robot {
    public DcMotor rightFront, leftFront, rightBack, leftBack, launcher, launcher2, intake, Rack;
    public CRServo intakeL, intakeR, loader, loader2, loader3, turret;
    public IMU imu;

    public HardwareMap hw;

    /** FIXED: Robot constructor now stores the hardware map AND initializes. */
    public Robot(HardwareMap hardwareMap) {
        init(hardwareMap);
    }

    /** Proper init for all motors */
    public void init(HardwareMap hardwareMap) {
        this.hw = hardwareMap;

        // Motors (MAKE SURE names match your config!)
        rightFront = initMotor("RightFront", false);
        leftFront  = initMotor("LeftFront", true);
        rightBack  = initMotor("RightBack", false);
        leftBack   = initMotor("LeftBack", true);

        launcher = initMotor("Launcher", true);
        intake = initMotor("Intake", false);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // CRServos
        intakeL = hw.crservo.get("IntakeL");
        intakeR = hw.crservo.get("IntakeR");
        loader  = hw.crservo.get("Loader");
        loader2 = hw.crservo.get("LoaderWheel");
        loader3 = hw.crservo.get(("Loader3"));
        turret = hw.crservo.get(("Turret"));

        // IMU
        imu = hw.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        ));

        // Brake mode default
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private DcMotor initMotor(String name, boolean forward) {
        DcMotor m = hw.get(DcMotor.class, name);

        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return m;
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        if (leftFront != null) leftFront.setZeroPowerBehavior(behavior);
        if (rightFront!= null) rightFront.setZeroPowerBehavior(behavior);
        if (leftBack  != null) leftBack.setZeroPowerBehavior(behavior);
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
