package org.firstinspires.ftc.teamcode.common.commandbase;

public class InstantCommand implements Command
{
	private Function function;

	public InstantCommand (Function function)
	{
		this.function = function;
	}

	@Override
	public void run()
	{
		function.run();
	}

	@Override
	public boolean isFinished()
	{
		return true;
	}
}
