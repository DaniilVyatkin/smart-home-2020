package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

import java.util.Map;

public class EventHandlerToCCEventHandlerAdapter implements EventHandler {
    SmartHome smartHome; //TODO исправить эту заглушку.
    Map<String, SensorEventType> eventTypeMapper;
    ru.sbt.mipt.oop.EventHandler eventHandler;
    // В данный момент в ru.sbt.mipt.oop хэндлеры получают на вход SmartHome, в котором хэндлят ивенты.
    // А в Coolcompany в интерфейсе хэндлера этого нет.

    public EventHandlerToCCEventHandlerAdapter(ru.sbt.mipt.oop.EventHandler eventHandler, Map eventTypeMapper) {
        this.eventHandler = eventHandler;
        this.eventTypeMapper = eventTypeMapper;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        if (eventTypeMapper.containsKey(event)) {
            SensorEventType sensorEventType = eventTypeMapper.get(event);
            if (sensorEventType != null) {
                this.eventHandler.handle(new SensorEvent(sensorEventType, event.getObjectId()), smartHome);
            }
        }
    }
}