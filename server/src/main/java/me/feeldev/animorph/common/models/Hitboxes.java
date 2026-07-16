package me.feeldev.animorph.common.models;

import me.feeldev.animorph.common.constants.HitboxPoseType;

import java.util.HashMap;
import java.util.Map;

public class Hitboxes {
    public static final double PIXEL_TO_BLOCK = 1.0 / 16.0;
    private final Map<HitboxPoseType, ModelHitbox> hitboxMap;
    private boolean hasCustomHitboxes = false;

    public Hitboxes() {
        hitboxMap = new HashMap<>();
        hitboxMap.put(HitboxPoseType.STANDING, ModelHitbox.empty().multiply(PIXEL_TO_BLOCK));
    }

    public Hitboxes(Map<HitboxPoseType, ModelHitbox> hitboxMap) {
        this.hitboxMap = hitboxMap;
        this.hasCustomHitboxes = !hitboxMap.isEmpty();
    }

    public void addHitbox(HitboxPoseType type, ModelHitbox modelHitbox) {
        hitboxMap.put(type, modelHitbox);
        hasCustomHitboxes = true;
    }

    public boolean hasCustomHitboxes() {
        return hasCustomHitboxes;
    }

    public ModelHitbox getHitbox(HitboxPoseType type) {
        return hitboxMap.getOrDefault(type, hitboxMap.get(HitboxPoseType.STANDING));
    }

    public Map<HitboxPoseType, ModelHitbox> getHitboxMap() {
        return hitboxMap;
    }

    public Hitboxes copy() {
        return new Hitboxes(new HashMap<>(hitboxMap));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hitboxes other)) return false;
        return hasCustomHitboxes == other.hasCustomHitboxes && hitboxMap.equals(other.hitboxMap);
    }

    @Override
    public int hashCode() {
        int mapHash = 0;
        for (Map.Entry<HitboxPoseType, ModelHitbox> entry : hitboxMap.entrySet()) {
            mapHash += entry.getKey().name().hashCode() ^ entry.getValue().hashCode();
        }
        return 31 * Boolean.hashCode(hasCustomHitboxes) + mapHash;
    }
}
