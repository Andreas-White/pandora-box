package enums;

public enum LoginMethod {

    PORTAL(0, "You have logged in through the portal"),
    FID(1, "You have logged in with fId"),
    BANK_ID(2, "You have logged in with bankId"),
    USERNAME_PASSWORD(3, "You have  logged in with username and password");

    private final String message;
    private final int status;

    LoginMethod(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return message;
    }

}