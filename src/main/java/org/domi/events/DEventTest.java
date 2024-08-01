package org.domi.events;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DEventTest implements Listener {
    public DEventTest(NPCRightClickEvent event) {
        event.getNPC();
    }
}
