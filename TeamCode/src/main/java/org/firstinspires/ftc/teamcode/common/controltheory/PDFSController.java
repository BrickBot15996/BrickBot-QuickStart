package org.firstinspires.ftc.teamcode.common.controltheory;

public class PDFSController
{
	public enum FeedForwardType {
		CONSTANT,
		SIN,
		COS
	}

	private final double kP;
	private final double kD;
	private final double kF;
	private final double kStatic;
	private double lastError;
	private double errorThreshold;
	private FeedForwardType feedForwardType;

	public PDFSController(double kP, double kD, double kF, double kStatic) {
		this.kP = kP;
		this.kD = kD;
		this.kF = kF;
		this.kStatic = kStatic;
		lastError = 0.0;
		errorThreshold = 0.0;
		feedForwardType = FeedForwardType.CONSTANT;
	}

	public PDFSController setErrorThreshold(double errorThreshold) {
		this.errorThreshold = errorThreshold;
		return this;
	}

	public PDFSController setFeedForwardType(FeedForwardType feedforwardType) {
		this.feedForwardType = feedforwardType;
		return this;
	}

	public double calculate(double currentPosition, double targetPosition) {
		double error = targetPosition - currentPosition;
		if (error < errorThreshold)
			return kF;
		lastError = lastError == 0.0 ? error : lastError;
		double proportional = kP * error;
		double derivative = kD * (error - lastError);
		double fStatic = Math.signum(error) * kStatic;
		double value = proportional + derivative;
		value *= (1 - kStatic);
		return value + fStatic;
	}

	public double calculate(double currentPosition, double targetPosition, double currentAngle) {
		double error = targetPosition - currentPosition;
		if (error < errorThreshold)
			return updatedKF(currentAngle);
		lastError = lastError == 0.0 ? error : lastError;
		double proportional = kP * error;
		double derivative = kD * (error - lastError);
		double fStatic = Math.signum(error) * kStatic;
		double value = proportional + derivative;
		value *= (1 - kStatic);
		return value + fStatic;
	}

	public double calculate(int currentPosition, int targetPosition) {
		double error = targetPosition - currentPosition;
		if (error < errorThreshold)
			return kF;
		lastError = lastError == 0.0 ? error : lastError;
		double proportional = kP * error;
		double derivative = kD * (error - lastError);
		double fStatic = Math.signum(error) * kStatic;
		double value = proportional + derivative;
		value *= (1 - kStatic);
		return value + fStatic;
	}

	public double calculate(int currentPosition, int targetPosition, double currentAngle) {
		double error = targetPosition - currentPosition;
		if (error < errorThreshold)
			return updatedKF(currentAngle);
		lastError = lastError == 0.0 ? error : lastError;
		double proportional = kP * error;
		double derivative = kD * (error - lastError);
		double fStatic = Math.signum(error) * kStatic;
		double value = proportional + derivative;
		value *= (1 - kStatic);
		return value + fStatic;
	}

	private double updatedKF(double currentAngle) {
		switch (feedForwardType) {
			case CONSTANT:
				return kF;
			case SIN:
				return kF * Math.sin(currentAngle);
			case COS:
				return kF * Math.cos(currentAngle);
			default:
				return 0.0;
		}
	}
}
