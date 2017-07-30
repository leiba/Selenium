package dp.leiba.selenium.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * IO.
 */
public class IO
{

    /**
     * Read.
     *
     * @return Line.
     */
    public static String read()
        throws Exception
    {
        return read(null);
    }

    /**
     * Read.
     *
     * @param prepend Prepend.
     *
     * @return Line.
     */
    public static String read(String prepend)
        throws Exception
    {
        if (prepend != null) {
            write(prepend, false);
        }

        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    /**
     * Write.
     *
     * @param line Line.
     */
    public static void write(String line)
    {
        write(line, true);
    }

    /**
     * Write.
     *
     * @param line    Line.
     * @param isBreak Is break.
     */
    public static void write(String line, boolean isBreak)
    {
        if (isBreak) {
            System.out.println(line);
        } else {
            System.out.print(line);
        }
    }
}
