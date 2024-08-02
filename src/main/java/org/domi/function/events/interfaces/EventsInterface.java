package org.domi.function.events.interfaces;

import org.bukkit.event.EventHandler;
import org.domi.function.events.DomiNPCRightClickEvent;

public interface EventsInterface {
    /*@EventHandler
    void onClickDomiNPC(DomiNPCClickEvent event);*/

    @EventHandler
    void onRightClickDomiNPC(DomiNPCRightClickEvent event);
}
