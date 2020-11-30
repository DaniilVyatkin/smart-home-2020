package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.RCcommands.ActivateAlarmCommandRC;
import ru.sbt.mipt.oop.RCcommands.SetAlarmToAlertModeCommandRC;
import ru.sbt.mipt.oop.rc.RemoteControl;
import ru.sbt.mipt.oop.RCcommands.CommandRemoteControl;

import java.util.Map;

public class ProgrammableRemoteControl implements RemoteControl {
    Map<String, CommandRemoteControl> buttonMap;
    int code;

    public ProgrammableRemoteControl(Map<String, CommandRemoteControl> buttonMap) {
        this.buttonMap = buttonMap;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonMap.containsKey(buttonCode)) {
            if (buttonMap.get(buttonCode) instanceof ActivateAlarmCommandRC) {
                ((ActivateAlarmCommandRC) buttonMap.get(buttonCode)).setCode(code);
            };
            buttonMap.get(buttonCode).execute();
        }
        else {
            System.out.println("Для данной кнопки команда не задана!");
        }
    }

    public void setCode(int code) {
        this.code = code;
    }
}