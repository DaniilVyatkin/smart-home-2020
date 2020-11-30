package ru.sbt.mipt.oop.alarm;

public class ActivatedAlarmState implements AlarmState {
    private final Alarm alarm;

    public ActivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(int code) {
        if (!alarm.isCodeCorrect(code)) {
            alarm.setAlarmState(new AlertAlarmState(alarm));
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
            alarm.setAlarmState(new AlertAlarmState(alarm));
            System.out.println("Wrong code! Alarm was set to alert state!");
        }
    }

    @Override
    public void alert() {
        alarm.setAlarmState(new AlertAlarmState(alarm));
        System.out.println("Alarm was set to alert state!");
    }
}
