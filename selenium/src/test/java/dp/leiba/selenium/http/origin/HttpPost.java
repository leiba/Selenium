package dp.leiba.selenium.http.origin;

import dp.leiba.selenium.http.HttpDriver;

import java.util.HashMap;

/**
 * HttpPost.
 */
public class HttpPost extends HttpDriver
{

    /**
     * Url.
     */
    private static final String URL = "https://www.golchess.com";

    /**
     * Constructor.
     */
    public HttpPost()
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
    public HttpPost(String host, int port)
        throws Exception
    {
        super(host, port);
    }

    /**
     * Get url.
     *
     * @return Url.
     */
    public String getUrl()
    {
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
