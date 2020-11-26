package tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivatedAlarmState;

import static org.junit.Assert.*;

public class TestAlarmEventHandler {
    @Test
    public void testHandleAlarmActivate() {
        String objectId = "1";
        int code = 1;

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JSON_PATH_FOR_TESTING_BASE_STATE);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        smartHomeActual.setAlarm(alarm);

        // Create event
        SensorEventType sensorEventType = SensorEventType.ALARM_ACTIVATE;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);
        event.setCode(code);

        // Create instance of AlarmEventHandler to handle the event
        AlarmEventHandler alarmEventHandler = new AlarmEventHandler();
        alarmEventHandler.handle(event, smartHomeActual);

        // Compare the resulting alarm state with the expected alarm state

        assertTrue(smartHomeActual.getAlarm().getAlarmState() instanceof ActivatedAlarmState);
    }

    @Test
    public void testHandleAlarmDeactivate() {
        String objectId = "1";
        int code = 1;

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JSON_PATH_FOR_TESTING_BASE_STATE);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        smartHomeActual.setAlarm(alarm);

        // Create event
        SensorEventType sensorEventType = SensorEventType.ALARM_DEACTIVATE;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);
        event.setCode(code);

        // Create instance of AlarmEventHandler to handle the event
        AlarmEventHandler alarmEventHandler = new AlarmEventHandler();
        alarmEventHandler.handle(event, smartHomeActual);

        // Compare the resulting alarm state with the expected alarm state

        assertTrue(smartHomeActual.getAlarm().getAlarmState() instanceof DeactivatedAlarmState);
    }
}