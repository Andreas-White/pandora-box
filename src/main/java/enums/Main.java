package enums;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int loginMethod = (int) (start % 4);

        switch (loginMethod) {
            case 0:
                System.out.println(LoginMethod.PORTAL.getMessage());
                break;
            case 1:
                System.out.println(LoginMethod.FID.getMessage());
                break;
            case 2:
                System.out.println(LoginMethod.BANK_ID.getMessage());
                break;
            case 3:
                System.out.println(LoginMethod.USERNAME_PASSWORD.getMessage());
                break;
            default:
                System.out.println("No valid login method");
        }
    }
}
