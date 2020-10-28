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
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(doorEventHandler()));
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(hallDoorEventHandler()));
        sensorEventsManager.registerEventHandler(eventHandlerToCCEventHandlerAdapter(lightEventHandler()));
        return sensorEventsManager;
    }

    @Bean
    DoorEventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    HallDoorEventHandler hallDoorEventHandler() {
        return new HallDoorEventHandler();
    }

    @Bean
    LightEventHandler lightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    EventHandlerToCCEventHandlerAdapter eventHandlerToCCEventHandlerAdapter(ru.sbt.mipt.oop.EventHandler eventHandler) {
        EventHandlerToCCEventHandlerAdapter eventHandlerToCCEventHandlerAdapter = new EventHandlerToCCEventHandlerAdapter();
        eventHandlerToCCEventHandlerAdapter.setEventHandler(eventHandler);
        return eventHandlerToCCEventHandlerAdapter;
    }
}