package ro.fmi.HeathTracker.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "There is already an account registered with this username or email!";

    public UserAlreadyExistsException() {
        super(String.format(MESSAGE));
    }
}
