package com.gmail.nossr50.commands.party;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.McMMO;
import com.gmail.nossr50.commands.CommandHelper;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.party.Party;
import com.gmail.nossr50.util.Users;

//TODO: Make this work from console.
public class InviteCommand implements CommandExecutor {
    private final McMMO plugin;

    public InviteCommand(McMMO instance) {
        this.plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String usage = ChatColor.RED + "Proper usage is /invite <player>"; //TODO: Needs more locale.

        if (CommandHelper.noConsoleUsage(sender)) {
            return true;
        }

        if (CommandHelper.noCommandPermissions(sender, "mcmmo.commands.party")) {
            return true;
        }

        switch (args.length) {
        case 1:
            Player player = (Player) sender;
            PlayerProfile PP = Users.getProfile(player);

            Party partyInstance = Party.getInstance();

            if (!PP.inParty()) {
                player.sendMessage(LocaleLoader.getString("Commands.Party.None"));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target != null) {
                if (partyInstance.canInvite(player, PP)) {
                    PlayerProfile PPt = Users.getProfile(target);
                    PPt.modifyInvite(PP.getParty());

                    player.sendMessage(LocaleLoader.getString("Commands.Invite.Success"));

                    target.sendMessage(LocaleLoader.getString("Commands.Party.Invite.0", new Object[] { PPt.getInvite(), player.getName() }));
                    target.sendMessage(LocaleLoader.getString("Commands.Party.Invite.1"));
                    return true;
                }
                else {
                    player.sendMessage(LocaleLoader.getString("Party.Locked"));
                    return true;
                }
            }
            else {
                player.sendMessage(LocaleLoader.getString("Party.Player.Invalid"));
                return true;
            }

        default:
            sender.sendMessage(usage);
            return true;
        }
    }
}
