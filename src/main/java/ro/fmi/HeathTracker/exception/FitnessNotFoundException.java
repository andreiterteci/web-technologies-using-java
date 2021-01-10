package ro.fmi.HeathTracker.exception;

public class FitnessNotFoundException extends RuntimeException {
    private static final String MESSAGE = "The fitness was not found!";

    public FitnessNotFoundException() {
        super(MESSAGE);
    }
}
