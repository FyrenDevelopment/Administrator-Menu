package me.FyrenDev.AdminGUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class commandHandler implements CommandExecutor {


@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if(command.getName().equalsIgnoreCase("AdminMenu")){
       if(sender instanceof Player) {
           Inventory adminMenu = Bukkit.createInventory(null, 36, ChatColor.GOLD + "Administrator Menu");
           ItemStack nextArrow = new ItemStack(Material.ARROW);
           nextArrow.getItemMeta().setDisplayName("Next");
           if (Bukkit.getOnlinePlayers().size() > 18) {
               ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
               SkullMeta sm = (SkullMeta) skull.getItemMeta();
               adminMenu.setItem(8, nextArrow);
               int c = 18;
               while (c <= 35){
                   for(Player p : Bukkit.getOnlinePlayers()){
                       sm.setDisplayName(p.getDisplayName());
                       sm.setOwningPlayer(p.getPlayer());
                       skull.setItemMeta(sm);
                       adminMenu.setItem(c, skull);
                       c = c + 1;
                   }
               }
           } else {

               int count = 18;
               ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
               SkullMeta sm = (SkullMeta) skull.getItemMeta();
               for (Player p : Bukkit.getOnlinePlayers()) {

                   sm.setDisplayName(p.getDisplayName());
                   sm.setOwningPlayer(p);
                   skull.setItemMeta(sm);
                   adminMenu.setItem(count, skull);
                   count = count + 1;


               }
                Player player = (Player) sender;
               player.openInventory(adminMenu);

           }
       }








    }


    return false;
}
















}



