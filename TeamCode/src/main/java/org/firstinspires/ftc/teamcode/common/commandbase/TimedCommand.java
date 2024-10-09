package org.firstinspires.ftc.teamcode.common.commandbase;

import static org.json.JSONObject.NULL;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Usage:
 * Allows both boolean and void functions:
 * new ConditionalCommand(() -> {return class.method();}, timeoutSeconds);
 */
public class TimedCommand implements Command
{
    ElapsedTime timer = new ElapsedTime();
    double timeout;
    private BooleanFunction booleanFunction;
    private Function function;

    public TimedCommand (BooleanFunction booleanFunction, double timeout)
    {
        this.booleanFunction = booleanFunction;
        this.timeout = timeout;
    }

    public TimedCommand(Function function, double timeout)
    {
        this.function = function;
        this.timeout = timeout;
    }

    @Override
    public boolean run() {
        if (booleanFunction != NULL)
            booleanFunction.run();
        else
            function.run();

        if (timer.seconds() > timeout)
            return true;
        else
            return false;
    }

    public void startTimer()
    {
        timer.reset();
    }
}
