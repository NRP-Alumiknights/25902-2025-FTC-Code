package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Robot {

    public DcMotor lf, rf, lb, rb;
    public IMU imu;

    private HardwareMap hw;

    public Robot(HardwareMap hardwareMap) {
        this.hw = hardwareMap;
    }

    public void init() {
        lf = hw.get(DcMotor.class, "lf");
        rf = hw.get(DcMotor.class, "rf");
        lb = hw.get(DcMotor.class, "lb");
        rb = hw.get(DcMotor.class, "rb");

        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);

        imu = hw.get(IMU.class, "imu");
        // PUT IN IMU PARAMS imu.initialize(new IMU.Parameters());
    }
}
