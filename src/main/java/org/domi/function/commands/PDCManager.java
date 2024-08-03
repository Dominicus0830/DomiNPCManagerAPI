package org.domi.function.commands;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.domi.function.events.enums.DomiNPCClickType;

public class PDCManager {
    public void setNPCPDC(JavaPlugin plugin, NPC npc, DomiNPCClickType npcType) {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey(plugin, "DomiNPCType");
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            dataContainer.set(key, PersistentDataType.STRING, npcType.name());
        }
    }

    public void removeNPCPDC(JavaPlugin plugin, NPC npc, DomiNPCClickType npcType) {
        Entity entity = npc.getEntity();
        NamespacedKey key = new NamespacedKey(plugin, "DomiNPCType");
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        if (entity != null) {
            if (dataContainer.has(key, PersistentDataType.STRING)) {
                String storedValue = dataContainer.get(key, PersistentDataType.STRING);
                if (storedValue.equals(npcType.name())) {
                    dataContainer.remove(key);
                }
            }
        }
    }

    public void removeAllNPCPDC(JavaPlugin plugin, NPC npc) {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey(plugin, "DomiNPCType");
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            dataContainer.remove(key);
        }
    }

    public boolean hasNPCPDCValue(JavaPlugin plugin, NPC npc, DomiNPCClickType npcType) {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey(plugin, "DomiNPCType");
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            return dataContainer.has(key, PersistentDataType.STRING) && dataContainer.get(key, PersistentDataType.STRING).equals(npcType.name());
        }
        return false;
    }

    public DomiNPCClickType getNPCPDCValue(JavaPlugin plugin, NPC npc) throws IllegalArgumentException {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey(plugin, "DomiNPCType");
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            if (dataContainer.has(key, PersistentDataType.STRING)) {
                String value = dataContainer.get(key, PersistentDataType.STRING);
                return DomiNPCClickType.valueOf(value);
            }
        }
        return DomiNPCClickType.NONE;
    }
}
