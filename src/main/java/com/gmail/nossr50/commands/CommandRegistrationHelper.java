package com.gmail.nossr50.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.PluginCommand;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.commands.admin.AddlevelsCommand;
import com.gmail.nossr50.commands.admin.AddxpCommand;
import com.gmail.nossr50.commands.admin.McgodCommand;
import com.gmail.nossr50.commands.admin.McrefreshCommand;
import com.gmail.nossr50.commands.admin.MmoeditCommand;
import com.gmail.nossr50.commands.admin.SkillresetCommand;
import com.gmail.nossr50.commands.admin.XprateCommand;
import com.gmail.nossr50.commands.player.InspectCommand;
import com.gmail.nossr50.commands.player.McabilityCommand;
import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.skills.acrobatics.AcrobaticsCommand;
import com.gmail.nossr50.skills.archery.ArcheryCommand;
import com.gmail.nossr50.skills.axes.AxesCommand;
import com.gmail.nossr50.skills.excavation.ExcavationCommand;
import com.gmail.nossr50.skills.fishing.FishingCommand;
import com.gmail.nossr50.skills.herbalism.HerbalismCommand;
import com.gmail.nossr50.skills.mining.MiningCommand;
import com.gmail.nossr50.skills.repair.RepairCommand;
import com.gmail.nossr50.skills.smelting.SmeltingCommand;
import com.gmail.nossr50.skills.swords.SwordsCommand;
import com.gmail.nossr50.skills.taming.TamingCommand;
import com.gmail.nossr50.skills.unarmed.UnarmedCommand;
import com.gmail.nossr50.skills.utilities.SkillType;
import com.gmail.nossr50.skills.woodcutting.WoodcuttingCommand;
import com.gmail.nossr50.util.Misc;

public final class CommandRegistrationHelper {
    private CommandRegistrationHelper() {};
    private static String permissionsMessage = LocaleLoader.getString("mcMMO.NoPermission");

    public static void registerSkillCommands() {
        for (SkillType skill : SkillType.values()) {
            String commandName = skill.toString().toLowerCase();
            String localizedName = LocaleLoader.getString(Misc.getCapitalized(commandName) + ".SkillName").toLowerCase();

            List<String> aliasList = new ArrayList<String>();
            aliasList.add(localizedName);

            PluginCommand command;

            // Make us play nice with Essentials
            if (skill == SkillType.REPAIR && mcMMO.p.getServer().getPluginManager().isPluginEnabled("Essentials")) {
                command = mcMMO.p.getCommand("mcrepair");
            }
            else {
                command = mcMMO.p.getCommand(commandName);
            }

            command.setAliases(aliasList);
            command.setDescription(LocaleLoader.getString("Commands.Description.Skill", Misc.getCapitalized(localizedName)));
            command.setPermission("mcmmo.commands." + commandName);
            command.setPermissionMessage(permissionsMessage);

            switch (skill) {
            case ACROBATICS:
                command.setExecutor(new AcrobaticsCommand());
                break;

            case ARCHERY:
                command.setExecutor(new ArcheryCommand());
                break;

            case AXES:
                command.setExecutor(new AxesCommand());
                break;

            case EXCAVATION:
                command.setExecutor(new ExcavationCommand());
                break;

            case FISHING:
                command.setExecutor(new FishingCommand());
                break;

            case HERBALISM:
                command.setExecutor(new HerbalismCommand());
                break;

            case MINING:
                command.setExecutor(new MiningCommand());
                break;

            case REPAIR:
                command.setExecutor(new RepairCommand());
                break;

            case SMELTING:
                command.setExecutor(new SmeltingCommand());
                break;

            case SWORDS:
                command.setExecutor(new SwordsCommand());
                break;

            case TAMING:
                command.setExecutor(new TamingCommand());
                break;

            case UNARMED:
                command.setExecutor(new UnarmedCommand());
                break;

            case WOODCUTTING:
                command.setExecutor(new WoodcuttingCommand());
                break;

            default:
                break;
            }
        }
    }

    public static void registerAddlevelsCommand() {
        PluginCommand command = mcMMO.p.getCommand("addlevels");
        command.setDescription(LocaleLoader.getString("Commands.Description.addlevels"));
        command.setPermission("mcmmo.commands.addlevels;mcmmo.commands.addlevels.others");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.3", "addlevels", "[" + LocaleLoader.getString("Commands.Usage.Player") + "]", "<" + LocaleLoader.getString("Commands.Usage.Skill") + ">", "<" + LocaleLoader.getString("Commands.Usage.Level") + ">"));
        command.setExecutor(new AddlevelsCommand());
    }

    public static void registerAddxpCommand() {
        PluginCommand command = mcMMO.p.getCommand("addxp");
        command.setDescription(LocaleLoader.getString("Commands.Description.addxp"));
        command.setPermission("mcmmo.commands.addxp;mcmmo.commands.addxp.others");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.3", "addxp", "[" + LocaleLoader.getString("Commands.Usage.Player") + "]", "<" + LocaleLoader.getString("Commands.Usage.Skill") + ">", "<" + LocaleLoader.getString("Commands.Usage.XP") + ">"));
        command.setExecutor(new AddxpCommand());
    }

    public static void registerMcgodCommand() {
        PluginCommand command = mcMMO.p.getCommand("mcgod");
        command.setDescription(LocaleLoader.getString("Commands.Description.mcgod"));
        command.setPermission("mcmmo.commands.mcgod;mcmmo.commands.mcgod.others");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.1", "mcgod", "[" + LocaleLoader.getString("Commands.Usage.Player") + "]"));
        command.setExecutor(new McgodCommand());
    }

    public static void registerMcrefreshCommand() {
        PluginCommand command = mcMMO.p.getCommand("mcrefresh");
        command.setDescription(LocaleLoader.getString("Commands.Description.mcrefresh"));
        command.setPermission("mcmmo.commands.mcrefresh;mcmmo.commands.mcrefresh.others");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.1", "mcrefresh", "[" + LocaleLoader.getString("Commands.Usage.Player") + "]"));
        command.setExecutor(new McrefreshCommand());
    }

    public static void registerMmoeditCommand() {
        PluginCommand command = mcMMO.p.getCommand("mmoedit");
        command.setDescription(LocaleLoader.getString("Commands.Description.mmoedit"));
        command.setPermission("mcmmo.commands.mmoedit;mcmmo.commands.mmoedit.others");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.3", "mmoedit", "[" + LocaleLoader.getString("Commands.Usage.Player") + "]", "<" + LocaleLoader.getString("Commands.Usage.Skill") + ">", "<" + LocaleLoader.getString("Commands.Usage.Level") + ">"));
        command.setExecutor(new MmoeditCommand());
    }

    public static void registerSkillresetCommand() {
        PluginCommand command = mcMMO.p.getCommand("skillreset");
        command.setDescription(LocaleLoader.getString("Commands.Description.skillreset"));
        command.setPermission("mcmmo.commands.skillreset;mcmmo.commands.skillreset.others"); // Only need the main ones, not the individual skill ones
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.2", "skillreset", "[" + LocaleLoader.getString("Commands.Usage.Player") + "]", "<" + LocaleLoader.getString("Commands.Usage.Skill") + ">"));
        command.setExecutor(new SkillresetCommand());
    }

    public static void registerXprateCommand() {
        List<String> aliasList = new ArrayList<String>();
        aliasList.add("mcxprate");

        PluginCommand command = mcMMO.p.getCommand("xprate");
        command.setDescription(LocaleLoader.getString("Commands.Description.xprate"));
        command.setPermission("mcmmo.commands.xprate;mcmmo.commands.xprate.reset;mcmmo.commands.xprate.set");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.2", "xprate", "<" + LocaleLoader.getString("Commands.Usage.Rate") + ">", "<" + LocaleLoader.getString("Commands.Usage.True") + "|" + LocaleLoader.getString("Commands.Usage.False")+ ">"));
        command.setUsage(command.getUsage() + "\n" + LocaleLoader.getString("Commands.Usage.1", "xprate", "reset"));
        command.setAliases(aliasList);
        command.setExecutor(new XprateCommand());
    }

    public static void registerInspectCommand() {
        PluginCommand command = mcMMO.p.getCommand("inspect");
        command.setDescription(LocaleLoader.getString("Commands.Description.inspect"));
        command.setPermission("mcmmo.commands.inspect;mcmmo.commands.inspect.far;mcmmo.commands.inspect.offline");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.1", "inspect", "<" + LocaleLoader.getString("Commands.Usage.Player") + ">"));
        command.setExecutor(new InspectCommand());
    }

    public static void registerMcabilityCommand() {
        PluginCommand command = mcMMO.p.getCommand("mcability");
        command.setDescription(LocaleLoader.getString("Commands.Description.mcability"));
        command.setPermission("mcmmo.commands.ability;mcmmo.commands.mcability.others");
        command.setPermissionMessage(permissionsMessage);
        command.setUsage(LocaleLoader.getString("Commands.Usage.1", "mcability", "<" + LocaleLoader.getString("Commands.Usage.Player") + ">"));
        command.setExecutor(new McabilityCommand());
    }
}