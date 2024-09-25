package org.firstinspires.ftc.teamcode.common.commandbase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialCommand implements Command
{
	private ArrayList<Command> commands;

	public SequentialCommand(List<Command> commands)
	{
		this.commands = new ArrayList<>(commands);
	}

	public SequentialCommand(Command... commands)
	{
		this(Arrays.asList(commands));
	}

	@Override
	public boolean run()
	{
		if (commands.isEmpty())
			return true;

		if (commands.get(0).run())
			commands.remove(0);

		return false;
	}
}
