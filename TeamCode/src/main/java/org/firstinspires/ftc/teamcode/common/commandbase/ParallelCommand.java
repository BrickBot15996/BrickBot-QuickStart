package org.firstinspires.ftc.teamcode.common.commandbase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelCommand implements Command
{
	private ArrayList<Command> commands;

	public ParallelCommand (List<Command> commands)
	{
		this.commands = new ArrayList<>(commands);
	}

	public ParallelCommand (Command... commands)
	{
		this(Arrays.asList(commands));
	}

	@Override
	public boolean run()
	{
		if (commands.isEmpty())
			return true;

		for (Command command : commands)
			if (command.run())
				commands.remove(command);

		return false;
	}
}
