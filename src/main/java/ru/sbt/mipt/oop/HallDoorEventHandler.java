package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class HallDoorEventHandler implements EventHandler {
    LightEventHandler lightEventHandler = new LightEventHandler();
    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_CLOSED) {
            for (Room room: smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (Door door: room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            System.out.println("Была закрыта входная дверь, начинаю выключать свет во всем доме");
                            for (Room targetRoom : smartHome.getRooms()) {
                                for (Light light : targetRoom.getLights()) {
                                    SensorEvent newSensorEvent = new SensorEvent(LIGHT_OFF, light.getId());
                                    lightEventHandler.handle(newSensorEvent, smartHome);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
