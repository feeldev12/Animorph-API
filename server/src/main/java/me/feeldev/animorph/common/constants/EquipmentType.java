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
        this.armorName = armorName;
    }

    public String getArmorName() {
        return armorName;
    }

    public static EquipmentType fromId(int id) {
        return EquipmentType.values()[id];
    }
}
