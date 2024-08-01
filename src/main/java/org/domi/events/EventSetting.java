package org.domi.events;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.ClickRedirectTrait;
import net.citizensnpcs.trait.CommandTrait;
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventSetting implements Listener {
    public EventSetting() {
    }

    private static boolean SUPPORT_STOP_USE_ITEM = true;

    public void ClickNPC(PlayerInteractEntityEvent event) {

        NPC npc = CitizensAPI.getNPCRegistry().getNPC(event.getRightClicked());
        if (npc == null) {
            return;
        }

        ClickRedirectTrait crt = npc.getTraitNullable(ClickRedirectTrait.class);
        if (crt != null && (npc = crt.getRedirectNPC()) == null)
            return;

        Player player = event.getPlayer();
        DomiNPCRightClickEvent rightClickEvent = new DomiNPCRightClickEvent(npc, player, );
        Bukkit.getPluginManager().callEvent(rightClickEvent);

        if (rightClickEvent.isCancelled()) {
            event.setCancelled(true);
            return;
        }
        if (event.getPlayer().getItemInHand().getType() == Material.NAME_TAG) {
            rightClickEvent.setCancelled(npc.isProtected());
        }
        if (rightClickEvent.isCancelled()) {
            rightClickEvent.setCancelled(true);
        }
        if (event.isCancelled()) {
            if (SUPPORT_STOP_USE_ITEM) {
                try {
                    PlayerAnimation.STOP_USE_ITEM.play(player);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(CitizensAPI.getPlugin(),
                            () -> PlayerAnimation.STOP_USE_ITEM.play(player));
                } catch (UnsupportedOperationException e) {
                    SUPPORT_STOP_USE_ITEM = false;
                }
            }
        }
    }
}
