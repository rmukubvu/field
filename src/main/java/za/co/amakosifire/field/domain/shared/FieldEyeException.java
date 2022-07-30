package za.co.amakosifire.field.domain.shared;

public class FieldEyeException extends RuntimeException {
    public FieldEyeException(String message) {
        super(message);
    }

    public FieldEyeException(final String message, final Exception e) {
        super(message, e);
    }
}
