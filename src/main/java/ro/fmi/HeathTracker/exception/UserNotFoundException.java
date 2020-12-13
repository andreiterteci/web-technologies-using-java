package ro.fmi.HeathTracker.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "The user was not found!";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
