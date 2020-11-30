package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.*;

import java.util.Collection;

public class CloseHallDoorCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public CloseHallDoorCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(new CloseHallDoorAction(smartHome));
    }
}