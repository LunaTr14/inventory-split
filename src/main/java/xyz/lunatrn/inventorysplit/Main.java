package xyz.lunatrn.inventorysplit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    private boolean isEnabled = true;

    public static float ITEM_LOSS_PERCENTAGE = 0.25f;

    public EventHandler eventHandler;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.eventHandler = new EventHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp() && label.equalsIgnoreCase("inventory_split") && args.length > 0){
            if(args[0] == "enable"){
                isEnabled = true;
            }
            else if(args[0] == "disable"){
                isEnabled = false;
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
