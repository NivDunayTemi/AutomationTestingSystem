package AutomationTestingSystem.Constants;

public class LoggerConstants
{
    public static class Status
    {
        public static final String OK = "OK";
        public static final String SUCCESS = "Success";
        public static final String FAIL = "FAIL";
        public static final String OCCURRED = "Occurred";
        public static final String START = "Start";
    }

    public static class Boot
    {
        public static final String TEST_BOOT = "TEST_BOOT";
        public static final String INTERNET_CONNECTION = "Internet Connection";
        public static final String MQTT_CONNECTION = "MQTT Connection";
        public static final String BOOT_ENDED = "Boot Ended";
        public static final String BOOT_STARTED = "Boot Started";
        public static final String ROBOX_OS = "Robox OS";
        public static final String IS_READY = "Robox Is Ready";
        public static final String WAKEUP_DISPLAYED = "WakeUp Displayed";
        public static final String TIME_OUT = "Boot TimeOut";
        public static final String REQUEST_WAKEUP_LAYER_TO_DISPLAY = "Request WakeUp Layer To Display";
        public static final String REQUEST_GREETINGS = "Request Greetings";
        public static final String GREETINGS = "Greetings";
        public static final String LOCATIONS = "Locations";
    }

    public static class Ftue
    {
        public static final String TEST_FTUE = "TEST_FTUE";
        public static final String REQUEST_ROBOX_SERIAL_NUMBER = "Request Robox Serial Number";
        public static final String ROBOX_SERIAL_NUMBER = "Robox Serial Number";
        public static final String ROBOX_SERIAL_NUMBER_TIMEOUT = "Robox Serial Number TimeOut";
        public static final String FTUE_STARTED = "Ftue Started";
        public static final String POWER_MANAGMENT = "Power Managment";
        public static final String CHARGING = "Charging";
        public static final String READY_FOR_ACTIVATION = "Ready For FTUE";
        public static final String ACTIVATION = "FTUE";
        public static final String TCSR_CREATED = "TCSR Created";
        public static final String REQUEST_SERVER_POST_TCSR = "Request Sever To Post TCSR";
        public static final String TCSR_POSTED_ON_SERVER = "TCSR Posted On Server";
        public static final String QR_CODE_CREATED = "QR Code Created";
        public static final String QR_CODE_DISPLAYED = "QR Code Displayed";
        public static final String REQUEST_SERVER_TID = "Request Sever To Get TiD";
        public static final String TiD = "TiD";
        public static final String ACCESS_TOKEN_SAVED = "Access Token Saved";
    }
}
