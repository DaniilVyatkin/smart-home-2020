package ru.sbt.mipt.oop.alarm;

public class AlertAlarmState implements AlarmState {
    private final Alarm alarm;

    public AlertAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
        if (alarm.isCodeCorrect(code)) {
            alarm.setAlarmState(new ActivatedAlarmState(alarm));
            System.out.println("Alarm was activated");
        }
        else {
            System.out.println("Wrong code! Alarm was set to alert state!");
        }
    }

    @Override
    public void deactivate(int code) {
        if (alarm.isCodeCorrect(code)) {
            alarm.setAlarmState(new DeactivatedAlarmState(alarm));
            System.out.println("Alarm was deactivated");
        }
        else {
            System.out.println("Wrong code! Alarm was already set to alert state!");
        }
    }

    @Override
    public void alert() {
        System.out.println("Alarm is already in alert state!");
    }
}