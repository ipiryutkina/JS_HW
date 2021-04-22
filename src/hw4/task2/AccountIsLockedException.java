package hw4.task2;

public class AccountIsLockedException extends Exception {

    public AccountIsLockedException() {
        super();
    }

    public AccountIsLockedException(String message) {
        super(message);
    }

    public AccountIsLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountIsLockedException(Throwable cause) {
        super(cause);
    }

    public AccountIsLockedException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}