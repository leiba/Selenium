package dp.leiba.social.driver.versions;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gargoylesoftware.htmlunit.javascript.host.Location;
import com.gargoylesoftware.htmlunit.javascript.host.html.DocumentProxy;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import com.google.common.collect.Maps;
import dp.leiba.social.tools.Logger;
import net.sourceforge.htmlunit.corejs.javascript.Function;
import net.sourceforge.htmlunit.corejs.javascript.NativeArray;
import net.sourceforge.htmlunit.corejs.javascript.NativeObject;
import net.sourceforge.htmlunit.corejs.javascript.Undefined;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
