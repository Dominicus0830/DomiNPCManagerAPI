package org.domi.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.domi.DomiNPCManagerAPI;
import org.domi.function.commands.PDCManager;
import org.domi.function.events.enums.DomiNPCClickType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DomiNPCTypeCommand implements CommandExecutor, TabCompleter {
    private final DomiNPCManagerAPI plugin = DomiNPCManagerAPI.getPlugin();
    private final PDCManager pdcManager = new PDCManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }
        Integer npcId = CitizensAPI.getDefaultNPCSelector().getSelected(sender).getId();
        NPC npc = CitizensAPI.getNPCRegistry().getById(npcId);
        if (npc == null) {
            sender.sendMessage("선택된 NPC가 없습니다.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("명령어를 입력하세요.");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "settype":
                if (args.length < 2) {
                    sender.sendMessage("사용법: /dominpc settype [type]");
                    sender.sendMessage(npc.getFullName() + " : " + npc.getId());
                    return true;
                }
                setType(sender, npc, args[1]);
                break;
            case "removetype":
                if (args.length < 2) {
                    sender.sendMessage("사용법: /dominpc removetype [type]");
                    sender.sendMessage(npc.getFullName() + " : " + npc.getId());
                    return true;
                }
                removeType(sender, npc, args[1]);
                sender.sendMessage(npc.getFullName() + " : " + npc.getId());
                break;
            case "removealltype":
                removeAllType(sender, npc);
                sender.sendMessage(npc.getFullName() + " : " + npc.getId());
                break;
            case "getnpctype":
                getNPCType(sender, npc);
                sender.sendMessage(npc.getFullName() + " : " + npc.getId());
                break;
            default:
                sender.sendMessage("알 수 없는 명령어입니다. 도움말은 '/dominpc help'를 입력하세요.");
                break;
        }
        return true;
    }

    private void setType(CommandSender sender, NPC npc, String typeStr) {
        try {
            DomiNPCClickType type = DomiNPCClickType.valueOf(typeStr.toUpperCase());
            DomiNPCClickType currentType = pdcManager.getNPCPDCValue(npc);

            if (currentType != DomiNPCClickType.NONE) {
                sender.sendMessage("NPC 유형이 이미 설정되어 있습니다.");
                return;
            }

            pdcManager.setNPCPDC(npc, type);
            sender.sendMessage("NPC 유형이 " + type + "(으)로 설정되었습니다.");
        } catch (IllegalArgumentException e) {
            sender.sendMessage("유효하지 않은 유형입니다: " + typeStr);
        }
    }

    private void removeType(CommandSender sender, NPC npc, String typeStr) {
        try {
            DomiNPCClickType type = DomiNPCClickType.valueOf(typeStr.toUpperCase());

            if (pdcManager.hasNPCPDCValue(npc, type)) {
                pdcManager.removeNPCPDC(npc, type);
                sender.sendMessage("NPC 유형 " + type + "이(가) 제거되었습니다.");
            } else {
                sender.sendMessage("NPC에 설정된 유형이 아니거나 이미 제거되었습니다: " + type);
            }
        } catch (IllegalArgumentException e) {
            sender.sendMessage("유효하지 않은 유형입니다: " + typeStr);
        }
    }

    private void removeAllType(CommandSender sender, NPC npc) {
        DomiNPCClickType currentType = pdcManager.getNPCPDCValue(npc);

        if (currentType != null && currentType != DomiNPCClickType.NONE) {
            pdcManager.removeAllNPCPDC(npc);
            sender.sendMessage("모든 NPC 유형이 제거되었습니다.");
        } else {
            sender.sendMessage("제거할 유형이 없습니다. NPC에 유형이 설정되어 있지 않습니다.");
        }
    }

    private void getNPCType(CommandSender sender, NPC npc) {
        DomiNPCClickType type = pdcManager.getNPCPDCValue(npc);
        sender.sendMessage("NPC 유형은 " + type + "입니다.");
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return Collections.emptyList();
        }

        if (args.length == 1) {
            return Arrays.asList("settype", "removetype", "removealltype", "getnpctype").stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        } else if (args.length == 2 && (args[0].equalsIgnoreCase("settype") || args[0].equalsIgnoreCase("removetype"))) {
            return Arrays.stream(DomiNPCClickType.values()).map(Enum::name).map(String::toUpperCase) // 대문자로 변환
                    .filter(s -> s.startsWith(args[1].toUpperCase())) // 입력값 대문자로 변환
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
