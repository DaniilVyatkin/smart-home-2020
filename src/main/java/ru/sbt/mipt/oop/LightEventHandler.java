package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON) {
            TurnLightOnAction turnLightOnAction = new TurnLightOnAction();
            turnLightOnAction.setId(event.getObjectId());
            smartHome.execute(turnLightOnAction);
        }
        if (event.getType() == LIGHT_OFF) {
            TurnLightOffAction turnLightOffAction = new TurnLightOffAction();
            turnLightOffAction.setId(event.getObjectId());
            smartHome.execute(turnLightOffAction);
        }
    }
}