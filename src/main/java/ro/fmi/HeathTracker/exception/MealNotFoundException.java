package ro.fmi.HeathTracker.exception;

public class MealNotFoundException extends RuntimeException {
    private static final String MESSAGE = "The meal was not found!";

    public MealNotFoundException() {
        super(MESSAGE);
    }
}
