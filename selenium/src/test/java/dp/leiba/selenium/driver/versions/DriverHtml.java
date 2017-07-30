package dp.leiba.selenium.driver.versions;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * DriverHtml.
 */
public class DriverHtml extends HtmlUnitDriver
{

    /**
     * DriverHtml.
     *
     * @param cap Capabilities.
     */
    public DriverHtml(Capabilities cap)
    {
        super(cap);
        getWebClient().getOptions().setThrowExceptionOnScriptError(false);
        getWebClient().getOptions().setThrowExceptionOnFailingStatusCode(false);
    }

}
