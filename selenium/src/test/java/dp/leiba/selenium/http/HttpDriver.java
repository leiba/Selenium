package dp.leiba.selenium.http;

import dp.leiba.selenium.http.bound.Bound;
import dp.leiba.selenium.tools.Array;
import dp.leiba.selenium.tools.Stream;

import javax.net.ssl.HttpsURLConnection;
import java.net.*;
import java.util.HashMap;
import java.util.List;

/**
 * .HttpDriver.
 */
abstract public class HttpDriver
{

    /**
     * Tokens.
     */
    public static final String EQUAL         = "=";
    public static final String SEP           = "; ";
    public static final String NOP           = "";
    public static final String HTTPS         = "https";

    /**
     * Request types.
     */
    private static final String REQ_GET      = "GET";
    private static final String REQ_POST     = "POST";

    /**
     * Headers.
     */
    private static final String HD_SET_COOKIE   = "Set-Cookie";
    private static final String HD_COOKIE       = "Cookie";
    private static final String HD_BOUND        = "Content-Type";

    /**
     * Headers values.
     */
    private static final String HDV_BOUND       = "multipart/form-data; boundary=";

    /**
     * Headers.
     */
    private HashMap<String, String> _headers = new HashMap<String, String>() {{
        put("User-Agent",       "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36)");
        put("Accept",           "*/*");
        put("Accept-Language",  "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        put("Pragma",           "no-cache");
    }};

    /**
     * Host.
     */
    private String _host;

    /**
     * Port.
     */
    private int _port;

    /**
     * Cookies.
     */
    private HashMap<String, String> _cookies;

    /**
     * Constructor.
     */
    public HttpDriver()
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
    public HttpDriver(String host, int port)
        throws Exception
    {
        _host    = host;
        _port    = port;
        _cookies = getCookies();

        try {
            doGet(NOP);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * Get url.
     *
     * @return Url.
     */
    abstract public String getUrl();

    /**
     * Get cookies.
     *
     * @return Cookies.
     */
    abstract public HashMap<String, String> getCookies();

    /**
     * Get headers.
     *
     * @return Headers.
     */
    abstract public HashMap<String, String> getHeaders();

    /**
     * Get headers for post.
     *
     * @return Headers.
     */
    abstract public HashMap<String, String> getHeadersPost();

    /**
     * Get cookie.
     *
     * @param name Name.
     *
     * @return Cookie.
     */
    public String getCookie(String name)
    {
        return _cookies.get(name);
    }

    /**
     * Set cookie.
     *
     * @param name  Name.
     * @param value Value.
     */
    public void setCookie(String name, String value)
    {
        _cookies.put(name.trim(), value.trim());
    }

    /**
     * Remove cookie.
     *
     * @param name Name.
     */
    public void removeCookie(String name)
    {
        _cookies.remove(name);
    }

    /**
     * Set geader.
     *
     * @param name  Name.
     * @param value Value.
     */
    public void setHeader(String name, String value)
    {
        _headers.put(name.trim(), value.trim());
    }

    /**
     * Get header.
     *
     * @param name Name.
     *
     * @return Cookie.
     */
    public String getHeader(String name)
    {
        return _headers.get(name);
    }

    /**
     * Remove header.
     *
     * @param name Name.
     */
    public void removeHeader(String name)
    {
        _headers.remove(name);
    }

    /**
     * Do get.
     *
     * @param uri Uri.
     *
     * @return Result.
     */
    public String doGet(String uri)
        throws Exception
    {
        return _doRequest(
            getUrl() + uri,
            NOP.getBytes(),
            false,
            null
        );
    }

    /**
     * Do post.
     *
     * @param uri    Uri.
     * @param params Params.
     *
     * @return Result.
     */
    public String doPost(String uri, String params)
        throws Exception
    {
        return _doRequest(
            getUrl() + uri,
            params.getBytes(),
            true,
            null
        );
    }

    /**
     * Do multipart.
     *
     * @param uri    Uri.
     * @param bounds Bounds.
     *
     * @return Result.
     */
    public String doMultipart(String uri, Bound[] bounds)
        throws Exception
    {
        String part = Bound.getBoundPart();

        return _doRequest(
            getUrl() + uri,
            Bound.getFull(part, bounds),
            true,
            part
        );
    }

    /**
     * Do request.
     *
     * @param url    Url.
     * @param params Params.
     * @param isPost Is post.
     * @param bound  Bound.
     *
     * @return Response.
     */
    private String _doRequest(String url, byte[] params, boolean isPost, String bound)
        throws Exception
    {
        URLConnection con = _getCon(url, isPost);

        _putProp(con, _headers);
        _putProp(con, HD_COOKIE, Array.implode(SEP, EQUAL, _cookies));

        if (!isPost) {
            _putProp(con, getHeaders());
        } else {
            _putProp(con, getHeadersPost());

            if (bound != null) {
                _putProp(con, HD_BOUND, HDV_BOUND + bound);
            }

            Stream.put(con.getOutputStream(), params);
        }

        _putCookies(con);
        return Stream.get(con.getInputStream());
    }

    /**
     * Get connection.
     *
     * @param url  Url.
     * @param post Post.
     *
     * @return HttpsURLConnection.
     */
    private URLConnection _getCon(String url, boolean post)
        throws Exception
    {
        Proxy proxy;
        URLConnection con;
        boolean https = url.startsWith(HTTPS);

        if (_host != null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(_host, _port));
            con   = new URL(url).openConnection(proxy);
        } else {
            con   = new URL(url).openConnection();
        }

        if (!post) {
            if (https) {
                ((HttpsURLConnection) con).setRequestMethod(REQ_GET);
            } else {
                ((HttpURLConnection) con).setRequestMethod(REQ_GET);
            }


        } else {
            if (https) {
                ((HttpsURLConnection) con).setRequestMethod(REQ_POST);
            } else {
                ((HttpURLConnection) con).setRequestMethod(REQ_POST);
            }
        }

        con.setDoInput(true);
        con.setDoOutput(true);

        return con;
    }

    /**
     * Put prop.
     *
     * @param con Connection.
     * @param key Key.
     * @param val Value.
     */
    private void _putProp(URLConnection con, final String key, final String val)
    {
        _putProp(con, new HashMap<String, String>() {{
            put(key, val);
        }});
    }

    /**
     * Set props.
     *
     * @param con   Connection.
     * @param props Props.
     */
    private void _putProp(URLConnection con, HashMap<String, String> props)
    {
        for (String key : props.keySet()) {
            con.setRequestProperty(key, props.get(key));
        }
    }

    /**
     * Put cookies.
     *
     * @param con Connection.
     */
    private void _putCookies(URLConnection con)
    {
        String[] parts;
        List<String> cookies = con.getHeaderFields().get(HD_SET_COOKIE);

        if (cookies != null) {
            for (String cookie : con.getHeaderFields().get(HD_SET_COOKIE)) {
                parts = cookie.split(SEP)[0].split(EQUAL);
                setCookie(parts[0], parts.length == 2 ? parts[1] : NOP);
            }
        }
    }
}