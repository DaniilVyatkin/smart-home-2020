package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.RCcommands.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Application {
    private static SmartHome smartHome;
    private final HomeLoader homeLoader;
    private EventGenerator eventGenerator;

    public Application(HomeLoader homeLoader, EventGenerator eventGenerator) {
        this.homeLoader = homeLoader;
        this.eventGenerator = eventGenerator;
    }

    public static void main(String... args) {
//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
//        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
//        sensorEventsManager.start();

        // Я правильно понял, что теперь мы вместо нижележащего кода (4 строчки)
        // используем SensorEventsManager из com.coolcompany.smarthome.events,
        // который делает примерно все то же самое, но без загрузки дома?

        HomeLoader homeLoader = new JsonHomeLoader(Constants.JSON_FILE_FOR_LOADING);
        EventGenerator eventGenerator = new RandomSensorEventGenerator();
        Application application = new Application(homeLoader, eventGenerator);

        // Добавим пульт управления
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, "1");
        // Настроим некоторые его кнопки
        remoteControl.setButton("1", new ActivateAlarmCommandRC());
        remoteControl.setButton("2", new SetAlarmToAlertModeCommandRC());
        remoteControl.setButton("3", new CloseHallDoorCommandRC(smartHome));
        remoteControl.setButton("4", new TurnOffAllLightsCommandRC(smartHome));
        remoteControl.setButton("A", new TurnOnAllLightsCommandRC(smartHome));
        remoteControl.setButton("B", new TurnOnHallLightsCommandRC(smartHome));

        application.run();
    }

    private void run() {
        smartHome = homeLoader.loadHome();
        ArrayList<EventHandler> eventHandlers = new ArrayList<>(
                Arrays.asList(new DoorEventHandler(), new LightEventHandler(), new HallDoorEventHandler()));

        // начинаем цикл обработки событий
        while (true) {
            SensorEvent event = eventGenerator.getNextSensorEvent();
            if (event == null) return;
            System.out.println("Got event: " + event);

            for (EventHandler handler : eventHandlers) {
                handler.handle(event, smartHome);
            }
        }
    }
}