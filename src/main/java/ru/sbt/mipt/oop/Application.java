package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.RCcommands.*;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.rc.RemoteControlRegistry;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Application {
    private static SmartHome smartHome;
    private final HomeLoader homeLoader;
    private final EventHandlingLoop eventHandlingLoop;
    private EventGenerator eventGenerator;

    static Notifier notifier;

    public Application(HomeLoader homeLoader, EventGenerator eventGenerator, EventHandlingLoop eventHandlingLoop) {
        this.homeLoader = homeLoader;
        this.eventGenerator = eventGenerator;
        this.eventHandlingLoop = eventHandlingLoop;
    }

    public static void main(String... args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
        notifier = context.getBean(Notifier.class);

        HomeLoader homeLoader = new JsonHomeLoader(Constants.JSON_FILE_FOR_LOADING);
        EventGenerator eventGenerator = new RandomSensorEventGenerator();
        EventHandlingLoop eventHandlingLoop = new EventHandlingLoop(Arrays.asList(
                new DoorEventHandler(),
                new LightEventHandler(),
                new HallDoorEventHandler()
        ));
        Application application = new Application(homeLoader, eventGenerator, eventHandlingLoop);

        // Настроим кнопки для пульта
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new ActivateAlarmCommandRC()),
                Map.entry("2", new SetAlarmToAlertModeCommandRC()),
                Map.entry("3", new CloseHallDoorCommandRC(smartHome)),
                Map.entry("4", new TurnOffAllLightsCommandRC(smartHome)),
                Map.entry("A", new TurnOnAllLightsCommandRC(smartHome)),
                Map.entry("B", new TurnOnHallLightsCommandRC(smartHome))
        ));


        // Добавим пульт управления
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);
        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
        remoteControlRegistry.registerRemoteControl(remoteControl, "1");

        application.run();
    }

    private void run() {
        smartHome = homeLoader.loadHome();
        Alarm alarm = new Alarm(1111);
        smartHome.setAlarm(alarm);

        ArrayList<EventHandler> eventHandlers = new ArrayList<>(
                Arrays.asList(
                        new DoorEventHandler(),
                        new LightEventHandler(),
                        new HallDoorEventHandler(),
                        new AlarmEventHandler()
                        ));
        eventHandlingLoop.handleEvents(smartHome, eventGenerator);
    }
}