package AutomationTestingSystem;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListHandler
{
//    void SwipeForward();
//    void SwipeBackward();
//    WebElement FindElementByID(String i_ElementID);
//    List<WebElement> FindElementListByID(String i_ElementsID);
//    void ClickElement(String i_ElementID);

    private Utils m_Utils;
    private int m_X1;
    private int m_X2;
    private int m_Y1;
    private int m_Y2;

    public ListHandler(Utils i_Utils)
    {
        m_Utils = i_Utils;
        calculateDimension();
    }

    private void calculateDimension()
    {
        Dimension size = m_Utils.GetDimensionSize();
        m_X1 = 10;
        m_X2 = size.getWidth() - 10;
        m_Y1 = size.getHeight() / 2;
        m_Y2 = m_Y1;
    }

    public void SwipeForward()
    {
        m_Utils.Swipe(m_X2, m_Y1, m_X1, m_Y2);
    }

    public void SwipeBackward()
    {
        m_Utils.Swipe(m_X1, m_Y1, m_X2, m_Y2);
    }

    public WebElement FindElementByXPath(String i_ElementXPath)
    {
        return m_Utils.FindElementByXPath(i_ElementXPath);
    }

    public List<WebElement> FindElementsListByXPath(String i_ElementsXPath)
    {
        return m_Utils.FindElementsByXPath(i_ElementsXPath);
    }

    public void ClickElement(String i_ElementID)
    {
        WebElement webElement = FindElementByXPath(i_ElementID);
        m_Utils.WaitTillVisibilityOfElement(60, i_ElementID);
        webElement.click();
    }

    public boolean WaitTillVisibilityOfElement(int i_TimeInSeconds, String i_ElementID)
    {
        return m_Utils.WaitTillVisibilityOfElement(i_TimeInSeconds, i_ElementID);
    }
}
