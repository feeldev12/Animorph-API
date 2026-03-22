package me.feeldev.animorph.api;

/**
 * Thrown when a stub method is called without a real implementation.
 * <p>
 * This API module is compile-only. The actual implementation is provided
 * at runtime by the Animorph plugin/mod.
 */
public class NotImplementedException extends RuntimeException {

    public NotImplementedException() {
        super("This method is not implemented. The Animorph plugin/mod must be present at runtime.");
    }
}
