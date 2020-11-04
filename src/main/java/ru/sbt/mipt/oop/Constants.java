package ru.sbt.mipt.oop;

public class Constants {
    public static final String JSON_FILE_FOR_LOADING = "smart-home-1.js";
    public static final String JSON_PATH_FOR_WRITING = "output.js";

    // The following constants are for testing purposes
    public static final String JSON_PATH_FOR_TESTING_BASE_STATE = "home_base_state_to_test.js";
    public static final String JSON_FILE_TEST_ACTUAL =
            "home_state_testing_actual.js";

    // For door
    public static final String JSON_PATH_FOR_TESTING_DOOR_EVENT_HANDLER_EXPECTED_TEST_1 =
            "home_state_door_handler_test_1.js";
    public static final String JSON_PATH_FOR_TESTING_DOOR_EVENT_HANDLER_EXPECTED_TEST_2 =
            "home_state_door_handler_test_2.js";

    // For hall door
    public static final String JSON_PATH_HALL_DOOR_EXPECTED_TEST_1 =
            "home_state_halldoor_handler_test_1.js";


    // For light
    public static final String JSON_PATH_FOR_TESTING_LIGHT_EVENT_HANDLER_EXPECTED_TEST_1 =
            "home_state_light_handler_test_1.js";
    public static final String JSON_PATH_FOR_TESTING_LIGHT_EVENT_HANDLER_EXPECTED_TEST_2 =
            "home_state_light_handler_test_2.js";


    // For remote control testing
    public static final String JS_PATH_RC_HOME_BASE_STATE = "rc_test_home_base_state.js";
    public static final String JS_PATH_RC_CLOSE_HALL_DOOR = "rc_test_close_hall_door.js";
    public static final String JS_PATH_RC_TURN_OFF_ALL_LIGHTS = "rc_test_turn_off_all_lights.js";
    public static final String JS_PATH_RC_TURN_ON_ALL_LIGHTS = "rc_test_turn_on_all_lights.js";
    public static final String JS_PATH_RC_TURN_ON_HALL_LIGHTS = "rc_test_turn_on_hall_lights.js";
}