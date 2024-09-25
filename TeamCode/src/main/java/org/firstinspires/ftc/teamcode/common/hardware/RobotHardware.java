package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
import org.firstinspires.ftc.robotcore.internal.ftdi.BaudRate;
import org.firstinspires.ftc.teamcode.common.commandbase.CommandScheduler;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.BetterMotor;
import org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad.BetterGamepad;
import org.firstinspires.ftc.teamcode.common.hardware.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.common.hardware.subsystems.Subsystem;

import java.util.ArrayList;

public class RobotHardware
{
	private static RobotHardware robotHardware;

	public double voltage = 12.0;

	private RobotHardware() {}

	// Declare Devices
	LynxModule controlHub;
	//LynxModule expansionHub;

	public BetterGamepad gamepad1;
	public BetterGamepad gamepad2;

	public BetterMotor frontLeft = new BetterMotor("frontLeft");
	public BetterMotor rearLeft = new BetterMotor("rearLeft");
	public BetterMotor rearRight = new BetterMotor("rearRight");
	public BetterMotor frontRight = new BetterMotor("frontRight");

	public IMU imu;

	// Declare Subsystems
	ArrayList<Subsystem> subsystems = new ArrayList<>();

	MecanumDrive drivetrain = new MecanumDrive()
								.setHeadingPDFS(0.0, 0.0, 0.0, 0.0)
								.setDrivingType(MecanumDrive.DrivingType.FIELD_CENTRIC);


	public RobotHardware init(HardwareMap hwMap)
	{
		subsystems.add(drivetrain);

		CommandScheduler.getInstance().addSubsystems(subsystems);

		controlHub = hwMap.get(LynxModule.class, "Control Hub");
		//expansionHub = hwMap.get(LynxModule.class, "Expansion Hub");

		controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
		//expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

		imu = hwMap.get(IMU.class, "imu");

		IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
				RevHubOrientationOnRobot.LogoFacingDirection.UP,
				RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

		imu.initialize(parameters);

		frontLeft.init(hwMap);
		rearLeft.init(hwMap);
		rearRight.init(hwMap);
		frontRight.init(hwMap);

		frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

		frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		return this;
	}

	public RobotHardware setGamepads(BetterGamepad gamepad1, BetterGamepad gamepad2)
	{
		this.gamepad1 = gamepad1;
		this.gamepad2 = gamepad2;
		return this;
	}

	public void read()
	{
		for (Subsystem subsystem : subsystems)
			subsystem.read();
		voltage = controlHub.getInputVoltage(VoltageUnit.VOLTS);
	}

	/*public void periodic()
	{
		for (Subsystem subsystem : subsystems)
			subsystem.periodic();
	}*/

	public void write()
	{
		for (Subsystem subsystem : subsystems)
			subsystem.write();
	}

	public void clearCache()
	{
		controlHub.clearBulkCache();
		//expansionHub.clearBulkCache();
	}

	public static RobotHardware getInstance()
	{
		if (robotHardware == null)
			robotHardware = new RobotHardware();
		return robotHardware;
	}
}
