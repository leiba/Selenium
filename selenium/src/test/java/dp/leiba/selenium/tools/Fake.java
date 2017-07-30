package dp.leiba.selenium.tools;

import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Fake.
 */
public class Fake
{

    /**
     * First.
     *
     * @return First.
     */
    public static String first()
    {
        return new Faker().firstName();
    }

    /**
     * Last.
     *
     * @return Last.
     */
    public static String last()
    {
        return new Faker().lastName();
    }

    /**
     * Both.
     *
     * @return Both.
     */
    public static String both()
    {
        return new Faker().name();
    }

    /**
     * Password.
     *
     * @return Password.
     */
    public static String password()
    {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
