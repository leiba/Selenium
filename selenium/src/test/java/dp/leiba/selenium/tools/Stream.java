package dp.leiba.selenium.tools;

import java.io.*;

/**
 * Stream.
 */
public class Stream
{

    /**
     * Put.
     *
     * @param stream Stream.
     * @param data   Data.
     */
    public static void put(OutputStream stream, byte[] data)
        throws Exception
    {
        DataOutputStream wr = new DataOutputStream(stream);
        wr.write(data);
        wr.flush();
        wr.close();
    }

    /**
     * Get response.
     *
     * @param stream Stream.
     *
     * @return Response.
     */
    public static String get(InputStream stream)
        throws Exception
    {
        String inputLine;
        BufferedReader in     = new BufferedReader(new InputStreamReader(stream));
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        stream.close();

        return response.toString();
    }
}
