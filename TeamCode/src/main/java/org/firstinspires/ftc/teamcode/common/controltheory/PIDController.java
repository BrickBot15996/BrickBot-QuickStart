package org.firstinspires.ftc.teamcode.common.controltheory;

import com.qualcomm.robotcore.util.Range;

public class PIDController
{
	private double kP = 0.0;
	private double kI = 0.0;
	private double kD = 0.0;
	private double lastError = 0.0;
	private double lastTimeStamp = 0.0;
	private double totalError = 0.0;

	public PIDController(double kP, double kI, double kD)
	{
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}

	public double calculate(double currPos, double targetPos)
	{
		double error = targetPos - currPos;
		double currTimeStamp = System.nanoTime() * 1E-9;
		if (lastTimeStamp == 0.0) lastTimeStamp = currTimeStamp;

		totalError += (currTimeStamp - lastTimeStamp) * error;
		totalError = Range.clip(totalError, -1.0, 1.0);
		lastTimeStamp = currTimeStamp;

		double proportional = error * kP;
		double integral = totalError * kI;
		double derivative = (error - lastError) * kD;

		return proportional + integral + derivative;
	}

	public double calculate (int currPos, int targetPos)
	{
		double error = targetPos - currPos;
		double currTimeStamp = System.nanoTime() * 1E-9;
		if (lastTimeStamp == 0.0) lastTimeStamp = currTimeStamp;

		totalError += (currTimeStamp - lastTimeStamp) * error;
		totalError = Range.clip(totalError, -500.0, 500.0);
		lastTimeStamp = currTimeStamp;

		double proportional = error * kP;
		double integral = totalError * kI;
		double derivative = (error - lastError) * kD;

		return proportional + integral + derivative;
	}
}
