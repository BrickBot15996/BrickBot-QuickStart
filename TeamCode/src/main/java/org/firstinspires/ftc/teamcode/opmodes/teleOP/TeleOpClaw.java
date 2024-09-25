package org.firstinspires.ftc.teamcode.opmodes.teleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.common.commandbase.CommandScheduler;
import org.firstinspires.ftc.teamcode.common.commandbase.InstantCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.SequentialCommand;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.BetterServo;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.BetterGamepad;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.DigitalBindings;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "ClawTest")
public class TeleOpClaw extends LinearOpMode
{
    CommandScheduler scheduler = CommandScheduler.getInstance();
    Gamepad lastGamepad = new Gamepad();
    BetterServo claw = new BetterServo("gheara");

    private static final double closed = 0.03;
    private static final double open = 0.25;

    private long systemTime = 0;

    boolean isOpen = false;

    @Override
    public void runOpMode()
    {
        lastGamepad.copy(gamepad1);
        claw.init(hardwareMap);
        claw.setPosition(closed);

        waitForStart();

        systemTime = System.nanoTime();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.a && !lastGamepad.a)
                /*scheduler.schedule(new SequentialCommand(new InstantCommand(this::toggleClaw),
                                                        new InstantCommand(this::toggleClaw),
                                                        new InstantCommand(this::toggleClaw)));*/
                scheduler.schedule(new InstantCommand(this::toggleClaw));
            lastGamepad.copy(gamepad1);
            scheduler.run();

            telemetry.addData("loopTime:", (System.nanoTime() - systemTime) / 1000000);
            telemetry.update();

            systemTime = System.nanoTime();
        }
    }

    private boolean toggleClaw()
    {
        claw.setPosition(isOpen ? closed : open);
        isOpen = !isOpen;
        sleep(200);
        return true;
    }
}
