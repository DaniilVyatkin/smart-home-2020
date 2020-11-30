package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.*;

public class TurnOnHallLightsCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public TurnOnHallLightsCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(new TurnOnHallLightsAction(smartHome));
    }
}