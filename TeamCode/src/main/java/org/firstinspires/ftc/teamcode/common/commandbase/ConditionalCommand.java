package org.firstinspires.ftc.teamcode.common.commandbase;

/**
 * Usage:
 * If using a boolean returning function:
 * new ConditionalCommand(() -> {return class.method();});
 */
public class ConditionalCommand implements Command
{

    private Function function;

    public ConditionalCommand (Function function) {
        this.function = function;
    }

    @Override
    public boolean run() {
        return function.run();
    }
}