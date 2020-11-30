package ru.sbt.mipt.oop;

public class CloseHallDoorAction implements Action {
    private SmartHome smartHome;

    public CloseHallDoorAction(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Room) {
            if (((Room) actionable).getName().equals("hall")) {
                for (Door door: ((Room) actionable).getDoors()) {
                    CloseDoorAction closeDoorAction = new CloseDoorAction(door.getId());
                    TurnAllLightsOffAction turnAllLightsOffAction = new TurnAllLightsOffAction();
                    smartHome.execute(closeDoorAction);
                    smartHome.execute(turnAllLightsOffAction);
                }
            }
        }
    }
}