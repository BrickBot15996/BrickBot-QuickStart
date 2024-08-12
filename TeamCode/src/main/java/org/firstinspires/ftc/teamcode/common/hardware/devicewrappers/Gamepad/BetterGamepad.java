package org.firstinspires.ftc.teamcode.common.hardware.devicewrappers.Gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

public class BetterGamepad
{
    Gamepad gamepad;

    private HashMap<Button,Boolean> buttons;

    public BetterGamepad(Gamepad gamepad)
    {
        this.gamepad = gamepad;
        
    }
}
