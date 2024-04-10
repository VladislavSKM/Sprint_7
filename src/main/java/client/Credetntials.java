package client;

public class Credetntials {
    private final String login;
    private final String password;

    public static Credetntials fromCourier(Courier courier) {
        return new Credetntials(courier.getLogin(), courier.getPassword());
    }

    public Credetntials(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
