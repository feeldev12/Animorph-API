package me.feeldev.animorph.client.api;

/**
 * Thrown when a stub method is called without a real implementation.
 * <p>
 * This API module is compile-only. The actual implementation is provided
 * at runtime by the Animorph client mod.
 */
public class NotImplementedException extends RuntimeException {

    public NotImplementedException() {
        super("This method is not implemented. The Animorph client mod must be present at runtime.");
    }
}
