package dp.leiba.selenium.http.bound;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLConnection;

/**
 * BoundFile.
 */
public class BoundFile extends Bound
{

    /**
     * Name.
     */
    public String name;

    /**
     * File name.
     */
    public String fileName;

    /**
     * File.
     */
    public String file;

    public BoundFile(String name, String fileName, String file)
    {
        this.name       = name;
        this.fileName   = fileName;
        this.file       = file;
    }

    /**
     * To part.
     *
     * @param bound Bound.
     * @return Bytes.
     */
    public byte[] toPart(String bound)
        throws Exception
    {
        File path           = new File(file);
        FileInputStream fis = new FileInputStream(path);
        byte[] part         = new byte[0];
        byte[] data         = new byte[(int) path.length()];

        fis.read(data);
        part = ArrayUtils.addAll(part, (BOUND + bound + NL).getBytes());;
        part = ArrayUtils.addAll(part, (String.format(D_FILE, name, fileName) + NL).getBytes());
        part = ArrayUtils.addAll(part, (String.format(TYPE, URLConnection.guessContentTypeFromName(path.getName())) + NL + NL).getBytes());
        part = ArrayUtils.addAll(part, data);
        part = ArrayUtils.addAll(part, NL.getBytes());

        return part;
    }
}
