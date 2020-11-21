package ro.fmi.HeathTracker.exception;

public class RoleNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Role not found!";

    public RoleNotFoundException() {
        super(String.format(MESSAGE));
    }
}
