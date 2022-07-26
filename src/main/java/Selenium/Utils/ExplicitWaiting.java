package Selenium.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 *
 */
public class ExplicitWaiting {
    public static WebDriver driver;
    public static JavascriptExecutor js = (JavascriptExecutor) driver;

    /*Select using WebElements*/

    /**
     * To Wait Until Element to be Clickable
     *
     * @param element
     * @param time
     * @param driver
     */
    public static void explicitWaitElementToBeClickable(WebElement element, int time, WebDriver driver) {
        WebDriverWait clickableWait = new WebDriverWait(driver, time);
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * To Wait Until Element to be Selectable
     *
     * @param element
     * @param time
     */
    public static void explicitWaitElementToBeSelected(WebElement element, int time, WebDriver driver) {

        WebDriverWait selectableWait = new WebDriverWait(driver, time);
        selectableWait.until(ExpectedConditions.elementToBeSelected(element));
    }


    /**
     * To Wait Until Element has the text
     *
     * @param element
     * @param time
     * @param text
     */
    public static void explicitWaitTextToBePresentInElement(WebElement element, int time, String text, WebDriver driver) {

        WebDriverWait textToBePresent = new WebDriverWait(driver, time);
        textToBePresent.until(ExpectedConditions.textToBePresentInElement(element, text));
    }


    /**
     * To Wait Until Title contains the text
     *
     * @param element
     * @param time
     * @param title
     */
    public static void explicitWaitTitleContains(WebElement element, int time, String title, WebDriver driver) {

        WebDriverWait titleContains = new WebDriverWait(driver, time);
        titleContains.until(ExpectedConditions.titleContains(title));
    }


    /**
     * To Wait Until Title is
     *
     * @param element
     * @param time
     * @param title
     */
    public static void explicitWaitTitleIs(WebElement element, int time, String title, WebDriver driver) {
        WebDriverWait titleIs = new WebDriverWait(driver, time);
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /**
     * To Wait Until Element to be Visible
     *
     * @param element
     * @param time
     */
    public static void explicitWaitVisibilityOfElement(WebElement element, int time,WebDriver driver) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To Wait Until Element to be Visible
     *
     * @param element
     * @param time
     * @param driver
     */
    public static void explicitWaitInvisibilityOfElement(WebElement element, int time, WebDriver driver) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * @param element
     * @param time
     * @param driver
     */
    public static void explicitWaitInvisibilityOfElement(By element, int time, WebDriver driver) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
    }


    /**
     * To Wait Until Element is Selected
     *
     * @param element
     * @param time
     * @param selected
     */
    public static void explicitWaitSelectionStateToBe(WebElement element, int time, boolean selected, WebDriver driver) {
        WebDriverWait elementIsSelected = new WebDriverWait(driver, time);
        elementIsSelected.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }


    /**
     * To Wait Until Elements to be Visible
     *
     * @param element
     * @param time
     */
    public static void explicitWaitVisibilityOfElements(List<WebElement> element, int time, WebDriver driver) {
        WebDriverWait elementsToBeVisible = new WebDriverWait(driver, time);
        elementsToBeVisible.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    /*Select using By Method*/

    /**
     * To Wait Until Element to be Clickable
     *
     * @param element
     * @param time
     * @param driver
     */
    public static void explicitWaitElementToBeClickable(By element, int time, WebDriver driver) {
        WebDriverWait clickableWait = new WebDriverWait(driver, time);
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * To Wait Until Element to be Selectable
     *
     * @param element
     * @param time
     */
    public static void explicitWaitElementToBeSelected(By element, int time, WebDriver driver) {
        WebDriverWait selectableWait = new WebDriverWait(driver, time);
        selectableWait.until(ExpectedConditions.elementToBeSelected(element));
    }


    /**
     * To Wait Until Title contains the text
     *
     * @param element
     * @param time
     * @param title
     */
    public static void explicitWaitTitleContains(By element, int time, String title, WebDriver driver) {
        WebDriverWait titleContains = new WebDriverWait(driver, time);
        titleContains.until(ExpectedConditions.titleContains(title));
    }


    /**
     * To Wait Until Title is
     *
     * @param element
     * @param time
     * @param title
     */
    public static void explicitWaitTitleIs(By element, int time, String title, WebDriver driver) {
        WebDriverWait titleIs = new WebDriverWait(driver, time);
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /**
     * To Wait Until Element to be Visible
     *
     * @param element
     * @param time
     * @param driver
     */
    public static void explicitWaitVisibilityOfElement(By element, int time, WebDriver driver) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    /**
     * To Wait Until Element is Selected
     *
     * @param element
     * @param time
     * @param selected
     */
    public static void explicitWaitSelectionStateToBe(By element, int time, boolean selected, WebDriver driver) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }
    

    /**
     * To Wait for the Alert
     *
     * @param time
     */
    public static void explicitWaitForAlert(int time, WebDriver driver) {
        WebDriverWait waitForAlert = new WebDriverWait(driver, time);
        waitForAlert.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * @param numberOfSeconds
     */
    public static void waitInSeconds(int numberOfSeconds) {
        for (int i = 0; i < numberOfSeconds; ++i)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
    }

}
