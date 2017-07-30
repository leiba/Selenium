package dp.leiba.selenium.http.bound;

import org.apache.commons.lang3.ArrayUtils;

/**
 * BoundString.
 */
public class BoundString extends Bound
{

    /**
     * Name.
     */
    public String name;

    /**
     * Value.
     */
    public String value;

    /**
     * Constructor.
     *
     * @param name  Name.
     * @param value Value.
     */
    public BoundString(String name, String value)
    {
        this.name   = name;
        this.value  = value;
    }

    /**
     * To part.
     *
     * @param bound Bound.
     *
     * @return Bytes.
     */
    public byte[] toPart(String bound)
        throws Exception
    {
        byte[] part = new byte[0];

        part = ArrayUtils.addAll(part, (BOUND + bound + NL).getBytes());
        part = ArrayUtils.addAll(part, (String.format(D_STRING, name) + NL + NL).getBytes());
        part = ArrayUtils.addAll(part, (value + NL).getBytes());

        return part;
    }
}
