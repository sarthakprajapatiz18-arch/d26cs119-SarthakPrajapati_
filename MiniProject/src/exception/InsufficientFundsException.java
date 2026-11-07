package exception;

/** Raised when an account does not have enough available funds. */
public class InsufficientFundsException extends BankException {
    private static final long serialVersionUID = 1L;
    private final long shortfall;

    public InsufficientFundsException(String message, long shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    /** Returns the amount by which the requested withdrawal exceeds available funds. */
    public long getShortfall() {
        return shortfall;
    }
}
