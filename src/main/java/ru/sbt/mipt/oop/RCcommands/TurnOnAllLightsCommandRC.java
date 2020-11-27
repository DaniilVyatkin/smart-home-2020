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
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            Collection<Light> lights = room.getLights();
            for (Light light:lights) {
                TurnLightOnAction turnLightOnAction = new TurnLightOnAction();
                turnLightOnAction.setId(light.getId());
                smartHome.execute(turnLightOnAction);
            }
        }
    }
}