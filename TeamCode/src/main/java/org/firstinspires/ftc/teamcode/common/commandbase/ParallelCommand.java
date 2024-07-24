package org.firstinspires.ftc.teamcode.common.commandbase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelCommand implements Command
{
	private List<Command> commands;

	public ParallelCommand (List<Command> commands)
	{
		this.commands = new ArrayList<>(commands);
	}

	public ParallelCommand (Command... commands)
	{
		this(Arrays.asList(commands));
	}

	@Override
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

	@Override
	public boolean isFinished()
	{
		return commands.isEmpty();
	}
}
