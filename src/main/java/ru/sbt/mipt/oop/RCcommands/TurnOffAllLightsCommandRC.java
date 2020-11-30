package ru.sbt.mipt.oop.RCcommands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.TurnLightOffAction;

import java.util.Collection;

public class TurnOffAllLightsCommandRC implements CommandRemoteControl {
    private SmartHome smartHome;

    public TurnOffAllLightsCommandRC(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room: rooms) {
            Collection<Light> lights = room.getLights();
            for (Light light:lights) {
                TurnLightOffAction turnLightOffAction = new TurnLightOffAction();
                turnLightOffAction.setId(light.getId());
                smartHome.execute(turnLightOffAction);
            }
        }
    }
}