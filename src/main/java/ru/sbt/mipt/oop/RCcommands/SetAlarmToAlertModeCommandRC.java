package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.SmartHome;

public class SetAlarmToAlertModeCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public SetAlarmToAlertModeCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().alert();
    }
}