package org.firstinspires.ftc.teamcode.common.commandbase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialCommand implements Command
{
	private List<Command> commands;

	public SequentialCommand(List<Command> commands)
	{
		this.commands = new ArrayList<>(commands);
	}

	public SequentialCommand(Command... commands)
	{
		this(Arrays.asList(commands));
	}

	@Override
	public void run()
	{
		if (commands.isEmpty())
			return;

		commands.get(0).run();
		if (commands.get(0).isFinished()) {
			commands.remove(0);
			run();
		}
	}

	@Override
	public boolean isFinished()
	{
		return commands.isEmpty();
	}
}
