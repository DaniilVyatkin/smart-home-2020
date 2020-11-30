package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.ArrayList;
import java.util.Arrays;


public class Application {
    private static SmartHome smartHome;
    private final HomeLoader homeLoader;
    private EventGenerator eventGenerator;
    static Notifier notifier;

    public Application(HomeLoader homeLoader, EventGenerator eventGenerator) {
        this.homeLoader = homeLoader;
        this.eventGenerator = eventGenerator;
    }

    public static void main(String... args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
//        notifier = context.getBean(Notifier.class);
    }

//    private void run() {
//        smartHome = homeLoader.loadHome();
//        Alarm alarm = new Alarm(1111);
//        smartHome.setAlarm(alarm);
//
//        ArrayList<EventHandler> eventHandlers = new ArrayList<>(
//                Arrays.asList(
//                        new DoorEventHandler(),
//                        new LightEventHandler(),
//                        new HallDoorEventHandler(),
//                        new AlarmEventHandler()
//                        ));
//        // начинаем цикл обработки событий
//        while (true) {
//            SensorEvent event = eventGenerator.getNextSensorEvent();
//            if (event == null) return;
//            System.out.println("Got event: " + event);
//
//            for (EventHandler handler : eventHandlers) {
//                EventHandler securityEventHandlerDecorator = new SecurityEventHandlerDecorator(handler, notifier);
//                securityEventHandlerDecorator.handle(event, smartHome);
//            }
//        }
//    }
}