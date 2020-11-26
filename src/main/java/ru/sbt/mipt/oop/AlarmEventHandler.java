package ru.sbt.mipt.oop;

public class AlarmEventHandler implements EventHandler {
    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getAlarm().activate(event.getCode());
        }
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getAlarm().deactivate(event.getCode());
        }
    }
}
