package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        boolean isSetLightOn = (event.getType() == LIGHT_ON);
                        light.setOn(isSetLightOn);
                        String stringToPrint = "Light " + light.getId() + " in room " + room.getName() + " was turned ";
                        if (isSetLightOn) {
                            System.out.println(stringToPrint + "on.");
                        } else {
                            System.out.println(stringToPrint + "off.");
                        }
                    }
                }
            }
        }
    }
}