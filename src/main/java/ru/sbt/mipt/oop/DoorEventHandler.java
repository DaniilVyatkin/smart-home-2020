package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        System.out.println(event.toString());
        if (event.getType() == DOOR_OPEN) {
            OpenDoorAction openDoorAction = new OpenDoorAction();
            openDoorAction.setId(event.getObjectId());
            smartHome.execute(openDoorAction);
        }
        if (event.getType() == DOOR_CLOSED) {
            CloseDoorAction closeDoorAction = new CloseDoorAction();
            closeDoorAction.setId(event.getObjectId());
            smartHome.execute(closeDoorAction);
        }
    }
}