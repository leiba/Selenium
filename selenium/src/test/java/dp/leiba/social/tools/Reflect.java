package dp.leiba.social.tools;

/**
 * Reflect.
 */
public class Reflect
{

    /**
     * Instance.
     *
     * @param cls Class.
     * @param <T> Type.
     *
     * @return Instance of type.
     */
    public static <T> T instance(Class cls)
    {
        try {
            return (T) cls.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
