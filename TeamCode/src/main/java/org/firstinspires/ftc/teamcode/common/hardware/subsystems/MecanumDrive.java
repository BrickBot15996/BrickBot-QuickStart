package org.firstinspires.ftc.teamcode.common.hardware.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.teamcode.common.controltheory.PIDController;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.BetterMotor;

public class MecanumDrive implements Subsystem
{
    private RobotHardware robot = RobotHardware.getInstance();

    private BetterMotor frontLeft;
    private BetterMotor rearLeft;
    private BetterMotor rearRight;
    private BetterMotor frontRight;

    private double kP;
    private double kI;
    private double kD;
    private PIDController headingPID = new PIDController(kP, kI, kD);

    private double targetHeading;

    public enum DrivingType
    {
        ROBOT_CENTRIC,
        FIELD_CENTRIC
    }

    private DrivingType drivingType = DrivingType.ROBOT_CENTRIC;

    public MecanumDrive (BetterMotor frontLeft, BetterMotor rearLeft, BetterMotor rearRight, BetterMotor frontRight)
    {
        this.frontLeft = frontLeft;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.frontRight = frontRight;
    }

    public MecanumDrive setHeadingPID(double kP, double kI, double kD)
    {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        return this;
    }

    public MecanumDrive setDrivingType(DrivingType type)
    {
        drivingType = type;
        return this;
    }

    @Override
    public void read() {}

    @Override
    public void periodic() {

    }

    @Override
    public void write() {

    }

}
