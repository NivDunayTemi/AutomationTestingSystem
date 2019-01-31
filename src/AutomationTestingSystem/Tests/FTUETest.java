package AutomationTestingSystem.Tests;
import AutomationTestingSystem.FTUE.FTUE;
import AutomationTestingSystem.Constants.LoggerConstants;
import AutomationTestingSystem.Tester;
import AutomationTestingSystem.Utils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FTUETest
{
    private static Tester tester;
    private static FTUE FTUETest;
    private static Utils utils;
    private static String testName;

    @BeforeClass
    public static void init() throws Exception {
        testName = LoggerConstants.Ftue.TEST_FTUE;
        tester = new Tester();
        tester.Initialize("10.10.110.83:5555", testName, false);
        utils = tester.Utils();
        FTUETest = new FTUE(utils);
    }

    @Rule public TestName methodTestName = new TestName();

    @Test
    public void Test01_CheckFTUEStart()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.FTUE_STARTED, LoggerConstants.Status.OK));
    }

    @Test
    public void Test02_CheckRoboxSerialNumber()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLogRequestAndResponse(methodTestName.getMethodName(),
                LoggerConstants.Ftue.REQUEST_ROBOX_SERIAL_NUMBER,
                LoggerConstants.Status.OK, LoggerConstants.Ftue.ROBOX_SERIAL_NUMBER, LoggerConstants.Status.OK));
    }

    @Test
    public void Test03_CheckHelloScreen()
    {
        Assert.assertEquals(true, FTUETest.CheckStartScreen()); //todo check later if we want every operation seperatly
    }

    @Test
    public void Test04_CheckCharging()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.CHARGING, LoggerConstants.Status.OK));
    }

    @Test
    public void Test05_CheckChargingScreen()
    {
        Assert.assertEquals(true, FTUETest.CheckChargingScreen());
    }

    @Test
    public void Test06_CheckReadyToActivateScreen()
    {
        Assert.assertEquals(true, FTUETest.ClickReadyToActiveButton()); //todo check later if we want every operation seperatly
    }

    @Test
    public void Test07_CheckTCSRCreated()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.TCSR_CREATED, LoggerConstants.Status.OK));
    }

    @Test
    public void Test08_CheckTCSRRequest()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.REQUEST_SERVER_POST_TCSR, LoggerConstants.Status.OK));
    }

    @Test
    public void Test09_CheckTCSRPosted()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.TCSR_POSTED_ON_SERVER, LoggerConstants.Status.OK));
    }

    @Test
    public void Test10_CheckCreatingQRCode()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.QR_CODE_DISPLAYED, LoggerConstants.Status.OK));
    }

    @Test
    public void Test11_CheckQRCodeDisplayed()
    {
        Assert.assertEquals(true, FTUETest.CheckActivationScreen());
    }

    @Test
    public void Test12_CheckPollingTiDStarted()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.REQUEST_SERVER_TID, LoggerConstants.Status.OK));
    }

    @Test
    public void Test13_CheckTiD()
    {
        Assert.assertEquals(true, utils.CheckTestCaseByLog(methodTestName.getMethodName(),
                LoggerConstants.Ftue.TiD, LoggerConstants.Status.OK));
    }

}
