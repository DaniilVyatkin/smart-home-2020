package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;

import static ru.sbt.mipt.oop.MyConfiguration.eventHandlerToCCEventHandlerAdapter;


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
        return sensorEventsManager;
    }

    static EventHandlerToCCEventHandlerAdapter eventHandlerToCCEventHandlerAdapter(ru.sbt.mipt.oop.EventHandler eventHandler) {
        EventHandlerToCCEventHandlerAdapter eventHandlerToCCEventHandlerAdapter = new EventHandlerToCCEventHandlerAdapter();
        eventHandlerToCCEventHandlerAdapter.setEventHandler(eventHandler);
        return eventHandlerToCCEventHandlerAdapter;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }
}