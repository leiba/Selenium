package dp.leiba.selenium.tools;

import java.util.Random;

/**
 * Maths.
 */
public class Maths
{

    /**
     * Random.
     *
     * @param min Min.
     * @param max Max.
     *
     * @return Random mills.
     */
    public static int getRand(int min, int max)
    {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * Get rand.
     *
     * @param objects Objects.
     *
     * @return Object.
     */
    public static <T> T getRand(T[] objects)
    {
        return objects[getRand(0, objects.length - 1)];
    }

    /**
     * Random input pause.
     *
     * @return Random mills.
     */
    public static int getRandInput()
    {
        return getRand(200, 400);
    }

    /**
     * Get rand is.
     *
     * @return is.
     */
    public static boolean getIs()
    {
        return getRand(0, 1) == 1;
    }
}
