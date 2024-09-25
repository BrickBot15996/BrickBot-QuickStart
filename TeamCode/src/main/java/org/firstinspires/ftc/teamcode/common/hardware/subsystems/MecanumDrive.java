package org.firstinspires.ftc.teamcode.common.hardware.subsystems;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.controltheory.PDFSController;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.AnalogBindings;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.DigitalBindings;

public class MecanumDrive implements Subsystem
{
    private RobotHardware robot = RobotHardware.getInstance();

    private PDFSController headingPDFS;

    private double x;
    private double y;
    private double lastTurn;
    private double turn;
    private double brake = 1.0;

    private double headingOffset = 0.0;
    private double currHeading;
    private double targetHeading;

    private boolean headingController = true;

    public enum DrivingType
    {
        ROBOT_CENTRIC,
        FIELD_CENTRIC
    }

    private DrivingType drivingType = DrivingType.ROBOT_CENTRIC;

    public MecanumDrive () {}

    public MecanumDrive setHeadingPDFS(double kP, double kD, double kF, double kStatic)
    {
        headingPDFS = new PDFSController(kP, kD, kF, kStatic);
        return this;
    }

    public MecanumDrive setDrivingType(DrivingType type)
    {
        drivingType = type;
        return this;
    }

    public MecanumDrive setHeadingOffset(double headingOffset)
    {
        this.headingOffset = headingOffset;
        return this;
    }

    @Override
    public void read()
    {
        // Read heading and fit into [0, 360]
        currHeading = robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) + headingOffset;
        currHeading = currHeading > 360 ? currHeading - 360 : (currHeading < 0 ? currHeading + 360 : currHeading);

        // Read joysticks
        x = robot.gamepad1.get(AnalogBindings.left_stick_x).get();
        y = robot.gamepad1.get(AnalogBindings.left_stick_y).get();

        lastTurn = turn;

        // Handle heading controller
        if (!headingController) {
            turn = robot.gamepad1.get(AnalogBindings.right_trigger).get() - robot.gamepad1.get(AnalogBindings.left_trigger).get();
            if (turn == 0.0 && lastTurn != 0.0) {
                targetHeading = currHeading;
                headingController = true;
            }
        } else {
            double tempTurn = robot.gamepad1.get(AnalogBindings.right_trigger).get() - robot.gamepad1.get(AnalogBindings.left_trigger).get();
            if (tempTurn != 0.0) {
                headingController = false;
                turn = tempTurn;
            }
        }

        // Handle brake
        if (robot.gamepad1.get(DigitalBindings.left_bumper).isPressed())
            brake = 0.5;
        else
            brake = 1.0;
    }

    @Override
    public void periodic()
    {
        if (drivingType == DrivingType.FIELD_CENTRIC) {
            double xCopy = x;
            double yCopy = y;
            x = xCopy * Math.cos(-currHeading) - yCopy * Math.sin(-currHeading);
            y = xCopy * Math.sin(-currHeading) + yCopy * Math.cos(-currHeading);
        }

        if (headingController) {
            turn = Range.clip(headingPDFS.calculate(currHeading, targetHeading), 0.0, 1.0);
        }
    }

    @Override
    public void write()
    {
        /*double voltageCorrection = 12.0 / robot.voltage;
        double denominator = Math.max((Math.abs(y) + Math.abs(x) + Math.abs(turn)) * voltageCorrection, 1.0);
        double frontLeftPower = (y + x + turn) * voltageCorrection / denominator;
        double rearLeftPower = (y - x + turn) * voltageCorrection / denominator;
        double rearRightPower = (y - x - turn) * voltageCorrection / denominator;
        double frontRightPower = (y + x - turn) * voltageCorrection / denominator;

        robot.frontLeft.setPower(frontLeftPower * brake);
        robot.rearLeft.setPower(rearLeftPower * brake);
        robot.rearRight.setPower(rearRightPower * brake);
        robot.frontRight.setPower(frontRightPower * brake);*/
    }

}
