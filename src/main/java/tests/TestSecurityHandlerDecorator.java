package tests;

import com.coolcompany.smarthome.events.EventHandler;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlertAlarmState;
import ru.sbt.mipt.oop.alarm.DeactivatedAlarmState;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestSecurityHandlerDecorator {

    @Test
    public void testHandleOpenDoorWhileInDeactivatedState() {
        String objectId = "1";
        int code = 1;

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JSON_PATH_FOR_TESTING_BASE_STATE);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        alarm.deactivate(code);
        smartHomeActual.setAlarm(alarm);

        // Create event
        SensorEventType sensorEventType = SensorEventType.DOOR_OPEN;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);

        // Create instance of DoorEventHandler to handle the event
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        SecurityEventHandlerDecorator securityEventHandlerDecorator = new SecurityEventHandlerDecorator(doorEventHandler);
        securityEventHandlerDecorator.handle(event, smartHomeActual);

        assertTrue(alarm.getAlarmState() instanceof DeactivatedAlarmState);
    }

    @Test
    public void testHandleOpenDoorWhileInActivatedState() {
        String objectId = "1";
        int code = 1;

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JSON_PATH_FOR_TESTING_BASE_STATE);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        smartHomeActual.setAlarm(alarm);

        // Create event
        SensorEventType sensorEventType = SensorEventType.DOOR_OPEN;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);

        // Create instance of DoorEventHandler to handle the event
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        SecurityEventHandlerDecorator securityEventHandlerDecorator = new SecurityEventHandlerDecorator(doorEventHandler);
        securityEventHandlerDecorator.handle(event, smartHomeActual);

        assertTrue(alarm.getAlarmState() instanceof AlertAlarmState);
    }

    @Test
    public void testHandleOpenDoorWhileInAlertState() {
        String objectId = "1";
        int code = 1;

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JSON_PATH_FOR_TESTING_BASE_STATE);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        alarm.alert();
        smartHomeActual.setAlarm(alarm);

        // Create event
        SensorEventType sensorEventType = SensorEventType.DOOR_OPEN;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);

        // Create instance of DoorEventHandler to handle the event
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        SecurityEventHandlerDecorator securityEventHandlerDecorator = new SecurityEventHandlerDecorator(doorEventHandler);
        securityEventHandlerDecorator.handle(event, smartHomeActual);

        assertTrue(alarm.getAlarmState() instanceof AlertAlarmState);
    }
}