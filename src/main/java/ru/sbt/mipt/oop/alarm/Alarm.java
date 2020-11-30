package ru.sbt.mipt.oop.alarm;

public class Alarm {
    private final int code;
    private AlarmState alarmState;

    public Alarm(int code) {
        this.code = code;
        this.alarmState = new ActivatedAlarmState(this);
    }

    public void activate(int code) {
        alarmState.activate(code);
    }

    public void deactivate(int code) {
        alarmState.deactivate(code);
    }

    public void alert() {
        alarmState.alert();
    }

    boolean isCodeCorrect(int code) {
        return this.code == code;
    }

    public boolean isActivated() {
        return (alarmState instanceof ActivatedAlarmState);
    }

    public boolean isDeactivated() {
        return (alarmState instanceof DeactivatedAlarmState);
    }

    public boolean isAlert() {
        return (alarmState instanceof AlertAlarmState);
    }

    void setAlarmState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

}