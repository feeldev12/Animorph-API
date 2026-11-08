package me.feeldev.animorph.api;

import java.util.List;
import java.util.Set;

/**
 * Emotes your mod/plugin registers at runtime (see {@link IMorphAPI#registerEmotes}), from an
 * animation file the game clients download themselves: the same as an
 * {@code animorph/animations/<namespace>.yml} with {@code animation: client:<id>} (or a remote ref)
 * and an {@code animations:} list, plus its {@code properties:}.
 * <p>
 * Each name in {@link #animations()} becomes its own emote, {@code "<namespace>:<name>"}, playing the
 * animation of that name inside the file.
 *
 * @param namespace            the emotes' namespace (lowercase {@code a-z0-9/._-})
 * @param animationRef         the GeckoLib {@code .animation.json}: {@code client:<id>} or
 *                             {@code https://...#sha256=<64 hex>[&size=<bytes>]}
 * @param animations           the animation names inside the file to register as emotes
 * @param freeze               the player can't move while it plays ({@code freeze})
 * @param stopOnDeath          it stops when the player dies ({@code stop.on_death})
 * @param stopOnHurt           it stops when the player is hurt ({@code stop.on_hurt})
 * @param override             it replaces the player's other animations while it plays ({@code override})
 * @param controllerExceptions controllers that keep playing over it ({@code controller_exceptions})
 */
public record AnimorphEmoteDefinition(String namespace, String animationRef, List<String> animations, boolean freeze, boolean stopOnDeath, boolean stopOnHurt, boolean override, Set<String> controllerExceptions) {

    public AnimorphEmoteDefinition {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * Plain emotes with every property off.
     */
    public AnimorphEmoteDefinition(String namespace, String animationRef, List<String> animations) {
        this(namespace, animationRef, animations, false, false, false, false, Set.of());
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }

    /**
     * The emote id of one of {@link #animations()}.
     */
    public String emoteId(String animation) {
        throw new UnsupportedOperationException("Animorph API: provided by the Animorph mod at runtime");
    }
}
