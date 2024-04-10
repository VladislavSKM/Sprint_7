package client;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private final String login;
    private final String password;
    private final String firstName;

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public static Courier createRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public static Courier createRandomWithoutPass() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, null, firstName);
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

}
