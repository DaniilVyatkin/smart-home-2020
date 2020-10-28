package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

public class SensorEventToCCSensorEventAdapter extends CCSensorEvent{
    private CCSensorEvent sensorEvent;

    // пришлось вынести конвертацию типа события из конструктора в отдельную функцию, так как
    // в конструкторе super() должно быть в первой строчке
    private static String CCSensorEventEventTypeToEventTypeConverter(SensorEvent sensorEvent) {
        SensorEventType sensorEventEventType = sensorEvent.getType();
        String ccSensorEventType;
        if (sensorEventEventType == SensorEventType.LIGHT_ON) {
            ccSensorEventType = "LightIsOn";
        } else {
            if (sensorEventEventType == SensorEventType.LIGHT_OFF) {
                ccSensorEventType = "LightIsOff";
            } else {
                if (sensorEventEventType == SensorEventType.DOOR_OPEN) {
                    ccSensorEventType = "DoorIsOpen";
                } else {
                    if (sensorEventEventType == SensorEventType.DOOR_CLOSED) {
                        ccSensorEventType = "DoorIsClosed";
                    } else {
                        ccSensorEventType = null;
                    }
                }
            }
        }
        return ccSensorEventType;
    }

    public SensorEventToCCSensorEventAdapter(SensorEvent sensorEvent) {
        super(CCSensorEventEventTypeToEventTypeConverter(sensorEvent), sensorEvent.getObjectId());
    }
}
