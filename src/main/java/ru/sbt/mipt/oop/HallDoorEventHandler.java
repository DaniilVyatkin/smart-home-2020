package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HallDoorEventHandler implements EventHandler {
    private CommandSender commandSender;
    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        CommandSender commandSender = new PrinterCommandSender();
        if (event.getType() == DOOR_CLOSED) {
            CloseDoorAction closeDoorAction = new CloseDoorAction();
            closeDoorAction.setId(event.getObjectId());
            smartHome.execute(closeDoorAction);
        }
    }
}