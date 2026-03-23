package me.feeldev.animorph.client.api;

import me.feeldev.animorph.client.interfaces.ICustomMolangQuery;
import me.feeldev.animorph.client.interfaces.IPlayerData;

import java.util.Optional;
import java.util.UUID;

/**
 * Internal handler interface for the client API.
 * <p>
 * Implemented by the Animorph client module. Not intended for external use.
 */
public interface ClientMorphAPIHandler {

    void registerController(String id, AnimorphController controller);

    boolean hasController(String id);

    void registerMolangVariable(String name);

    <T extends IPlayerData> void addMolangQuery(ICustomMolangQuery<T> query);

    Optional<String> getPlayerModelId(UUID playerId);

    boolean hasModel(UUID playerId);

    Optional<String> getEmoteId(UUID playerId);

    boolean isEmoting(UUID playerId);
}
