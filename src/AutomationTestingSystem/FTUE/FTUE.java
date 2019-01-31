package AutomationTestingSystem.FTUE;
import AutomationTestingSystem.Constants.XPathConstants;
import AutomationTestingSystem.Utils;
import org.openqa.selenium.WebElement;

public class FTUE
{
    private Utils m_Utils;

    public FTUE(Utils i_Utils)
    {
        m_Utils = i_Utils;
    }

    public boolean CheckStartScreen()
    {
        boolean isStartScreenOK = true;
        WebElement helloLabel = m_Utils.FindElementByXPath(XPathConstants.StartScreen.HELLO_LABEL);
        WebElement startButton = m_Utils.FindElementByXPath(XPathConstants.StartScreen.START_BUTTON);
        isStartScreenOK = isStartScreenOK && helloLabel.getText().equals("HELLO");
        isStartScreenOK = isStartScreenOK && startButton.getText().equals("Start");
        //checkLocations();

        return isStartScreenOK;
    }

    public boolean ClickStartButton()
    {
        boolean isFunctionSucceeded = m_Utils.WaitTillVisibilityOfElement(30, XPathConstants.StartScreen.START_BUTTON);
        isFunctionSucceeded =
                isFunctionSucceeded &&
                m_Utils.WaitTillVisibilityOfElement(30, XPathConstants.StartScreen.HELLO_LABEL);

        if(isFunctionSucceeded)
        {
            WebElement startButton = m_Utils.FindElementByXPath(XPathConstants.StartScreen.START_BUTTON);
            startButton.click();
        }

        isFunctionSucceeded =
                isFunctionSucceeded &&
                m_Utils.WaitTillVisibilityOfElement(30, XPathConstants.DockingScreen.WAITING_FOR_DOCKING_LABEL);

        m_Utils.WriteLogToFiles(isFunctionSucceeded, "ClickStartButton");

        return isFunctionSucceeded;
    }

    public boolean ClickCoordinateToAvoidDockingScreen()
    {
        boolean isFunctionSucceeded = clickCoordinateToAvoidDocking(XPathConstants.DockingScreen.POWER_SKIP_BUTTON);
        m_Utils.WriteLogToFiles(isFunctionSucceeded, "ClickCoordinateToAvoidDockingScreen");

        return isFunctionSucceeded;
    }

    public boolean ClickCoordinateToAvoidActivationScreen()
    {
        boolean isFunctionSucceeded = clickCoordinateToAvoidDocking(XPathConstants.ReadyToActiveScreen.READY_TO_ACTIVE_BUTTON);
        m_Utils.WriteLogToFiles(isFunctionSucceeded, "ClickCoordinateToAvoidActivationScreen");

        return isFunctionSucceeded;
    }

    private boolean clickCoordinateToAvoidDocking(String i_ElementID)
    {
        boolean isFunctionSucceeded = m_Utils.WaitTillVisibilityOfElement(30, i_ElementID);
        m_Utils.TouchAction(50, 50);

        return isFunctionSucceeded;
    }

    public boolean ClickReadyToActiveButton()
    {
        boolean isFunctionSucceeded = m_Utils.WaitTillVisibilityOfElement(30, XPathConstants.ReadyToActiveScreen.READY_TO_ACTIVE_BUTTON);
        WebElement readyToActiveButton = m_Utils.FindElementByXPath(XPathConstants.ReadyToActiveScreen.READY_TO_ACTIVE_BUTTON);
        readyToActiveButton.click();
        m_Utils.WriteLogToFiles(isFunctionSucceeded, "ClickReadyToActiveButton");

        return isFunctionSucceeded;
    }

        public boolean CheckActivationScreen() {
        boolean isFunctionSucceeded = m_Utils.WaitTillVisibilityOfElement(30, XPathConstants.ActivationScreen.QR_CODE);

        m_Utils.WriteLogToFiles(isFunctionSucceeded, "CheckChargingScreen");

        return isFunctionSucceeded;
    }

    public boolean CheckChargingScreen()
    {
        boolean isFunctionSucceeded = m_Utils.WaitTillVisibilityOfElement(30, XPathConstants.DockingScreen.CHARGING_LABEL);

        if(isFunctionSucceeded)
        {
            WebElement chargingLabel = m_Utils.FindElementByXPath(XPathConstants.DockingScreen.CHARGING_LABEL);
            isFunctionSucceeded = isFunctionSucceeded &&
                    chargingLabel.getText().equals("Charging");
        }

        m_Utils.WriteLogToFiles(isFunctionSucceeded, "CheckChargingScreen");

        return isFunctionSucceeded;
    }
}
