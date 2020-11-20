package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        boolean isSetDoorOpen = (event.getType() == DOOR_OPEN);
                        door.setOpen(isSetDoorOpen);
                        String stringToPrint = "Door " + door.getId() + " in room " + room.getName() + " was ";
                        if (isSetDoorOpen) {
                            System.out.println(stringToPrint + "opened.");
                        } else {
                            System.out.println(stringToPrint + "closed.");
                        }
                    }
                }
            }
        }
    }
}