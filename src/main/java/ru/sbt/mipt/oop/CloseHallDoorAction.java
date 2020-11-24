package ru.sbt.mipt.oop;

public class CloseHallDoorAction implements Action {
    private String id;
    private SmartHome smartHome;

    public CloseHallDoorAction(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Room) {
            if (((Room) actionable).getName().equals("hall")) {
                for (Door door: ((Room) actionable).getDoors()) {
                    if (door.getId().equals(this.id)) {
                        TurnAllLightsOffAction turnAllLightsOffAction = new TurnAllLightsOffAction();
                        smartHome.execute(turnAllLightsOffAction);
                    }
                }
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }
}