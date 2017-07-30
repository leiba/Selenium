package dp.leiba.social.http.origin;

import dp.leiba.social.http.HttpDriver;

import java.util.HashMap;

/**
 * HttpInstagram.
 */
public class HttpInstagram extends HttpDriver
{

    /**
     * Url.
     */
    private static final String URL = "https://www.instagram.com";

    /**
     * Constructor.
     */
    public HttpInstagram()
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
    public HttpInstagram(String host, int port)
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
        return new HashMap<String, String>() {{
            put("ig_pr", "1.5");
            put("ig_vw", "1920");
            put("s_network", "");
        }};
    }

    /**
     * Get headers.
     *
     * @return Headers.
     */
    public HashMap<String, String> getHeaders()
    {
        return new HashMap<String, String>() {{
            put("Referer",          "https://www.instagram.com/");
            put("Origin",           "https://www.instagram.com");
            put("Host",             "www.instagram.com");
            put("X-Requested-With", "XMLHttpRequest");
        }};
    }

    /**
     * Get headers for post.
     *
     * @return Headers.
     */
    public HashMap<String, String> getHeadersPost()
    {
        return new HashMap<String, String>() {{
            put("Referer",          "https://www.instagram.com/");
            put("Origin",           "https://www.instagram.com");
            put("Host",             "www.instagram.com");
            put("X-Instagram-AJAX", "1");
            put("X-Requested-With", "XMLHttpRequest");
            put("X-CSRFToken",      getCookie("csrftoken"));
        }};
    }

    public void update()
        throws Exception
    {
        setHeader("Upgrade-Insecure-Requests", "1");
        doGet(NOP);
        removeHeader("Upgrade-Insecure-Requests");
    }
}
