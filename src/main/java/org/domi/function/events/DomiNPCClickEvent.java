package org.domi.function.events;

import net.citizensnpcs.api.event.NPCEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.domi.function.events.enums.DomiNPCClickType;

public class DomiNPCClickEvent extends NPCEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player clicker;
    private final DomiNPCClickType npcType;
    private boolean cancelled = false;

    protected DomiNPCClickEvent(NPC npc, Player clicker, DomiNPCClickType npcType) {
        super(npc);
        this.clicker = clicker;
        this.npcType = npcType;
    }

    public static HandlerList getHandlersList() {
        return handlers;
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
}
