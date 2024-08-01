package org.domi.events;

import net.citizensnpcs.api.event.NPCEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.domi.events.enums.DomiNPCClickType;

public class DomiNPCClickEvent extends NPCEvent implements Cancellable {
    private boolean cancelled = false;
    private final Player clicker;
    private final DomiNPCClickType npcType;
    private static final HandlerList handlers = new HandlerList();

    protected DomiNPCClickEvent(NPC npc, Player clicker, DomiNPCClickType npcType) {
        super(npc);
        this.clicker = clicker;
        this.npcType = npcType;
    }

    public Player getClicker() {
        return this.clicker;
    }

    public DomiNPCClickType getNPCType() {
        return this.npcType;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlersList() {
        return handlers;
    }
}
