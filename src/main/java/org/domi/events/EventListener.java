package org.domi.events;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.domi.DomiNPCManagerAPI;
import org.domi.function.commands.PDCManager;
import org.domi.function.events.DomiNPCRightClickEvent;
import org.domi.function.events.enums.DomiNPCClickType;

public class EventListener implements Listener {
    private static boolean SUPPORT_STOP_USE_ITEM = true;
    private final DomiNPCManagerAPI plugin = DomiNPCManagerAPI.getPlugin();
    private final PDCManager pdcManager = new PDCManager();

    public EventListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClickNPC(PlayerInteractEntityEvent event) {
        NPC npc = CitizensAPI.getNPCRegistry().getNPC(event.getRightClicked());

        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            event.setCancelled(true);
            return;
        }

        if (npc == null) {
            return;
        }

        DomiNPCClickType NPCType = pdcManager.getNPCPDCValue(plugin, npc);

        Player player = event.getPlayer();
        DomiNPCRightClickEvent rightClickEvent = new DomiNPCRightClickEvent(npc, player, NPCType);
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
                    Bukkit.getScheduler().scheduleSyncDelayedTask(CitizensAPI.getPlugin(), () -> PlayerAnimation.STOP_USE_ITEM.play(player));
                } catch (UnsupportedOperationException e) {
                    SUPPORT_STOP_USE_ITEM = false;
                }
            }
        }
    }
}

