package org.firstinspires.ftc.teamcode.common.commandbase;

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