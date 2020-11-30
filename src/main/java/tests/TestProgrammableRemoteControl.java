package tests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.RCcommands.CloseHallDoorCommandRC;
import ru.sbt.mipt.oop.RCcommands.CommandRemoteControl;
import ru.sbt.mipt.oop.RCcommands.TurnOnHallLightsCommandRC;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestProgrammableRemoteControl {

    @Test
    public void testOnButtonPressed() {
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
}