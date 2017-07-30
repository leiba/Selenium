package dp.leiba.selenium.map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Map.
 */
public class Map
{

    /**
     * Split.
     */
    private static final String SPLIT = "\\.";

    /**
     * Map.
     *
     * @param json Json.
     * @param cls  Class.
     * @param <T>  Type.
     *
     * @return Filled.
     */
    public static <T> T map(JsonObject json, Class<T> cls)
        throws Exception
    {
        Class<?> type;
        JsonElement tmp;
        T obj = cls.newInstance();

        for (Field field : obj.getClass().getFields()) {
            if (field.isAnnotationPresent(IMap.class)) {
                type = field.getType();
                tmp  = null;

                for (String split : field.getAnnotation(IMap.class).value().split(SPLIT)) {
                    tmp = tmp == null ? json.get(split) : tmp.getAsJsonObject().get(split);
                }

                if (tmp == null) {
                    continue;
                }

                if (type.isArray()) {
                    field.set(obj, map(tmp.getAsJsonArray(), type.getComponentType()));
                } else if (type.equals(int.class)) {
                    field.set(obj, tmp.getAsInt());
                } else if (type.equals(long.class)) {
                    field.set(obj, tmp.getAsLong());
                } else if (type.equals(String.class)) {
                    field.set(obj, tmp.isJsonNull() ? null : tmp.getAsString());
                } else if (type.equals(boolean.class)) {
                    field.set(obj, tmp.getAsBoolean());
                }
            }
        }

        return obj;
    }

    /**
     * Map.
     *
     * @param nodes Json.
     * @param type  Class.
     * @param <T>   Type.
     *
     * @return Filled.
     */
    public static <T> T[] map(JsonArray nodes, Class<T> type)
        throws Exception
    {
        JsonElement node;
        Object[] array  = (Object[]) Array.newInstance(type, nodes.size());

        for (int i = 0; i < array.length; i++) {
            node = nodes.get(i);

            if (type.equals(int.class)) {
                array[i] = node.getAsInt();
            } else if (type.equals(long.class)) {
                array[i] = node.getAsLong();
            } else if (type.equals(String.class)) {
                array[i] = node.isJsonNull() ? null : node.getAsString();
            } else if (type.equals(boolean.class)) {
                array[i] = node.getAsBoolean();
            } else {
                array[i] = map(node.getAsJsonObject(), type);
            }
        }

        return (T[]) array;
    }
}
