package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.*;

import java.util.Collection;

public class TurnOnHallLightsCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public TurnOnHallLightsCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            if (room.getName().equals("hall")) {
                Collection<Light> lights = room.getLights();
                for (Light light:lights) {
                    TurnLightOnAction turnLightOnAction = new TurnLightOnAction();
                    turnLightOnAction.setId(light.getId());
                    smartHome.execute(turnLightOnAction);
                }
                break;
            }
        }
    }
}