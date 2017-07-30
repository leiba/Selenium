package dp.leiba.social.tools;

import com.google.gson.GsonBuilder;
import org.openqa.selenium.WebDriver;

/**
 * Logger.
 */
public class Logger
{

    /**
     * Log.
     *
     * @param message Message.
     */
    public static void log(String message)
    {
        IO.write(String.format(
            "[] : %s",
            message
        ));
    }

    /**
     * Error.
     *
     * @param message Message.
     */
    public static void error(String message)
    {
        log(message);
    }

    /**
     * Dump.
     *
     * @param var Variable.
     */
    public static void dump(Object var)
    {
        dump(var, null, false);
    }

    /**
     * Dump.
     *
     * @param var    Variable.
     * @param driver WebDriver.
     * @param exit   Exit.
     */
    public static void dump(Object var, WebDriver driver, boolean exit)
    {
        String dump = new GsonBuilder().setPrettyPrinting().create().toJson(var);
        IO.write(dump);

        if (exit) {
            if (driver != null) {
                driver.close();
            }

            System.exit(0);
        }
    }
}
