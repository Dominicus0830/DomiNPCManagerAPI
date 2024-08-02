package org.domi.function.events;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.domi.function.events.enums.DomiNPCClickType;

public class DomiNPCRightClickEvent extends DomiNPCClickEvent {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;

    public DomiNPCRightClickEvent(NPC npc, Player clicker, DomiNPCClickType npcType) {
        super(npc, clicker, npcType);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
