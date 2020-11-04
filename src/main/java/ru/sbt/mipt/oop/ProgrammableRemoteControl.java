package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.RCcommands.CommandRemoteControl;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgrammableRemoteControl implements RemoteControl {
    HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>();
    @Override
    public void onButtonPressed(String buttonCode) {
        buttonMap.get(buttonCode).execute();
    }

    public void setButton(String buttonCode, CommandRemoteControl commandRemoteControl) {
        buttonMap.put(buttonCode, commandRemoteControl);
    }
}