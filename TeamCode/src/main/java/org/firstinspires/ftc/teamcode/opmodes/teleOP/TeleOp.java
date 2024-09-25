package org.firstinspires.ftc.teamcode.opmodes.teleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.commandbase.CommandScheduler;
import org.firstinspires.ftc.teamcode.common.commandbase.InstantCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.ParallelCommand;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.AnalogBindings;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.BetterGamepad;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.DigitalBindings;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Mecanum Test")
public class TeleOp extends LinearOpMode
{
    CommandScheduler scheduler = CommandScheduler.getInstance();

    BetterGamepad gamepad_1 = new BetterGamepad(gamepad1);
    BetterGamepad gamepad_2 = new BetterGamepad(gamepad2);

    @Override
    public void runOpMode()
    {
        RobotHardware.getInstance().init(hardwareMap);
        RobotHardware.getInstance().setGamepads(gamepad_1, gamepad_2);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            //RobotHardware.getInstance().read();

            scheduler.run();
            //RobotHardware.getInstance().write();
            //RobotHardware.getInstance().clearCache();
        }
    }
}
