package org.firstinspires.ftc.teamcode.common.wrappers;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BetterEncoder implements HardwareDevice
{
	private DcMotor encoder;

	public BetterEncoder(@NonNull HardwareMap hwMap, String encoderName)
	{
		encoder = hwMap.get(DcMotor.class, encoderName);
	}

	public int getCurrentPosition()
	{
		return encoder.getCurrentPosition();
	}

	public void reset()
	{
		encoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		encoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	}

	@Override
	public Manufacturer getManufacturer() {	return null; }

	@Override
	public String getDeviceName() { return "BrickBot"; }

	@Override
	public String getConnectionInfo() {	return "Brickbot"; }

	@Override
	public int getVersion() { return 15996;	}

	@Override
	public void resetDeviceConfigurationForOpMode() {}

	@Override
	public void close() {}
}
