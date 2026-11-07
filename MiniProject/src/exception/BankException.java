package exception;

/** Base checked exception for MiniBank business-rule failures. */
public class BankException extends Exception {
    private static final long serialVersionUID = 1L;

    /** Creates a bank exception with a user-readable message. */
    public BankException(String message) {
        super(message);
    }
}
