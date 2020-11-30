package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.SmartHome;

public class ActivateAlarmCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;
    private int code;

    public ActivateAlarmCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(code);
    }

    public void setCode(int code) {
        this.code = code;
    }
}