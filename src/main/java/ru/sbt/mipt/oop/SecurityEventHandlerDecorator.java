package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlertAlarmState;

public class SecurityEventHandlerDecorator implements EventHandler {
    private EventHandler wrappedHandler;
    private Notifier notifier;

    public SecurityEventHandlerDecorator(EventHandler eventHandler, Notifier notifier) {
        this.wrappedHandler = eventHandler;
        this.notifier = notifier;
    }

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        if (event.getType() == SensorEventType.ALARM_ACTIVATE || event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            wrappedHandler.handle(event, smartHome);
        }
        else {
            if (alarm.isAlert()) {
                notifier.makeNotification();
                return;
            }
            wrappedHandler.handle(event, smartHome);
            if (alarm.isActivated()) {
                alarm.alert();
                notifier.makeNotification();
            }
        }
    }
}