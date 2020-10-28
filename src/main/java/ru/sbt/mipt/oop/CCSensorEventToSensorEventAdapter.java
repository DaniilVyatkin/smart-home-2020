package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

public class CCSensorEventToSensorEventAdapter extends SensorEvent{
    private CCSensorEvent ccSensorEvent;

    // пришлось вынести конвертацию типа события из конструктора в отдельную функцию, так как
    // в конструкторе super() должно быть в первой строчке
    private static SensorEventType CCSensorEventEventTypeToEventTypeConverter(CCSensorEvent ccSensorEvent) {
        String ccSensorEventEventType = ccSensorEvent.getEventType();
        SensorEventType sensorEventType;
        // конвертируем тип события ccSensorEventEventType в тип события SensorEventType
        if (ccSensorEventEventType == "LightIsOn") {
            sensorEventType = SensorEventType.LIGHT_ON;
        } else {
            if (ccSensorEventEventType == "LightIsOff") {
                sensorEventType = SensorEventType.LIGHT_OFF;
            } else {
                // Здесь возможны разные варианты интерпретации "DoorIsUnlocked"
                if (ccSensorEventEventType == "DoorIsOpen" || ccSensorEventEventType == "DoorIsUnlocked") {
                    sensorEventType = SensorEventType.DOOR_OPEN;
                } else {
                    // Здесь возможны разные варианты интерпретации "DoorIsLocked"
                    if (ccSensorEventEventType == "DoorIsClosed" || ccSensorEventEventType == "DoorIsLocked") {
                        sensorEventType = SensorEventType.DOOR_CLOSED;
                    } else {
                        sensorEventType = null;
                    }
                }
            }
        }
        return sensorEventType;
    }

    public CCSensorEventToSensorEventAdapter(CCSensorEvent ccSensorEvent) {
        super(CCSensorEventEventTypeToEventTypeConverter(ccSensorEvent), ccSensorEvent.getObjectId());
    }
}