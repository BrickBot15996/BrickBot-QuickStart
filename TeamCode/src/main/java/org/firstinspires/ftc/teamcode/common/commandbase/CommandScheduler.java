package org.firstinspires.ftc.teamcode.common.commandbase;

import java.util.ArrayList;
import java.util.List;

public class CommandScheduler
{
	private static CommandScheduler scheduler;
	private List<Command> commands;

	private CommandScheduler()
	{
		this.commands = new ArrayList<>();
	}

	public static CommandScheduler getInstance()
	{
		if (scheduler == null) {
			scheduler = new CommandScheduler();
		}
		return scheduler;
	}

	//TODO ADD SUBSYSTEM PERIODIC

	public void reset()
	{
		if (commands != null)
			commands.clear();
	}

	public void schedule(Command command)
	{
		commands.add(command);
	}

	public void remove(Command command)
	{
		commands.remove(command);
	}

	public void run()
	{
		if (commands.isEmpty())
			return;

		for (Command command : commands) {
			command.run();
			if (command.isFinished())
				commands.remove(command);
		}
	}
}
