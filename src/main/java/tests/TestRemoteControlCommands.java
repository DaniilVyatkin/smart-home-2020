package tests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.RCcommands.*;
import ru.sbt.mipt.oop.alarm.Alarm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestRemoteControlCommands {

    @Test
    public void TestCloseHallDoorCommandRC() {
        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JS_PATH_RC_HOME_BASE_STATE);
        SmartHome smartHome = jsonHomeLoaderActual.loadHome();

        // Create remote control
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new CloseHallDoorCommandRC(smartHome))));
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);

        // Press button
        remoteControl.onButtonPressed("1");

        // Write resulting home state to json
        JsonHomeWriter jsonHomeWriter = new JsonHomeWriter(Constants.JSON_FILE_TEST_ACTUAL);
        jsonHomeWriter.saveSmartHomeState(smartHome);

        // Compare the resulting home state with the expected home state
        try {
            File fileActual = new File(Constants.JSON_FILE_TEST_ACTUAL, "utf-8");
            File fileExpected = new File(Constants.JSON_PATH_HALL_DOOR_EXPECTED_TEST_1, "utf-8");
            assertTrue(FileUtils.contentEquals(fileActual, fileExpected));
            fileActual.delete();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Problems with reading files for comparison");
        }
    }

    @Test
    public void TestTurnOffAllLightsCommandRC() {
        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JS_PATH_RC_HOME_BASE_STATE);
        SmartHome smartHome = jsonHomeLoaderActual.loadHome();

        // Create remote control
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new TurnOffAllLightsCommandRC(smartHome))));
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);

        // Press button
        remoteControl.onButtonPressed("1");

        // Write resulting home state to json
        JsonHomeWriter jsonHomeWriter = new JsonHomeWriter(Constants.JSON_FILE_TEST_ACTUAL);
        jsonHomeWriter.saveSmartHomeState(smartHome);

        // Compare the resulting home state with the expected home state
        try {
            File fileActual = new File(Constants.JSON_FILE_TEST_ACTUAL);
            File fileExpected = new File(Constants.JS_PATH_RC_TURN_OFF_ALL_LIGHTS);
            assertTrue(FileUtils.contentEquals(fileActual, fileExpected));
            fileActual.delete();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Problems with reading files for comparison");
        }
    }

    @Test
    public void TestTurnOnAllLightsCommandRC() {
        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JS_PATH_RC_HOME_BASE_STATE);
        SmartHome smartHome = jsonHomeLoaderActual.loadHome();

        // Create remote control
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new TurnOnAllLightsCommandRC(smartHome))));
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);

        // Press button
        remoteControl.onButtonPressed("1");

        // Write resulting home state to json
        JsonHomeWriter jsonHomeWriter = new JsonHomeWriter(Constants.JSON_FILE_TEST_ACTUAL);
        jsonHomeWriter.saveSmartHomeState(smartHome);

        // Compare the resulting home state with the expected home state
        try {
            File fileActual = new File(Constants.JSON_FILE_TEST_ACTUAL);
            File fileExpected = new File(Constants.JS_PATH_RC_TURN_ON_ALL_LIGHTS);
            assertTrue(FileUtils.contentEquals(fileActual, fileExpected));
            fileActual.delete();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Problems with reading files for comparison");
        }
    }

    @Test
    public void TestTurnOnHallLightsCommandRC() {
        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JS_PATH_RC_HOME_BASE_STATE);
        SmartHome smartHome = jsonHomeLoaderActual.loadHome();

        // Create remote control
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new TurnOnHallLightsCommandRC(smartHome))));
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);

        // Press button
        remoteControl.onButtonPressed("1");

        // Write resulting home state to json
        JsonHomeWriter jsonHomeWriter = new JsonHomeWriter(Constants.JSON_FILE_TEST_ACTUAL);
        jsonHomeWriter.saveSmartHomeState(smartHome);

        // Compare the resulting home state with the expected home state
        try {
            File fileActual = new File(Constants.JSON_FILE_TEST_ACTUAL);
            File fileExpected = new File(Constants.JS_PATH_RC_TURN_ON_HALL_LIGHTS);
            assertTrue(FileUtils.contentEquals(fileActual, fileExpected));
            fileActual.delete();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Problems with reading files for comparison");
        }
    }

    @Test
    public void TestActivateAlarmCommandRC() {
        int code = 1;
        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JS_PATH_RC_HOME_BASE_STATE);
        SmartHome smartHome = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        smartHome.setAlarm(alarm);

        // Create remote control
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new ActivateAlarmCommandRC(smartHome))));
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);
        remoteControl.setCode(code);

        // Press button
        remoteControl.onButtonPressed("1");

        assertTrue(smartHome.getAlarm().isActivated());
    }

    @Test
    public void TestSetAlarmToAlertModeCommandRC() {
        int code = 1;
        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JS_PATH_RC_HOME_BASE_STATE);
        SmartHome smartHome = jsonHomeLoaderActual.loadHome();
        Alarm alarm = new Alarm(code);
        smartHome.setAlarm(alarm);

        // Create remote control
        HashMap<String, CommandRemoteControl> buttonMap = new HashMap<String, CommandRemoteControl>(Map.ofEntries(
                Map.entry("1", new SetAlarmToAlertModeCommandRC(smartHome))));
        ProgrammableRemoteControl remoteControl = new ProgrammableRemoteControl(buttonMap);

        // Press button
        remoteControl.onButtonPressed("1");

        assertTrue(smartHome.getAlarm().isAlert());
    }
}