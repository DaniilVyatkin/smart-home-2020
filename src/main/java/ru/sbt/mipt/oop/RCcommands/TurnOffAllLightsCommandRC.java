package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.*;

import java.util.Collection;

public class TurnOffAllLightsCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public TurnOffAllLightsCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(new TurnAllLightsOffAction());
    }
}