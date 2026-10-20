package me.feeldev.animorph.common.constants;

public enum EquipmentType {

    HEAD("head_armor"),
    CHEST("chest_armor"),
    LEGS("legs_armor"),
    FEET("feet_armor"),
    CAPE("cape_armor"),
    ELYTRA("elytra_armor"),
    OTHER("layer");

    final String armorName;

    EquipmentType(String armorName) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public String getArmorName() {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    public static EquipmentType fromId(int id) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
