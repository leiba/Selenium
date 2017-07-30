package dp.leiba.social.driver;

import dp.leiba.social.driver.versions.DriverHtml;
import dp.leiba.social.driver.versions.DriverWeb;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Driver.
 */
public class Driver
{

    /**
     * Driver params
     */
    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    private static final String DRIVER      = "X:\\social\\chromedriver.exe";

    /**
      * Get driver.
      *
      * @return WebDriver.
      */
    public static WebDriver getDriver()
    {
        return getDriver(null);
    }

            /**
      * Get driver.
      *
      * @param url Url.
      *
      * @return WebDriver.
      */
    public static WebDriver getDriver(String url)
    {
        return getDriver(url, null, null);
    }
    
    /**
     * Get driver.
     *
     * @param url  Url.
     * @param host Host.
     * @param port Port.
     *
     * @return WebDriver.
     */
    public static WebDriver getDriver(String url, String host, String port)
    {
        System.setProperty(DRIVER_NAME, DRIVER);

        Capabilities cap = _getCap(host, port);
        WebDriver driver;

        if (true) {
            driver = new DriverWeb(cap);
        } else {
            driver = new DriverHtml(cap);
        }

        if (url != null) {
            driver.navigate().to(url);
        }

        return driver;
    }

    /**
     * Get capabilities.
     *
     * @param host Host.
     * @param port Port.
     *
     * @return Capabilities.
     */
    private static Capabilities _getCap(String host, String port)
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        Proxy proxy = new Proxy();
        String part;

        if (host != null & port != null) {
            part = host + ":" + port;

            proxy.setHttpProxy(part).setFtpProxy(part).setSslProxy(part);
            proxy.setProxyType(Proxy.ProxyType.MANUAL);
            proxy.setNoProxy("");

            cap.setCapability(CapabilityType.PROXY, proxy);
        }

        cap.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        return cap;
    }
}
