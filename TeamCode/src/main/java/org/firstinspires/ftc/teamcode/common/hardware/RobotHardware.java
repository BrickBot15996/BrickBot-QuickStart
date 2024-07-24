package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

public class RobotHardware
{
	RobotHardware robotHardware;

	double voltage = 12.0;

	public HashMap<Sensors.UsedSensors, Object> encoderValues;

	public RobotHardware() {}

	// Declare Devices and ActuatorGroups
	LynxModule controlHub;
	LynxModule expansionHub;


	// Declare Subsystems


	//Create the init method

	public void init(HardwareMap hwMap) {
		controlHub = hwMap.get(LynxModule.class, "Control Hub");
		expansionHub = hwMap.get(LynxModule.class, "Expansion Hub");

		controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
		expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
	}

	public int intSupplier(Sensors.UsedSensors sensor)
	{
		Object position = encoderValues.getOrDefault(sensor, 0);
		if (position instanceof Integer) {
			return (Integer)position;
		} else if (position instanceof Double) {
			return ((Double)position).intValue();
		} else {
			throw new ClassCastException();
		}
	}

	public double doubleSupplier(Sensors.UsedSensors sensor)
	{
		Object position = encoderValues.getOrDefault(sensor, 0.0);
		if (position instanceof Integer) {
			return ((Integer)position).doubleValue();
		} else if (position instanceof Double) {
			return (Double)position;
		} else {
			throw new ClassCastException();
		}
	}

	public void clearCache()
	{
		controlHub.clearBulkCache();
		expansionHub.clearBulkCache();
	}

	public RobotHardware getInstance()
	{
		if (robotHardware == null)
			robotHardware = new RobotHardware();
		return robotHardware;
	}
}
