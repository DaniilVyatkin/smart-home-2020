package tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.*;

import static org.junit.Assert.*;

public class TestDeactivatedAlarmState {

    @Test
    public void testActivateWithCorrectCode() {
        int code = 1;

        // Create alarm for testing
        Alarm alarm = new Alarm(code);
        alarm.deactivate(code);
        AlarmState alarmState = new DeactivatedAlarmState(alarm);
        alarmState.activate(code);
        assertTrue(alarm.getAlarmState() instanceof ActivatedAlarmState);
    }

    @Test
    public void testActivateWithIncorrectCode() {
        int code = 1;
        int wrongCode = 2;

        // Create alarm for testing
        Alarm alarm = new Alarm(code);
        alarm.deactivate(code);
        AlarmState alarmState = new DeactivatedAlarmState(alarm);
        alarmState.activate(wrongCode);
        assertTrue(alarm.getAlarmState() instanceof AlertAlarmState);
    }

    @Test
    public void testDeactivateWithCorrectCode() {
        int code = 1;

        // Create alarm for testing
        Alarm alarm = new Alarm(code);
        alarm.deactivate(code);
        AlarmState alarmState = new DeactivatedAlarmState(alarm);
        alarmState.deactivate(code);
        System.out.println(alarm.getAlarmState());
        assertTrue(alarm.getAlarmState() instanceof DeactivatedAlarmState);
    }

    @Test
    public void testDeactivateWithIncorrectCode() {
        int code = 1;
        int wrongCode = 2;

        // Create alarm for testing
        Alarm alarm = new Alarm(code);
        alarm.deactivate(code);
        AlarmState alarmState = new DeactivatedAlarmState(alarm);
        alarmState.deactivate(wrongCode);
        assertTrue(alarm.getAlarmState() instanceof AlertAlarmState);
    }

    @Test
    public void testAlert() {
        int code = 1;

        // Create alarm for testing
        Alarm alarm = new Alarm(code);
        alarm.deactivate(code);
        AlarmState alarmState = new DeactivatedAlarmState(alarm);
        alarmState.alert();
        assertTrue(alarm.getAlarmState() instanceof AlertAlarmState);
    }
}
