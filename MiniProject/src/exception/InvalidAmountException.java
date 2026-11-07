package exception;

/** Raised when a deposit, withdrawal or transfer amount is invalid. */
public class InvalidAmountException extends BankException {
    private static final long serialVersionUID = 1L;

    public InvalidAmountException(String message) {
        super(message);
    }
}
