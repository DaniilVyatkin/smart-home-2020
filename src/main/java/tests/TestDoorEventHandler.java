package tests;

import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import ru.sbt.mipt.oop.*;
import java.io.File;
import static org.junit.Assert.*;

public class TestDoorEventHandler {

    @Test
    public void testHandleOpenDoor() {
        String objectId = "1";

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(Constants.JSON_PATH_FOR_TESTING_BASE_STATE);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();

        // Create event
        SensorEventType sensorEventType = SensorEventType.DOOR_OPEN;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);

        // Create instance of DoorEventHandler to handle the event
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        doorEventHandler.handle(event, smartHomeActual);

        // Write resulting home state to json
        JsonHomeWriter jsonHomeWriter = new JsonHomeWriter(Constants.JSON_FILE_TEST_ACTUAL);
        jsonHomeWriter.saveSmartHomeState(smartHomeActual);

        // Compare the resulting home state with the expected home state
        try {
            // Compare the resulting home state with the expected home state
            File fileActual = new File(Constants.JSON_FILE_TEST_ACTUAL);
            File fileExpected = new File(Constants.JSON_PATH_FOR_TESTING_DOOR_EVENT_HANDLER_EXPECTED_TEST_1);
            assertTrue(FileUtils.contentEquals(fileActual, fileExpected));
            fileActual.delete();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Problems with reading files for comparison");
        }
    }

    @Test
    public void testHandleCloseDoor() {
        String objectId = "1";

        // Create home for testing
        JsonHomeLoader jsonHomeLoaderActual = new JsonHomeLoader(
                Constants.JSON_PATH_FOR_TESTING_DOOR_EVENT_HANDLER_EXPECTED_TEST_1);
        SmartHome smartHomeActual = jsonHomeLoaderActual.loadHome();

        // Create event
        SensorEventType sensorEventType = SensorEventType.DOOR_CLOSED;
        SensorEvent event = new SensorEvent(sensorEventType, objectId);

        // Create instance of DoorEventHandler to handle the event
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        doorEventHandler.handle(event, smartHomeActual);

        // Write resulting home state to json
        JsonHomeWriter jsonHomeWriter = new JsonHomeWriter(Constants.JSON_FILE_TEST_ACTUAL);
        jsonHomeWriter.saveSmartHomeState(smartHomeActual);

        // Compare the resulting home state with the expected home state
        try {
            // Compare the resulting home state with the expected home state
            File fileActual = new File(Constants.JSON_FILE_TEST_ACTUAL);
            File fileExpected = new File(Constants.JSON_PATH_FOR_TESTING_DOOR_EVENT_HANDLER_EXPECTED_TEST_2);
            assertTrue(FileUtils.contentEquals(fileActual, fileExpected));
            fileActual.delete();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Problems with reading files for comparison");
        }
    }
}