package me.feeldev.animorph.client.models;

import software.bernie.geckolib.animation.RawAnimation;

public class CustomRawAnimation {
    private final String animationName;
    private final RawAnimation rawAnimation;

    public CustomRawAnimation(String animationName) {
        this.animationName = animationName;
        this.rawAnimation = RawAnimation.begin().thenPlay(animationName);
    }

    public String getAnimationName() {
        return animationName;
    }

    public RawAnimation getRawAnimation() {
        return rawAnimation;
    }
}
