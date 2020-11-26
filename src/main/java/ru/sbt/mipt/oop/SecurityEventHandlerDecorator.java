package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlertAlarmState;

public class SecurityEventHandlerDecorator implements EventHandler {
    private EventHandler wrappedHandler;

    public SecurityEventHandlerDecorator(EventHandler eventHandler) {
        this.wrappedHandler = eventHandler;
    }

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        if (event.getType() == SensorEventType.ALARM_ACTIVATE || event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            wrappedHandler.handle(event, smartHome);
        }
        else {
            if (alarm.getAlarmState() instanceof AlertAlarmState) {
                System.out.println("Alarm is in alert state and caught some sensor activity!");
                System.out.println("Sending more SMS!");
                return;
            }
            wrappedHandler.handle(event, smartHome);
            if (alarm.getAlarmState() instanceof ActivatedAlarmState) {
                alarm.alert();
                System.out.println("Alarm caught some sensor activity!");
                System.out.println("Sending SMS!");
            }
        }
    }
}