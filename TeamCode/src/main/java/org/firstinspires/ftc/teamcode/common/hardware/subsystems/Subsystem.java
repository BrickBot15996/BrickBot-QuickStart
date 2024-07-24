package org.firstinspires.ftc.teamcode.common.hardware.subsystems;

public interface Subsystem
{
	void read(); // Reads sensor data
	void periodic(); // Determines behavior based on read data
	void write(); // Writes to devices to fulfill determined behavior
}
