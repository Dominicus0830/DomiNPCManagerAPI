package org.domi.events;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.domi.events.enums.DomiNPCClickType;

public class DomiNPCRightClickEvent extends DomiNPCClickEvent {
    private boolean cancel;
    private static final HandlerList handlers = new HandlerList();

    protected DomiNPCRightClickEvent(NPC npc, Player clicker, DomiNPCClickType npcType) {
        super(npc, clicker, npcType);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
