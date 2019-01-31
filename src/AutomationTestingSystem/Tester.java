package AutomationTestingSystem;
import AutomationTestingSystem.Constants.TesterConstants;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Tester
{
    private DesiredCapabilities  desiredCapabilities;
    private Utils                utils;

    public void Initialize(String robotWifi, String testName, Boolean dontClearAppData)
    {
        setDesiredCapabilities(robotWifi, dontClearAppData);
        utils = new Utils(desiredCapabilities, testName);
    }

    private void setDesiredCapabilities(String robotWifi, Boolean dontClearAppData)
    {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(TesterConstants.DEVICE_NAME, robotWifi);
        desiredCapabilities.setCapability(TesterConstants.UD_ID, robotWifi);
        desiredCapabilities.setCapability(TesterConstants.PLATFORM_NAME, TesterConstants.ANDROID);
        desiredCapabilities.setCapability(TesterConstants.PLATFORM_VERSION, TesterConstants.PLATFORM_VERSION_NUMBER);
        desiredCapabilities.setCapability(TesterConstants.APP_PACKAGE, TesterConstants.APP_PACKAGE_NAME);
        desiredCapabilities.setCapability(TesterConstants.APP_ACTIVITY, TesterConstants.APP_ACTIVITY_NAME);
        desiredCapabilities.setCapability(TesterConstants.NO_RESET_APP, dontClearAppData);
        desiredCapabilities.setCapability(TesterConstants.CLEAR_DEVICE_LOGS_ON_START, true);
    }

    public Utils Utils()
    {
        return utils;
    }

}
