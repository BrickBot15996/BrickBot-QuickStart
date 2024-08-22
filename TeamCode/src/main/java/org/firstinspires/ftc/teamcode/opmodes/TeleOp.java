package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.commandbase.CommandScheduler;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.BetterGamepad;

public class TeleOp extends LinearOpMode
{
    RobotHardware robot = RobotHardware.getInstance();
    CommandScheduler scheduler = CommandScheduler.getInstance();

    BetterGamepad gamepad = new BetterGamepad(gamepad1);

    @Override
    public void runOpMode()
    {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

        }
    }
}
