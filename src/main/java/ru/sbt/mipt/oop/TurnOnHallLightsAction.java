package ru.sbt.mipt.oop;

public class TurnOnHallLightsAction implements Action {
    private SmartHome smartHome;

    public TurnOnHallLightsAction(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Room) {
            if (((Room) actionable).getName().equals("hall")) {
                for (Light light: ((Room) actionable).getLights()) {
                    TurnLightOnAction turnLightOnAction  = new TurnLightOnAction(light.getId());
                    smartHome.execute(turnLightOnAction);
                }
            }
        }
    }
}