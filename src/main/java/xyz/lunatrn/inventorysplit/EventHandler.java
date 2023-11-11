package xyz.lunatrn.inventorysplit;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;

public class EventHandler implements Listener {
    private Main plugin;

    private static int SPLIT_DELAY_SECONDS = 15;

    private void registerListener(){
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
    private Random random;
    EventHandler(Main plugin){
        this.plugin = plugin;
        random = new Random();
        registerListener();
    }

    @org.bukkit.event.EventHandler
    public boolean onPlayerDeath(PlayerDeathEvent deathEvent){

        LinkedList<ItemStack> inventoryContent = new LinkedList<>();
        for(ItemStack cont : deathEvent.getPlayer().getInventory().getContents()){
            inventoryContent.add(cont);
        }

        deathEvent.getDrops().clear();
        Collection<? extends Player> onlinePlayers = plugin.getServer().getOnlinePlayers();
        plugin.getServer().broadcast(Component.text("Items of " + deathEvent.getPlayer().getName() + " Will be split in " + SPLIT_DELAY_SECONDS));
        new BukkitRunnable() {
            @Override
            public void run() {

                for(ItemStack item : inventoryContent){
                    if(item != null){
                        getRandomPlayer(onlinePlayers).getInventory().addItem(item);
                    }
                }
            }
        }.runTaskLaterAsynchronously(plugin,20*SPLIT_DELAY_SECONDS);
        return true;
    }
    private Player getRandomPlayer(Collection<? extends Player>  playerList){
        if(playerList.size() <= 1 ){
            return (Player) playerList.toArray()[0];
        }
        return (Player) playerList.toArray()[(random.nextInt(playerList.size()))];
    }
}
