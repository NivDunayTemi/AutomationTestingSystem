package AutomationTestingSystem.Tests;
import AutomationTestingSystem.Constants.LoggerConstants;
import AutomationTestingSystem.Tester;
import AutomationTestingSystem.Utils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import java.time.Duration;
import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BootingTest {
    private static Tester tester;
    private static Utils utils;
    private static String testName;
    private static LocalDateTime startBootingTime;

    @BeforeClass
    public static void init() throws Exception {
        testName = LoggerConstants.Boot.TEST_BOOT;
        tester = new Tester();
        tester.Initialize("10.10.120.176:5555", testName, true);
        utils = tester.Utils();
        startBootingTime = LocalDateTime.now();
    }

    @Rule public TestName methodTestName = new TestName();

    @Test
    public void Test01_BootStarted()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.BOOT_STARTED, LoggerConstants.Status.OK));
    }

    @Test
    public void Test02_CheckInternetConnection()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.INTERNET_CONNECTION, LoggerConstants.Status.OK));
    }


    @Test
    public void Test03_CheckMQTTConnection()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.MQTT_CONNECTION, LoggerConstants.Status.OK));
    }

//    @Test
//    public void Test04_CheckRoboxIsReady()
//    {
//        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.IS_READY, LoggerConstants.Status.OK));
//    }
//
//    @Test
//    public void Test05_RoboxOSVersion()
//    {
//        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.ROBOX_OS, LoggerConstants.Status.OK));
//    }
//
//    @Test
//    public void Test06_DisplayWUGenerator()
//    {
//        Assert.assertEquals(true, utils.CheckTestCaseByLogRequestAndResponse(methodTestName.getMethodName(), LoggerConstants.Boot.REQUEST_WAKEUP_LAYER_TO_DISPLAY,
//                                            LoggerConstants.Status.OK, LoggerConstants.Boot.WAKEUP_DISPLAYED, LoggerConstants.Status.OK));
//    }

    @Test
    public void Test07_CheckLocations()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.LOCATIONS, LoggerConstants.Status.OK));
    }

    @Test
    public void Test08_CheckBootEndedSuccessfully()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(), LoggerConstants.Boot.BOOT_ENDED, LoggerConstants.Status.SUCCESS));
    }

    @Test
    public void Test09_CheckRoboxGreeting()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLogRequestAndResponse(methodTestName.getMethodName(), LoggerConstants.Boot.REQUEST_GREETINGS,
                                            LoggerConstants.Status.START, LoggerConstants.Boot.GREETINGS, LoggerConstants.Status.OK));
    }

    @Test
    public void Test10_CheckBootingTime()
    {
        LocalDateTime endBootingTime = LocalDateTime.now();
        int timeLimitInSeconds = 60;
        System.out.println("Boot time is: " + Duration.between(startBootingTime, endBootingTime).toMillis() / 1000 + " seconds");
        Assert.assertEquals(true, (Duration.between(startBootingTime, endBootingTime).toMillis() / 1000) < timeLimitInSeconds);
    }
}
