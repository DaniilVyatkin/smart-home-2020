package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.RCcommands.*;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.rc.RemoteControl;
import ru.sbt.mipt.oop.rc.RemoteControlRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Конфигурационный класс Spring IoC контейнера
 */
@Configuration
public class MyConfiguration {
    private int code = 1111;
    private String rcid = "1";
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

    // Ниже идет конфигурация дома, нужная для конфигурации пульта управления
    @Bean
    Alarm getAlarm() {
        Alarm alarm = new Alarm(code);
        return alarm;
    }

    @Bean
    SmartHome getSmartHome() {
        HomeLoader homeLoader = new JsonHomeLoader(Constants.JSON_FILE_FOR_LOADING);
        SmartHome smartHome = homeLoader.loadHome();
        smartHome.setAlarm(getAlarm());
        return smartHome;
    }

    // Ниже идет конфигурация пульта управления
    @Bean
    Map getButtonMap() {
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new ActivateAlarmCommandRC(getSmartHome())),
                Map.entry("2", new SetAlarmToAlertModeCommandRC(getSmartHome())),
                Map.entry("3", new CloseHallDoorCommandRC(getSmartHome())),
                Map.entry("4", new TurnOffAllLightsCommandRC(getSmartHome())),
                Map.entry("A", new TurnOnAllLightsCommandRC(getSmartHome())),
                Map.entry("B", new TurnOnHallLightsCommandRC(getSmartHome()))
        ));
        return buttonMap;
    }

    @Bean
    RemoteControl getRemoteControl() {
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(getButtonMap());
        return remoteControl;
    }


    @Bean
    RemoteControlRegistry getRemoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(getRemoteControl(), rcid);
        return remoteControlRegistry;
    }
}