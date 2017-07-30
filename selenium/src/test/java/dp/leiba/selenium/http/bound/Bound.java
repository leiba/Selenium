package dp.leiba.selenium.http.bound;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Bound.
 */
abstract public class Bound
{

    public static final String NL       = "\r\n";
    public static final String BOUND    = "--";
    public static final String D_STRING = "Content-Disposition: form-data; name=\"%1$s\"";
    public static final String D_FILE   = "Content-Disposition: form-data; name=\"%1$s\"; filename=\"%2$s\"";
    public static final String TYPE     = "Content-Type: %1$s";

    /**
     * Get bound.
     *
     * @return Bound.
     */
    public static String getBoundPart()
    {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    /**
     * Get full.
     *
     * @param boundPart Bound part.
     * @param bounds    Bounds.
     *
     * @return Full parts.
     */
    public static byte[] getFull(String boundPart, Bound[] bounds)
        throws Exception
    {
        byte[] full = new byte[0];

        for (Bound bound : bounds) {
            full = ArrayUtils.addAll(full, bound.toPart(boundPart));
        }

        return ArrayUtils.addAll(full, (BOUND + boundPart + BOUND + NL).getBytes());
    }

    /**
     * To part.
     *
     * @param bound Bound.
     *
     * @return Bytes.
     */
    abstract public byte[] toPart(String bound)
        throws Exception;
}
