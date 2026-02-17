package mg.itu.rh.auth.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account not found");
    }
}
