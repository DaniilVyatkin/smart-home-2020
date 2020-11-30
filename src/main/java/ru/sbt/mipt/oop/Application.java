package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.rc.RemoteControlRegistry;


public class Application {
    private static SmartHome smartHome;
    private final HomeLoader homeLoader;
    private final EventHandlingLoop eventHandlingLoop;
    private EventGenerator eventGenerator;

    public Application(HomeLoader homeLoader, EventGenerator eventGenerator, EventHandlingLoop eventHandlingLoop) {
        this.homeLoader = homeLoader;
        this.eventGenerator = eventGenerator;
        this.eventHandlingLoop = eventHandlingLoop;
    }

    public static void main(String... args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
        sensorEventsManager.start();
    }
}