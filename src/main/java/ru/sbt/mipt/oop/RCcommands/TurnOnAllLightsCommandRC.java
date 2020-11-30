package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.*;

import java.util.Collection;

public class TurnOnAllLightsCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public TurnOnAllLightsCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(new TurnAllLightsOnAction());
    }
}