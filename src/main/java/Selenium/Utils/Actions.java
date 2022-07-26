package Selenium.Utils;

import Utils.AllureUtils;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import utils.AllureUtils;

import java.util.List;
import java.util.Set;

/**
 *
 */
public class Actions extends org.openqa.selenium.interactions.Actions {
    private static final Logger LOGGER = LogManager.getLogger();

    private final WebDriver driver;
    private static final int TIMEOUT_SEC = 30;

    public Actions(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * @desctiption wait until element will be visible
     */
    public void waitForElementToBeVisible(WebElement el) {
        writeToLog(">>>>waitForElementToBeVisible");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until(ExpectedConditions.visibilityOf(el));
        writeToLog("<<<<waitForElementToBeVisible");
    }

    /**
     * @desctiption wait until element will be visible
     */
    public void waitForElementToBeInvisible(WebElement el) {
        writeToLog(">>>>waitForElementToBeInvisible");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until(ExpectedConditions.invisibilityOf(el));
        writeToLog("<<<<waitForElementToBeInvisible");
    }

    /**
     * @desctiption wait until text will be present and show ton element
     */
    public void waitForTextToBePresentOnElement(final WebElement el, final String txt) {
        writeToLog(">>>>waitForTextToBePresentOnElement");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until( d -> {
            writeToLog("<<<<waitForTextToBePresentOnElement");
            return el.getText().equals(txt);
        });
    }

    /**
     * @desctiption wait until element will be clickable
     */
    public void waitForElementToBeClickable(WebElement el) {
        writeToLog(">>>>waitForElementToBeClickable");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until(ExpectedConditions.elementToBeClickable(el));
        writeToLog("<<<<waitForElementToBeClickable");
    }

    /**
     * @desctiption return existing text on an element
     */
    public String getText(WebElement el) {
        writeToLog(">>>>getText");
        highlightElement(el, "orange");
        writeToLog("<<<<getText");
        return el.getText();
    }
    @Step("Clicking Main Windows Scroll Up")
    public void clickScrollUp(WebDriver driver) {
        AllureUtils.reportToLog4jAndAllure("Clicking scroll Up", LOGGER);
        WebElement ScrollUp = driver.findElement(new By.ByName("Line Up"));
        org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
        for (int i = 1; i <= 45; ++i)
            act.dragAndDropBy(ScrollUp, 0, 1000).build().perform();
    }

    @Step("Clicking Main Windows Scroll Down")
    public void clickScrollDown() {
        AllureUtils.reportToLog4jAndAllure("Clicking scroll Down", LOGGER);
        WebElement ScrollDown = driver.findElement(new By.ByName("Line Down"));
        org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
        //WebDriverWait wait = new WebDriverWait(getApplication().getDriver(), 10);
        //WebElement electronics = wait.until(ExpectedConditions.elementToBeClickable(By.name("Line Down")));
        for (int i = 1; i <= 45; ++i)
            act.dragAndDropBy(ScrollDown, 0, 1000).build().perform();
    }
    public WebElement getElementInListByValue(final List<WebElement> list, String value) {
        writeToLog(">>>>getElementInListByValue");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until((ExpectedCondition<Boolean>) d -> {
            writeToLog("<<<<waitForTextNotToBeEmpty");
            return list.size() != 0 && list.get(0).getText().length() > 0;
        });

        for (WebElement el : list) {
            if (el.getText().equalsIgnoreCase(value)) {
                writeToLog("<<<<getElementInListByValue**element**");
                return el;
            }

        }
        writeToLog("<<<<getElementInListByValue**null**");
        return null;
    }

    /**
     * @desctiption waits until the top progress bar will reach to a specific value(usually check that a page is finish loads)
     */
    public void waitForProgressBarWidth(final String width, final WebElement progressBar) {
        writeToLog(">>>>waitForProgressBarWidth");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until((ExpectedCondition<Boolean>) d -> {
            writeToLog("ProgressBarWidth: " + progressBar.getAttribute("style").toString());
            writeToLog("<<<<waitForProgressBarWidth");
            return progressBar.getAttribute("style").toString().equalsIgnoreCase("width: " + width + "%;");
        });
    }

    /**
     * @desctiption waits until an element is fixed and not in motion(usually check that a page is finish loads)
     */
    public void waitForElementToBeFixAtLocation(final WebElement el) {
        writeToLog(">>>>waitForElementToBeAtLocation");
        waitForElementToBeVisible(el);
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SEC);
        wait.until((ExpectedCondition<Boolean>) d -> {
            int x = el.getLocation().getX();
            int y = el.getLocation().getY();
            writeToLog("X: " + x);
            writeToLog("Y: " + y);
            if (el.getLocation().getX() == x && el.getLocation().getY() == y)
                return true;
            return false;
        });
        writeToLog("<<<<waitForElementToBeAtLocation");
    }

    /**
     * @desctiption moves between windows
     */
    public void moveBetweenHandels(String parentHandel) {
        writeToLog(">>>>moveBetweenHandels");
        Set<String> handels = driver.getWindowHandles();
        for (String h : handels) {
            if (!h.equals(parentHandel)) {
                switchToWindow(h);
                writeToLog("<<<<moveBetweenHandels");
                return;
            }
        }
    }

    /**
     * @desctiption does the switch action
     */
    public void switchToWindow(String handle) {
        writeToLog(">>>>switchToWindow");
        driver.switchTo().window(handle);
        writeToLog("<<<<switchToWindow");
    }

    /**
     * @desctiption choose a drop down element by value
     */
    public void chooseSelectElementByValue(WebElement el, String value) {
        writeToLog(">>>>chooseSelectElementByValue");
        Select select = new Select(el);
        select.selectByValue(value);
        writeToLog("<<<<chooseSelectElementByValue");
    }

    /**
     * @desctiption choose a drop down element by index
     */
    public void chooseSelectElementByIndex(WebElement el, int index) {
        writeToLog(">>>>chooseSelectElementByIndex");
        Select select = new Select(el);
        select.selectByIndex(index);
        writeToLog("<<<<chooseSelectElementByIndex");
    }

    /**
     * @desctiption close the current open tab
     */
    public void closeCurrentTab() {
        writeToLog(">>>>closeCurrentTab");
        driver.close();
        writeToLog("<<<<closeCurrentTab");
    }

    /**
     * @desctiption moves element via X and Y
     */
    public void dragAndDropByXY(WebElement el, int x, int y) {
        writeToLog(">>>>dragAndDropByXY");
        Actions act = new Actions(driver);
        act.dragAndDropBy(el, x, y).release().perform();
        highlightElement(el, "blue");
        writeToLog("<<<<dragAndDropByXY");
    }



    /**
     * @desctiption highlight an element
     */
    public void highlightElement(WebElement element, String color) {
        writeToLog(">>>>highlightElement");
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "border: 2px solid " + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few miliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
        writeToLog("<<<<highlightElement");

    }

    /**
     * @desctiption writing to allure log
     */
    @Step("will print{0}")
    public void writeToLog(String msg) {
        LOGGER.info(msg);
        //Allure.attachment("Message", msg);
    }

    public static void waitForJS(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until((ExpectedCondition<Boolean>)
                wd -> ((JavascriptExecutor) wd).
                        executeScript("return document.readyState").equals("complete"));
    }

    public static void waitForJquery(WebDriver driver) throws InterruptedException {
        int count = 0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if ((boolean) js.executeScript("return window.jQuery != undefined")) {
            while (!(Boolean) js.executeScript("return jQuery.active == 0")) {
                Thread.sleep(4000);
                if (count > 4) break;
                count++;
            }
        }
    }

    public static boolean waitForJStoLoad(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            @Override

            public Boolean apply(WebDriver driver) {

                try {

                    return ((long) js.executeScript("return jQuery.active")) == 0;

                } catch (Exception e) {

                    return true;

                }

            }

        };

        // wait for Javascript to load

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            @Override

            public Boolean apply(WebDriver driver) {

                return js.executeScript("return document.readyState")

                        .toString().equals("complete");

            }

        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);

    }

    public void waitAndClick(By by, WebDriver driver, int maxSecDelta) {

        for (int i = 0; i < maxSecDelta; i++) {
            try {
                explicitWaitElementToBeClickable(by, 1);
                WebElement element = driver.findElement(by);
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
//                ExplicitWaiting.waitInSeconds(1);
            }
        }
    }


    /*Select using WebElements*/

    /**
     * To Wait Until Element to be Clickable
     *
     * @param element
     * @param time
     */
    public void explicitWaitElementToBeClickable(WebElement element, int time) {
        WebDriverWait clickableWait = new WebDriverWait(driver, time);
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * To Wait Until Element to be Selectable
     *
     * @param element
     * @param time
     */
    public void explicitWaitElementToBeSelected(WebElement element, int time) {

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
    public void explicitWaitTextToBePresentInElement(WebElement element, int time, String text) {

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
    public void explicitWaitTitleContains(WebElement element, int time, String title) {

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
    public void explicitWaitTitleIs(WebElement element, int time, String title) {
        WebDriverWait titleIs = new WebDriverWait(driver, time);
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /**
     * To Wait Until Element to be Visible
     *
     * @param element
     * @param time
     */
    public void explicitWaitVisibilityOfElement(WebElement element, int time) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To Wait Until Element to be Visible
     *
     * @param element
     * @param time
     */
    public void explicitWaitInvisibilityOfElement(WebElement element, int time) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * @param element
     * @param time
     */
    public void explicitWaitInvisibilityOfElement(By element, int time) {
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
    public void explicitWaitSelectionStateToBe(WebElement element, int time, boolean selected) {
        WebDriverWait elementIsSelected = new WebDriverWait(driver, time);
        elementIsSelected.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }


    /**
     * To Wait Until Elements to be Visible
     *
     * @param element
     * @param time
     */
    public void explicitWaitVisibilityOfElements(List<WebElement> element, int time) {
        WebDriverWait elementsToBeVisible = new WebDriverWait(driver, time);
        elementsToBeVisible.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    /*Select using By Method*/

    /**
     * To Wait Until Element to be Clickable
     *
     * @param element
     * @param time
     */
    public void explicitWaitElementToBeClickable(By element, int time) {
        WebDriverWait clickableWait = new WebDriverWait(driver, time);
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * To Wait Until Element to be Selectable
     *
     * @param element
     * @param time
     */
    public void explicitWaitElementToBeSelected(By element, int time) {
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
    public void explicitWaitTitleContains(By element, int time, String title) {
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
    public void explicitWaitTitleIs(By element, int time, String title) {
        WebDriverWait titleIs = new WebDriverWait(driver, time);
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /**
     * To Wait Until Element to be Visible
     *
     * @param element
     * @param time
     */
    public void explicitWaitVisibilityOfElement(By element, int time) {
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
    public void explicitWaitSelectionStateToBe(By element, int time, boolean selected) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, time);
        elementToBeVisible.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }


    /**
     * To Wait for the Alert
     *
     * @param time
     */
    public void explicitWaitForAlert(int time) {
        WebDriverWait waitForAlert = new WebDriverWait(driver, time);
        waitForAlert.until(ExpectedConditions.alertIsPresent());
    }


    /**
     * @param numberOfSeconds
     */
    public void waitInSeconds(int numberOfSeconds) {
        LOGGER.debug("Waiting for " + numberOfSeconds + " seconds...");
        for (int i = 1; i <= numberOfSeconds; ++i)
            try {
                if (i % 5 == 0) {
                    LOGGER.debug("Waiting for " + i + " seconds...");
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
    }


}
