package org.firstinspires.ftc.teamcode.common.controltheory;

import com.qualcomm.robotcore.util.Range;

public class PDFSController
{
	private double kP;
	private double kD;
	private double kF;
	private double kStatic;
	private double lastError = 0.0;

	public PDFSController(double kP, double kD, double kF, double kStatic) {
		this.kP = kP;
		this.kD = kD;
		this.kF = kF;
		this.kStatic = kStatic;
	}

	public double calculate(double currentPosition, double targetPosition) {
		double error = targetPosition - currentPosition;
		lastError = lastError == 0.0 ? error : lastError;
		double proportional = kP * error;
		double derivative = kD * (error - lastError);
		double feedforward = kF;
		double staticF = kStatic;
		return proportional + derivative + feedforward + staticF;
	}

	public double calculate(int currentPosition, int targetPosition) {
		int error = targetPosition - currentPosition;
		lastError = lastError == 0.0 ? error : lastError;
		double proportional = kP * error;
		double derivative = kD * (error - lastError);
		double feedforward = kF;
		double staticF = kStatic;
		return proportional + derivative + feedforward + staticF;
	}
}
