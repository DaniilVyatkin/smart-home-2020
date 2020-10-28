package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

public class EventHandlerToCCEventHandlerAdapter implements EventHandler {
    ru.sbt.mipt.oop.EventHandler eventHandler;
    SmartHome smartHome; //TODO исправить эту заглушку.
    // В данный момент в ru.sbt.mipt.oop хэндлеры получают на вход SmartHome, в котором хэндлят ивенты.
    // А в Coolcompany в интерфейсе хэндлера этого нет.

    public void setEventHandler(ru.sbt.mipt.oop.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        this.eventHandler.handle(new CCSensorEventToSensorEventAdapter(event), smartHome);
    }
}