package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Конфигурационный класс Spring IoC контейнера
 */
@Configuration
public class MyConfiguration {
    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(new DoorEventHandler()));
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(new HallDoorEventHandler()));
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(new LightEventHandler()));
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(new AlarmEventHandler()));
        return sensorEventsManager;
    }

    static EventHandlerToCCEventHandlerAdapter eventHandlerToCCEventHandlerAdapter(ru.sbt.mipt.oop.EventHandler eventHandler) {
        EventHandlerToCCEventHandlerAdapter eventHandlerToCCEventHandlerAdapter = new EventHandlerToCCEventHandlerAdapter();
        eventHandlerToCCEventHandlerAdapter.setEventHandler(eventHandler);
        return eventHandlerToCCEventHandlerAdapter;
    }

    @Bean
    Notifier getNotifier() {
        return new DummySMSNotifier();
    }
}