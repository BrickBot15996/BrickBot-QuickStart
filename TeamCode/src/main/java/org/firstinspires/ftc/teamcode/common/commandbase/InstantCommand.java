package org.firstinspires.ftc.teamcode.common.commandbase;

/**
 * Usage:
 * new InstantCommand(() -> {class.method();});
 */
public class InstantCommand implements Command
{
	private Function function;

	public InstantCommand (Function function)
	{
		this.function = function;
	}

	@Override
	public boolean run()
	{
		function.run();
		return true;
	}
}
