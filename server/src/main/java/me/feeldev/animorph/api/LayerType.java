package me.feeldev.animorph.api;

/**
 * Defines the type of a model layer.
 */
public enum LayerType {
    /**
     * A model layer that adds additional geometry on top of the base model.
     */
    MODEL,

    /**
     * A texture layer that applies a texture overlay to the base model.
     */
    TEXTURE;

    /**
     * Finds a LayerType by its name (case-insensitive).
     *
     * @param name the name to search for
     * @return the matching LayerType, or {@code null} if not found
     */
    public static LayerType getByName(String name) {
        for (LayerType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Finds a LayerType by its ordinal index.
     *
     * @param ordinal the ordinal value
     * @return the matching LayerType
     * @throws ArrayIndexOutOfBoundsException if the ordinal is out of range
     */
    public static LayerType getByOrdinal(int ordinal) {
        return values()[ordinal];
    }
}
