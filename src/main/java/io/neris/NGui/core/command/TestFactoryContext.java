package io.neris.NGui.core.command;

import org.bukkit.Material;

import java.util.UUID;

public class TestFactoryContext {
    public UUID playerUUID;
    public Material material;
    public int amount;

    public TestFactoryContext(UUID playerUUID, Material material, int amount){
        this.playerUUID = playerUUID;
        this.material = material;
        this.amount = amount;
    }
}
