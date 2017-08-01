package dp.leiba.selenium.tools;

import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Locale;

/**
 * Fake.
 */
public class Fake
{

    /**
     * Faker.
     */
    private static Faker _faker = new Faker(new Locale("en"));

    /**
     * First.
     */
    private String first;

    /**
     * Last.
     */
    private String last;

    /**
     * Password.
     */
    private String password;

    /**
     * Constructor.
     */
    public Fake()
    {
        first    = _prepare(_faker.firstName());
        last     = _prepare(_faker.lastName());
        password = new BigInteger(130, new SecureRandom()).toString(32);
    }

    /**
     * First.
     *
     * @return First.
     */
    public String first()
    {
        return first;
    }

    /**
     * Last.
     *
     * @return Last.
     */
    public String last()
    {
        return last;
    }

    /**
     * Login.
     *
     * @return Login.
     */
    public String login()
    {
        return first.toLowerCase() + '.' + last.toLowerCase();
    }

    /**
     * Password.
     *
     * @return Password.
     */
    public String password()
    {
        return password;
    }

    /**
     * Prepare.
     *
     * @param text Text.
     *
     * @return Text.
     */
    private String _prepare(String text)
    {
        return text.replace("'", "");
    }
}
