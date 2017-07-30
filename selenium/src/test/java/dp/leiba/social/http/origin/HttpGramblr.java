package dp.leiba.social.http.origin;

import dp.leiba.social.http.HttpDriver;

import java.util.HashMap;

/**
 * HttpGramblr.
 */
public class HttpGramblr extends HttpDriver
{

    /**
     * Url.
     */
    private static final String URL = "http://localhost.gramblr.com:4343";

    /**
     * Constructor.
     */
    public HttpGramblr()
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
    public HttpGramblr(String host, int port)
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
        return new HashMap<String, String>() {{
            put("Content-Type", "application/json;charset=UTF-8");
        }};
    }
}
