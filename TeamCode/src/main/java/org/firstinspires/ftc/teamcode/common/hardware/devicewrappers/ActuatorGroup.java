package org.firstinspires.ftc.teamcode.common.hardware.devicewrappers;

import com.qualcomm.robotcore.hardware.HardwareDevice;

public class ActuatorGroup
{
	private enum Actuators
	{
		NONE,
		MOTOR,
		SERVO,
		CRSERVO
	}

	Actuators actuators = Actuators.NONE;



	public ActuatorGroup (HardwareDevice... devices)
	{
		for (HardwareDevice device : devices) {
			if (device instanceof BetterEncoder || device instanceof BetterAbsoluteEncoder) {

			}
		}
	}
}
