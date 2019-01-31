package AutomationTestingSystem;

import AutomationTestingSystem.Constants.UtilsConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

public class Utils
{

    private AndroidDriver           androidDriver;
    private String                  filePath = UtilsConstants.FILE_PATH;
    private Logger                  failedLogger = Logger.getLogger(UtilsConstants.FILE_PATH);
    private Logger                  succeededLogger = Logger.getLogger(UtilsConstants.SUCCESS_LOG);
    private String                  launcherLogs;
    private Gson                    gson = new Gson();
    private String                  testName;

    static
    {
        System.setProperty(UtilsConstants.LOGGING_FORMAT,
                UtilsConstants.LOGGING_FORMAT_VALUE);
    }

    public Utils(DesiredCapabilities desiredCapabilities, String testName)
    {
        try
        {
            androidDriver = new AndroidDriver(new URL(UtilsConstants.APPIUM_URL), desiredCapabilities);
            createFolderForLogs();
            this.testName = testName;
            createLogs(testName);
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void createLogs(String testName)
    {
        try
        {
            failedLogger = Logger.getLogger(testName + UtilsConstants.FAIL_LOG);
            succeededLogger = Logger.getLogger(testName + UtilsConstants.SUCCESS_LOG);

            FileHandler FailedFH = new FileHandler(filePath + UtilsConstants.FAIL_TEST_FILE_NAME);
            FileHandler SucceededFH = new FileHandler(filePath + UtilsConstants.SUCCESS_TEST_FILE_NAME);

            SimpleFormatter formatter = new SimpleFormatter();

            failedLogger.addHandler(FailedFH);
            succeededLogger.addHandler(SucceededFH);

            FailedFH.setFormatter(formatter);
            SucceededFH.setFormatter(formatter);

            failedLogger.info( UtilsConstants.INDETATION + testName + UtilsConstants.FAIL_LOGGER_TITLE);
            succeededLogger.info(UtilsConstants.INDETATION + testName + UtilsConstants.SUCCESS_LOGGER_TITLE);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void createFolderForLogs()
    {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(UtilsConstants.DATE_FORMAT);
        String time = dateFormat.format(now);

        filePath = filePath + time + UtilsConstants.BACK_SLASH;
        File dir = new File(time);
        dir.mkdir();
    }

    public String WaitTillLogAppearInLogcat(String logContent, int timeLimitInSeconds)
    {
        String log = null;
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        while(log == null && ((Duration.between(startTime, endTime).toMillis() / UtilsConstants.SECOND) < timeLimitInSeconds))
        {
            CollectLogs();
            log = GetLogLineFromCollectedLogs(logContent);
            endTime = LocalDateTime.now();
        }

        return log;
    }

    public boolean WaitTillVisibilityOfElement(int timeLimitInSeconds, String elementXPath)
    {
        boolean isElementPresent = false;
        WebElement webElement;

        try
        {
            WebDriverWait wait = new WebDriverWait(androidDriver, timeLimitInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXPath)));
            webElement = androidDriver.findElement(By.xpath(elementXPath));
            isElementPresent = webElement.isDisplayed();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage() + " " + elementXPath);
        }
        finally
        {
            return isElementPresent;
        }
    }

    public void CollectLogs()
    {
        List<LogEntry> entireLogBuffer = androidDriver.manage().logs().get(UtilsConstants.LOGCAT).getAll();
        for(LogEntry LogLine : entireLogBuffer)
        {
            launcherLogs += LogLine.getMessage();
            launcherLogs += System.lineSeparator();
        }
    }

    public String GetLogs()
    {
        return launcherLogs;
    }

    public String GetLogLineFromCollectedLogs(String logContent) {
        String LogInCollectedLogs = null;

        String[] lines = launcherLogs.split(System.lineSeparator());

        for (String line : lines) {
            if (line.contains(logContent)) {
                LogInCollectedLogs = line;
            }
        }

        return LogInCollectedLogs;
    }

    public void WriteLogToFiles(boolean isFunctionSucceeded, String testName)
    {

        if(isFunctionSucceeded == true)
        {
            succeededLogger.info(testName + UtilsConstants.SUCCEEDED);
        }
        else
        {
            CollectLogs();
            failedLogger.info(testName + UtilsConstants.FAILED);
            failedLogger.info( UtilsConstants.LOG_TITLE + launcherLogs + UtilsConstants.INDETATION);
        }
    }

    public String TurnStringToLoggerFormat(String testName , String testCaseName, String status)
    {
        String loggerTest;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(UtilsConstants.CHECK + testCaseName, status);
        loggerTest = testName + ": " + (gson.toJson(jsonObject));
        return loggerTest;
    }

    public boolean CheckTimeOutBetweenLogs(String firstLog, String secondLog, int timeOut) {
        final int firstLogTime = getTimeFromLog(firstLog);
        final int secondLogTime = getTimeFromLog(secondLog);

        return secondLogTime - firstLogTime < timeOut;
    }

    public String CreateTestCaseString(String testName, String bootStarted)
    {
        return testName + UtilsConstants.COLON_WITH_CHECK + bootStarted + "\"";
    }

    public boolean CheckTestCaseByLog(String methodTestName, String testCase, String expectedResult)
    {
        boolean isTestSucceeded = false;

        String expectedLog = WaitTillLogAppearInLogcat(CreateTestCaseString(testName, testCase), UtilsConstants.ONE_HUNDRED_SECONDS);
        if(expectedLog != null) {
            isTestSucceeded = expectedLog.contains(TurnStringToLoggerFormat(testName, testCase, expectedResult));
        }
        WriteLogToFiles(isTestSucceeded, methodTestName);

        return isTestSucceeded;
    }

    public boolean CheckTestCaseByLogRequestAndResponse(String methodTestName, String requestLog, String requestExpectedResultLog,
                                                        String responseLog, String responseExpectedResultLog)
    {
        boolean isTestSucceeded = false;

        String requestLogInLogcat = WaitTillLogAppearInLogcat(CreateTestCaseString(testName, requestLog), UtilsConstants.THIRTY_SECONDS);
        String responseLogInLogcat = WaitTillLogAppearInLogcat(CreateTestCaseString(testName, responseLog), UtilsConstants.THIRTY_SECONDS);
        if(requestLogInLogcat != null && responseLogInLogcat != null) {
            isTestSucceeded = requestLogInLogcat.contains(TurnStringToLoggerFormat(testName, requestLog, requestExpectedResultLog))
                    && responseLogInLogcat.contains(TurnStringToLoggerFormat(testName, responseLog, responseExpectedResultLog))
                    && CheckTimeOutBetweenLogs(requestLogInLogcat, responseLogInLogcat, UtilsConstants.SEVEN_SECONDS);
        }
        WriteLogToFiles(isTestSucceeded, methodTestName);

        return isTestSucceeded;
    }

    private int getTimeFromLog(String log) {
        String subString = log.split(" ")[1];
        return Integer.parseInt(subString
                        .substring(subString.lastIndexOf(":") + 1)
                        .split("\\.")[0]);
    }

    public WebElement FindElementByXPath(String i_ElementXPath)
    {
        WebElement webElement = null;
        try
        {
            webElement = androidDriver.findElement(By.xpath(i_ElementXPath));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            return webElement;
        }
    }

    public List<WebElement> FindElementsByXPath(String i_ElementXPath)
    {
        List<WebElement> webElements = null;
        try
        {
            webElements = androidDriver.findElements(By.xpath(i_ElementXPath));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            return webElements;
        }
    }

    public void TouchAction(int i_X, int i_Y)
    {
        new TouchAction(androidDriver).tap(PointOption.point(i_X, i_Y)).perform();
    }

    public Dimension GetDimensionSize()
    {
        return androidDriver.manage().window().getSize();
    }

    public void Swipe(int i_StartX, int i_StartY, int i_EndX, int i_EndY)
    {
        TouchAction action = new TouchAction (androidDriver);

        action.press (PointOption.point(i_StartX, i_StartY))
                .moveTo(PointOption.point(i_EndX, i_EndY))
                .release ()
                .perform ();
    }

    public int GetNumberOfPages(String i_ElementXPath)
    {
        int numberOfPages = UtilsConstants.ZERO;

        try
        {
            WebElement numberOfPagesElement = FindElementByXPath(i_ElementXPath);
            String numberOfPagesString = numberOfPagesElement.getText().split("/")[1].trim();
            numberOfPages = Integer.parseInt(numberOfPagesString);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            return numberOfPages;
        }
    }
}
