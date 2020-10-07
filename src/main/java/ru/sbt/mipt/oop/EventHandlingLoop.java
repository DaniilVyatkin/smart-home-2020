package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventHandlingLoop {

    public static void handleEvents(SmartHome smartHome) {
        // начинаем цикл обработки событий
        while (true) {
            SensorEvent event = getNextSensorEvent();
            if (event == null) {
                break;
            }
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                handleLightEvent(event, smartHome);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                handleDoorEvent(event, smartHome);
            }
        }
    }

    private static void handleLightEvent(SensorEvent event, SmartHome smartHome) {
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

    private static void handleDoorEvent(SensorEvent event, SmartHome smartHome) {
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
                    additionalActionInCertainRoomsOnDoorEvent(event, room, smartHome);
                }
            }
        }
    }

    private static void additionalActionInCertainRoomsOnDoorEvent(SensorEvent event, Room room, SmartHome smartHome) {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
         if (room.getName().equals("hall")) {
             for (Room homeRoom : smartHome.getRooms()) {
                 for (Light light : homeRoom.getLights()) {
                     light.setOn(false);
                     SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                     sendCommand(command);
                 }
             }
         }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}