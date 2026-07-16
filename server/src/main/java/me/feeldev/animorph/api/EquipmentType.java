package me.feeldev.animorph.api;

/**
 * The equipment slot an equipment override applies to, mirroring a model yml's
 * {@code equipment:} block. {@code OTHER} is not a real yml slot and is never populated.
 */
public enum EquipmentType {
    HEAD,
    CHEST,
    LEGS,
    FEET,
    CAPE,
    ELYTRA,
    OTHER
}
