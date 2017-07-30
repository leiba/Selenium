package dp.leiba.selenium;

import dp.leiba.selenium.driver.Driver;
import org.junit.Test;

/**
 * TestExample.
 */
public class TestExample
{

    @Test
    public void testExample()
        throws Exception
    {
        Driver.getDriver("http://example.com");
    }
}
