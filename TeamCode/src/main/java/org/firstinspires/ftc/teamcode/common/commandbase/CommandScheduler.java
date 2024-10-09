package org.firstinspires.ftc.teamcode.common.commandbase;

import org.firstinspires.ftc.teamcode.common.hardware.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandScheduler
{
	private static CommandScheduler scheduler;
	private static ArrayList<Subsystem> subsystems;
	private ArrayList<Command> commands;

	private CommandScheduler()
	{
		this.commands = new ArrayList<>();
	}

	public void addSubsystems(List<Subsystem> subsystems)
	{
		CommandScheduler.subsystems = new ArrayList<>(subsystems);
	}

	public void addSubsystems(Subsystem... subsystem)
	{
		addSubsystems(Arrays.asList(subsystem));
	}

	public static CommandScheduler getInstance()
	{
		if (scheduler == null)
			scheduler = new CommandScheduler();
		return scheduler;
	}

	public void reset()
	{
		if (commands != null)
			commands.clear();
	}

	public void schedule(Command command)
	{
		commands.add(command);
		if (command instanceof TimedCommand)
			((TimedCommand) command).startTimer();
	}

	public void remove(Command command)
	{
		commands.remove(command);
	}

	public void run()
	{
		if (subsystems != null && !subsystems.isEmpty())
			for(Subsystem subsystem : subsystems)
				subsystem.periodic();

		if (commands.isEmpty())
			return;

		for (Command command : commands) {
			if (command.run())
				commands.remove(command);
			if (commands.isEmpty())
				break;
		}
	}
}
