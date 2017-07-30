package dp.leiba.social.tools;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Actions.
 */
public class Actions
{

    public static final int SECOND = 1000;

    /**
     * Do print.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     * @param text     Text.
     *
     * @throws Exception
     */
    public static void doPrint(WebDriver driver, String selector, String text)
        throws Exception
    {
        doWait(driver, selector);
        WebElement el = driver.findElement(By.cssSelector(selector));

        for (char c : text.toCharArray()) {
            el.sendKeys(c + "");
            Thread.sleep(Maths.getRandInput());
        }

        Thread.sleep(Maths.getRandInput());
    }

    /**
     * Do print.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     * @param text     Text.
     *
     * @throws Exception
     */
    public static void doPrintNow(WebDriver driver, String selector, String text)
        throws Exception
    {
        doWait(driver, selector);
        driver.findElement(By.cssSelector(selector)).sendKeys(text);
    }

    /**
     * Do clear.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     *
     * @throws Exception
     */
    public static void doClear(WebDriver driver, String selector)
        throws Exception
    {
        doWait(driver, selector);
        driver.findElement(By.cssSelector(selector)).clear();
    }

    /**
     * Do print.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     * @param key      Key.
     *
     * @throws Exception
     */
    public static void doPrint(WebDriver driver, String selector, Keys key)
            throws Exception
    {
        doWait(driver, selector);
        driver.findElement(By.cssSelector(selector)).sendKeys(key);
    }

    /**
     * Do print im.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     * @param text     Text.
     *
     * @throws Exception
     */
    public static void doPrintIm(WebDriver driver, String selector, String text)
        throws Exception
    {
        doWaitExists(driver, selector);
        driver.findElement(By.cssSelector(selector)).sendKeys(text);
    }

    /**
     * Do print im.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     * @param text     Text.
     *
     * @throws Exception
     */
    public static void doPrintJs(WebDriver driver, String selector, String text)
            throws Exception
    {
        doWaitExists(driver, selector);

        System.out.println("$(\"" + selector + "\").text(\"" + text + "\");");
        ((JavascriptExecutor) driver).executeScript(
            "$(\"" + selector + "\").text(\"" + text + "\");",
            driver.findElement(By.cssSelector(selector))
        );
    }

    /**
     * Do click.
     *
     * @param driver   Driver.
     * @param selector Selector.
     *
     * @throws Exception
     */
    public static void doClick(WebDriver driver, String selector)
        throws Exception
    {
        doWait(driver, selector);
        driver.findElement(By.cssSelector(selector)).click();
    }

    /**
     * Do click.
     *
     * @param driver  Driver.
     * @param element Element.
     *
     * @throws Exception
     */
    public static void doClick(WebDriver driver, WebElement element)
            throws Exception
    {
        element.click();
    }

    /**
     * Do click js.
     *
     * @param driver   Driver.
     * @param selector Selector.
     *
     * @throws Exception
     */
    public static void doClickJS(WebDriver driver, String selector)
        throws Exception
    {
        doWaitExists(driver, selector);

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            driver.findElement(By.cssSelector(selector))
        );
    }

    /**
     * Do file.
     *
     * @param file File path.
     *
     * @throws Exception
     */
    public static void doFile(String file)
        throws Exception
    {
        Thread.sleep(3000);

        StringSelection ss = new StringSelection(file);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * Do get text.
     *
     * @param driver   Web driver.
     * @param selector Selector.
     *
     * @return Text.
     *
     * @throws Exception
     */
    public static String doText(WebDriver driver, String selector)
        throws Exception
    {
        try {
            return driver.findElement(By.cssSelector(selector)).getText();
        } catch (Exception e) {}

        return null;
    }

    /**
     * Do scroll.
     *
     * @param driver Driver.
     */
    public static void doScroll(WebDriver driver)
    {
        ((JavascriptExecutor) driver).executeScript(
            "window.scrollTo(0, document.body.scrollHeight);"
        );
    }

    /**
     * Do js.
     *
     * @param driver Web driver.
     * @param js     JavaScript.
     */
    public static void doJS(WebDriver driver, String js)
    {
        ((JavascriptExecutor) driver).executeScript(js);
    }

    /**
     * Do js return.
     *
     * @param driver Web driver.
     * @param js     JavaScript.
     *
     * @return String.
     */
    public static String doJSReturn(WebDriver driver, String js)
    {
        return (String) ((JavascriptExecutor) driver).executeScript(
            "return " + js
        );
    }


    /**
     * Sleep thread.
     *
     * @param secs Seconds.
     *
     * @throws Exception
     */
    public static void doSleep(int secs)
        throws Exception
    {
        if (secs > 0) {
            Thread.sleep(secs * SECOND);
        }
    }

    /**
     * Sleep thread.
     *
     * @param maxSecs Max seconds.
     *
     * @throws Exception
     */
    public static void doSleepRand(int maxSecs)
        throws Exception
    {
        Thread.sleep(Maths.getRand(0, maxSecs) * SECOND);
    }

    /**
     * Get value.
     *
     * @param driver   Driver.
     * @param selector Selector.
     *
     * @return Value.
     */
    public static String doGetVal(WebDriver driver, String selector)
    {
        return driver.findElement(By.cssSelector(selector)).getAttribute("value");
    }

    /**
     * Do ajax.
     *
     * @param driver Driver.
     * @param url    Url.
     *
     * @return Result.
     *
     * @throws Exception
     */
    public static String doAjax(WebDriver driver, String url)
        throws Exception
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (String) js.executeScript("" +
            "return (function() {xhr = new XMLHttpRequest();" +
            "xhr.open('GET', '" + url + "', false);" +
            "xhr.send();" +
            "return xhr.responseText;" +
            "})()"
        );
    }

    /**
     * Do ajax post.
     *
     * @param driver  Driver.
     * @param url     Url.
     * @param body    Body.
     * @param headers Headers.
     *
     * @return Result.
     *
     * @throws Exception
     */
    public static String doAjaxPost(WebDriver driver, String url, String body, HashMap<String, String> headers)
        throws Exception
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String rHeaders       = "";
        body                  = body.replace("\n", "").replace("\r", "");

        for (String key : headers.keySet()) {
            rHeaders += String.format(
                "xhr.setRequestHeader('%1$s', %2$s);",
                key,
                headers.get(key)
            );
        }

        return (String) js.executeScript(String.format("" +
            "return (function() {var xhr = new XMLHttpRequest();" +
            "xhr.withCredentials = true;" +
            "xhr.open(\"POST\", '%1$s', false);" +
            "%2$s" +
            "xhr.send('%3$s');" +
            "return xhr.responseText;" +
            "})()",
                url,
                rHeaders,
                body
        ));

    }

    /**
     * Wait for element.
     *
     * @param driver   Driver.
     * @param selector Selector.
     *
     * @throws Exception
     */
    public static void doWait(WebDriver driver, String selector)
        throws Exception
    {
        doWait(driver, selector, 60);
    }

    /**
     * Wait for element.
     *
     * @param driver   Driver.
     * @param selector Selector.
     * @param secs     Seconds.
     *
     * @throws Exception
     */
    public static void doWait(WebDriver driver, String selector, int secs)
            throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, secs);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    /**
     * Wait for element.
     *
     * @param driver   Driver.
     * @param selector Selector.
     *
     * @throws Exception
     */
    public static void doWaitExists(WebDriver driver, String selector)
        throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }
}
