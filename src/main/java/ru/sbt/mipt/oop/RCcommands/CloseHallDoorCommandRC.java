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
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            if (room.getName().equals("hall")) {
                Collection<Door> doors = room.getDoors();
                for (Door door : doors) {
                    CloseDoorAction closeDoorAction = new CloseDoorAction();
                    closeDoorAction.setId(door.getId());
                    smartHome.execute(closeDoorAction);
                }
                break;
            }
        }
    }
}