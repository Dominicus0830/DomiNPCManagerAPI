package org.domi.events.interfaces;

import org.bukkit.event.EventHandler;
import org.domi.events.DomiNPCClickEvent;
import org.domi.events.DomiNPCRightClickEvent;

public interface EventsInterface {
    /*@EventHandler
    void onClickDomiNPC(DomiNPCClickEvent event);*/

    @EventHandler
    void onRightClickDomiNPC(DomiNPCRightClickEvent event);
}
