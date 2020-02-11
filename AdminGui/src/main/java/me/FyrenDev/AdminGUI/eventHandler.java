package me.FyrenDev.AdminGUI;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Calendar;
import java.util.Date;


public class eventHandler implements Listener {




    @EventHandler
    public void onItemGrab(InventoryClickEvent e){

        if (e.getView().getTitle().contains("Administrator Menu")){
            if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                ItemMeta sm = e.getCurrentItem().getItemMeta();
                ItemStack titleCard = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta tm = (SkullMeta) titleCard.getItemMeta();
                tm.setDisplayName(sm.getDisplayName());
                SkullMeta currentSkull = (SkullMeta) e.getCurrentItem().getItemMeta();
                tm.setOwningPlayer(currentSkull.getOwningPlayer());


                titleCard.setItemMeta(tm);

                ItemStack kickArrow = new ItemStack(Material.ARROW);
                ItemMeta km = kickArrow.getItemMeta();
                km.setDisplayName("Kick Player");
                kickArrow.setItemMeta(km);

                ItemStack banClock = new ItemStack(Material.CLOCK);
                ItemMeta bcm = banClock.getItemMeta();
                bcm.setDisplayName(ChatColor.DARK_RED + "Ban Player");
                banClock.setItemMeta(bcm);


                ItemStack tpToCompass = new ItemStack(Material.COMPASS);
                ItemMeta tpm = tpToCompass.getItemMeta();
                tpm.setDisplayName("Teleport To This Player");
                tpToCompass.setItemMeta(tpm);







                Inventory playerInv = Bukkit.createInventory(null, 36, ChatColor.GOLD + "Administrator Menu");
                playerInv.setItem(4, titleCard);
                for(int i = 0; i <= 3; i++){
                    playerInv.setItem(i, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
                }
                for(int a = 5; a <= 17; a++){
                    playerInv.setItem(a, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
                }
                playerInv.setItem(20, kickArrow);
                playerInv.setItem(22, banClock);
                playerInv.setItem(24, tpToCompass);
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(playerInv);

            }



            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Kick Player")){
                SkullMeta sm = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
                Player player = (Player) sm.getOwningPlayer();
                player.kickPlayer("You Have Been Kicked For Breaking The Rules");

            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Ban Player")){
                ItemStack thirtyMins = new ItemStack(Material.DIRT);
                ItemMeta tmm = thirtyMins.getItemMeta();
                tmm.setDisplayName(ChatColor.AQUA + "Thirty Minutes");
                thirtyMins.setItemMeta(tmm);

                ItemStack oneHour = new ItemStack(Material.OAK_WOOD);
                ItemMeta ohm = oneHour.getItemMeta();
                ohm.setDisplayName(ChatColor.BLUE + "One Hour");
                oneHour.setItemMeta(ohm);
                //twelve hours
                ItemStack thours = new ItemStack(Material.IRON_INGOT);
                ItemMeta tmeta = thours.getItemMeta();
                tmeta.setDisplayName(ChatColor.GRAY + "Twelve Hours");
                thours.setItemMeta(tmeta);

                ItemStack ow = new ItemStack(Material.GOLD_INGOT);
                ItemMeta om = ow.getItemMeta();
                om.setDisplayName(ChatColor.GOLD + "One Week");
                ow.setItemMeta(om);


                ItemStack perm = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta pw = perm.getItemMeta();
                pw.setDisplayName(ChatColor.DARK_RED + "Permanent Ban");
                perm.setItemMeta(pw);

                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();

                playerHead.setItemMeta(sm);

                Inventory banInv = Bukkit.createInventory(null, 18, ChatColor.GOLD + "Ban Menu");
                banInv.setItem(4, playerHead);
                banInv.setItem(10,thirtyMins);
                banInv.setItem(12, oneHour);
                banInv.setItem(14, thours);
                banInv.setItem(16, perm);
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(banInv);








            }

            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Teleport To This Player")){
                Player admin = (Player) e.getWhoClicked();
                SkullMeta sm = (SkullMeta) e.getClickedInventory().getItem(4).getItemMeta();
                admin.teleport((Entity)sm.getOwningPlayer());
            }
        }
        if(e.getView().getTitle().contains("Ban Menu")){
            if(e.getCurrentItem().getType().equals(Material.DIRT)){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName())){

                        Date end = new Date(System.currentTimeMillis() + (1000 * 60 * 30));
                        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.WHITE + "You have been banned for 30 minutes for breaking the rules.", end, e.getWhoClicked().getName());
                        p.kickPlayer("You have been banned for 30 minutes for breaking the rules.");
                    }
                }
            }
            if(e.getCurrentItem().getType().equals(Material.OAK_WOOD)){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName())){

                        Date end = new Date(System.currentTimeMillis() + (1000 * 60 * 60));
                        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.WHITE + "You have been banned for 1 Hour for breaking the rules.", end, e.getWhoClicked().getName());
                        p.kickPlayer("You have been banned for 1 Hour for breaking the rules.");
                    }
                }
            }
            if(e.getCurrentItem().getType().equals(Material.IRON_INGOT)){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName())){

                        Date end = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 12));
                        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.WHITE + "You have been banned for 12 Hour for breaking the rules.", end, e.getWhoClicked().getName());
                        p.kickPlayer("You have been banned for 12 Hours for breaking the rules.");
                    }
                }
            }
            if(e.getCurrentItem().getType().equals(Material.DIAMOND_BLOCK)){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName())){


                        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.WHITE + "You have been PERMANENTLY Banned for breaking the rules.", null, e.getWhoClicked().getName());
                        p.kickPlayer("You have been PERMANENTLY Banned for breaking the rules.");
                    }
                }
            }
        }










    }







}
