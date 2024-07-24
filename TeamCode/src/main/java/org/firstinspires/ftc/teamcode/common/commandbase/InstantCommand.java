package org.firstinspires.ftc.teamcode.common.commandbase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstantCommand implements Command
{
	private List<Command> commands;

	public InstantCommand (List<Command> commands)
	{
		this.commands = new ArrayList<>(commands);
	}

	public InstantCommand (Command... commands)
	{
		this(Arrays.asList(commands));
	}

	@Override
	public void run() {
		if (commands.isEmpty())
			return;

		for (Command command : commands) {
			command.run();
			commands.remove(command);
		}
	}

	@Override
	public boolean isFinished() {
		return commands.isEmpty();
	}
}
