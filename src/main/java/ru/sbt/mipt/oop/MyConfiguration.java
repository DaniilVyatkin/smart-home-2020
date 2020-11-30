package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Конфигурационный класс Spring IoC контейнера
 */
@Configuration
public class MyConfiguration {
    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventHandler> eventHandlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        eventHandlers.forEach(handler -> sensorEventsManager.registerEventHandler(getEventHandlerAdaptedToCC(handler)));
        return sensorEventsManager;
    }

    @Bean
    EventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    EventHandler hallDoorEventHandler() {
        return new HallDoorEventHandler();
    }

    @Bean
    EventHandler lightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    EventHandler alarmEventHandler() {
        return new AlarmEventHandler();
    }

    @Bean
    Map eventTypeMapper() {
        Map<String, SensorEventType> mapper = new HashMap<>();
        mapper.put("LightIsOn", SensorEventType.LIGHT_ON);
        mapper.put("LightIsOff", SensorEventType.LIGHT_OFF);
        mapper.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        mapper.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        mapper.put("DoorIsUnlocked", SensorEventType.DOOR_OPEN);
        mapper.put("DoorIsLocked", SensorEventType.DOOR_CLOSED);
        return mapper;
    }

    @Bean
    Notifier getNotifier() {
        return new DummySMSNotifier();
    }

    com.coolcompany.smarthome.events.EventHandler getEventHandlerAdaptedToCC(EventHandler eventHandler) {
        return new EventHandlerToCCEventHandlerAdapter(
                new SecurityEventHandlerDecorator(eventHandler, getNotifier()), eventTypeMapper());
    }
}