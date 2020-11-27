package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.RCcommands.CommandRemoteControl;

import java.util.HashMap;
import java.util.Map;

public class ProgrammableRemoteControl implements RemoteControl {
    Map<String, CommandRemoteControl> buttonMap = new HashMap<>();

    public ProgrammableRemoteControl(Map<String, CommandRemoteControl> buttonMap) {
        this.buttonMap = buttonMap;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonMap.containsKey(buttonCode)) {
            buttonMap.get(buttonCode).execute();
        }
        else {
            System.out.println("Для данной кнопки команда не задана!");
        }
    }
}