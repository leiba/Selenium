package dp.leiba.social.tools;

import java.util.*;

/**
 * Array.
 */
public class Array
{

    /**
     * Shift.
     *
     * @param array Array.
     * @param <T>   Type.
     *
     * @return Shifted.
     */
    public static <T> T shift(List<T> array)
    {
        T t = null;

        if (array.size() > 0) {
            t = array.get(0);
            array.remove(0);
        }

        return t;
    }

    /**
     * Pop.
     *
     * @param array Array.
     * @param <T>   Type.
     *
     * @return Shifted.
     */
    public static <T> T pop(List<T> array)
    {
        T t = null;

        if (array.size() > 0) {
            t = array.get(array.size() - 1);
            array.remove(array.size() - 1);
        }

        return t;
    }

    /**
     * Shift.
     *
     * @param array Array.
     * @param <T>   Type.
     *
     * @return Shifted.
     */
    public static <T> T pop(T[] array)
    {
        T t = null;

        if (array.length > 0) {
            t = array[array.length - 1];
        }

        return t;
    }

    /**
     * Rand.
     *
     * @param array Array.
     * @param <T>   Type.
     *
     * @return Shifted.
     */
    public static <T> T rand(T[] array)
    {
        T t = null;

        if (array.length > 0) {
            t = array[Maths.getRand(0, array.length - 1)];
        }

        return t;
    }

    /**
     * Diff.
     *
     * @param one Array.
     * @param two Array.
     *
     * @return Array.
     */
    public static String[] diff(String[] one, String[] two)
    {
        boolean match;
        ArrayList<String> result = new ArrayList<String>();

        for (String oneS : one) {
            match = false;

            for (String twoS : two) {
                if (oneS.equals(twoS)) {
                    match = true;
                    break;
                }
            }

            if (!match) {
                result.add(oneS);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * Get implode.
     *
     * @param separator Separator.
     * @param equals    Equals.
     * @param map       Map.
     *
     * @return Imploded.
     */
    public static String implode(String separator, String equals, Map<String, String> map)
    {
        List<String> list = new ArrayList<String>();

        for (String key : map.keySet()) {
            list.add(key + equals + map.get(key));
        }

        return implode(separator, list.toArray(new String[list.size()]));
    }

    /**
     * Get implode.
     *
     * @param separator Separator.
     * @param data      Data.
     *
     * @return Imploded data.
     */
    public static String implode(String separator, String... data)
    {
        int i;
        StringBuilder sb = new StringBuilder();

        for (i = 0; i < data.length - 1; i++) {
            if (!data[i].matches(" *")) {
                sb.append(data[i]);
                sb.append(separator);
            }
        }

        return data.length > 0 ? sb.append(data[data.length - 1].trim()).toString() : "";
    }

    /**
     * Collection to implode array.
     *
     * @param separator  Separator.
     * @param escape     Escape.
     * @param collection Collection.
     *
     * @return Implode array.
     */
    public static String implode(
        String separator,
        String escape,
        Collection<String> collection
    ) {
        int i;
        String[] keys = getArray(collection);

        for (i = 0; i < keys.length; i++) {
            keys[i] = escape + keys[i] + escape;
        }

        return implode(separator, keys);
    }

    /**
     * Collection to implode array.
     *
     * @param separator  Separator.
     * @param escape     Escape.
     * @param collection Collection.
     *
     * @return Implode array.
     */
    public static String implodeNull(
        String separator,
        String escape,
        Collection<String> collection
    ) {
        int i;
        String[] keys = getArray(collection);

        for (i = 0; i < keys.length; i++) {
            keys[i] = keys[i] == null ? "null" : escape + keys[i] + escape;
        }

        return implode(separator, keys);
    }

    /**
     * Collection to array.
     *
     * @param collection Collection.
     *
     * @return Array.
     */
    public static String[] getArray(Collection<String> collection)
    {
        Object[] keys = collection.toArray();

        return Arrays.asList(keys).toArray(
            new String[keys.length]
        );
    }

    /**
     * Get array sub.
     *
     * @param array Array.
     * @param from  From.
     *
     * @return Sub array.
     */
    public static String[] getSub(String[] array, int from)
    {
        String[] result = new String[array.length - from];
        int i, index = 0;

        for (i = from; i < array.length; i++) {
            result[index++] = array[i];
        }

        return result;
    }
}
