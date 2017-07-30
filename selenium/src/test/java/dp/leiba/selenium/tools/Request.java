package dp.leiba.selenium.tools;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Request.
 */
public class Request
{

    /**
     * Keys.
     */
    private static final String KEY_UA  = "User-Agent";

    /**
     * Values.
     */
    private static final String VL_UA   = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36)";

    /**
     * Post.
     *
     * @param url    Url.
     * @param params Params.
     *
     * @return Response.
     */
    public static String doPost(String url, HashMap<String, String> params)
        throws Exception
    {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty(KEY_UA, VL_UA);

        con.setDoOutput(true);
        Stream.put(con.getOutputStream(), getParams(params).getBytes());

        return Stream.get(con.getInputStream());
    }

    /**
     * Dp multipart.
     *
     * @param url    Url.
     * @param entity Entity.
     *
     * @return Content.
     */
    public static String doMultipart(String url, HttpEntity entity)
        throws Exception
    {
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);

        return Stream.get(
            HttpClientBuilder.create().build().execute(post).getEntity().getContent()
        );
    }

    /**
     * Get params.
     *
     * @param params Params.
     *
     * @return Converted params.
     */
    public static String getParams(HashMap<String, String> params)
    {
        ArrayList<BasicNameValuePair> convert = new ArrayList<BasicNameValuePair>();

        for (String key : params.keySet()) {
            convert.add(new BasicNameValuePair(key, params.get(key)));
        }

        return URLEncodedUtils.format(convert, Charset.defaultCharset());
    }

    /**
     * Get cookies.
     *
     * @param cookies Cookies.
     *
     * @return Map.
     */
    public static HashMap<String, String> getCookies(List<String> cookies)
    {
        String[] node;
        HashMap<String, String> map = new HashMap<String, String>();

        if (cookies != null) for (String cookie : cookies) {
            for (String pair : cookie.split(";")) {
                node = pair.trim().split("=");

                map.put(node[0], node.length > 1 ? node[1] : "");
            }
        }

        return map;
    }
}
