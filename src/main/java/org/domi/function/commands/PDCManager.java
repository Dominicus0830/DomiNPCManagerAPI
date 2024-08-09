package org.domi.function.commands;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.domi.DomiNPCManagerAPI;
import org.domi.function.events.enums.DomiNPCClickType;

public class PDCManager {
    private static final DomiNPCManagerAPI plugin = DomiNPCManagerAPI.getPlugin();
    private final NamespacedKey key = new NamespacedKey("dominpc", "dominpctype");
    public void setNPCPDC(NPC npc, DomiNPCClickType npcType) {
        Entity entity = npc.getEntity();
        if (entity != null) {
            PersistentDataContainer dataContainer = npc.getEntity().getPersistentDataContainer();
            dataContainer.set(key, PersistentDataType.STRING, npcType.name().toUpperCase());
        }
    }



    public void removeNPCPDC(NPC npc, DomiNPCClickType npcType) {
        Entity entity = npc.getEntity();
        NamespacedKey key = new NamespacedKey(plugin, "dominpcype");
        PersistentDataContainer dataContainer = npc.getEntity().getPersistentDataContainer();
        if (entity != null) {
            if (dataContainer.has(key, PersistentDataType.STRING)) {
                String storedValue = dataContainer.get(key, PersistentDataType.STRING);
                if (storedValue.equals(npcType.name())) {
                    dataContainer.remove(key);
                }
            }
        }
    }

    public void removeAllNPCPDC(NPC npc) {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey(plugin, "dominpcype");
            PersistentDataContainer dataContainer = npc.getEntity().getPersistentDataContainer();
            dataContainer.remove(key);
        }
    }

    public boolean hasNPCPDCValue(NPC npc, DomiNPCClickType npcType) {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey(plugin, "dominpcype");
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            return dataContainer.has(key, PersistentDataType.STRING) && dataContainer.get(key, PersistentDataType.STRING).equals(npcType.name());
        }
        return false;
    }

    public DomiNPCClickType getNPCPDCValue(NPC npc) throws IllegalArgumentException {
        Entity entity = npc.getEntity();
        if (entity != null) {
            NamespacedKey key = new NamespacedKey("dominpc", "dominpcype");
            PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
            if (dataContainer.has(key, PersistentDataType.STRING)) {
                String value = dataContainer.get(key, PersistentDataType.STRING);

                return DomiNPCClickType.valueOf(value);
            }
        }
        return DomiNPCClickType.NONE;
    }
}
