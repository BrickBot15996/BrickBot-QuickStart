package org.firstinspires.ftc.teamcode.common.hardware.devicewrappers;

import com.qualcomm.robotcore.hardware.HardwareDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ActuatorGroup
{
	private List<HardwareDevice> devices;
	private Supplier<Object> encoder;

	public ActuatorGroup (Supplier<Object> encoder, HardwareDevice... devices)
	{
		this.encoder = encoder;
		this.devices = new ArrayList<>(Arrays.asList(devices));
	}
}
