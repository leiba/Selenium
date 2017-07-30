package dp.leiba.selenium.http.origin;

import dp.leiba.selenium.http.HttpDriver;

import java.util.HashMap;

/**
 * HttpIp.
 */
public class HttpIp extends HttpDriver
{
    /**
     * Url.
     */
    private static final String URL = "http://ipv6bot.whatismyipaddress.com/";

    /**
     * Constructor.
     */
    public HttpIp()
        throws Exception
    {
        this(null, 0);
    }

    /**
     * Constructor.
     *
     * @param host Host.
     * @param port Port.
     */
    public HttpIp(String host, int port)
        throws Exception
    {
        super(host, port);
    }

    /**
     * Get url.
     *
     * @return Url.
     */
    public String getUrl() {
        return URL;
    }

    /**
     * Get cookies.
     *
     * @return Cookies.
     */
    public HashMap<String, String> getCookies()
    {
        return new HashMap<String, String>();
    }

    /**
     * Get headers.
     *
     * @return Headers.
     */
    public HashMap<String, String> getHeaders()
    {
        return new HashMap<String, String>();
    }

    /**
     * Get headers for post.
     *
     * @return Headers.
     */
    public HashMap<String, String> getHeadersPost()
    {
        return new HashMap<String, String>();
    }
}
