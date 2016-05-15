package io.Jerry.EffectItem.Listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.Jerry.EffectItem.Class.EffectItem;
import io.Jerry.EffectItem.Class.EffectItem.EffectCause;
import io.Jerry.EffectItem.Util.ConfigUtil;

@SuppressWarnings("deprecation")
public class ClickListener implements Listener {
	@EventHandler
	public void ede(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player == false){
			return;
		}
		
		Player p = (Player)e.getDamager();
		ItemStack Item = p.getItemInHand();
		if(Item == null || Item.getType() == Material.AIR || Item.hasItemMeta() == false){
			return;
		}

		ItemMeta IM = Item.getItemMeta();
		if(IM.hasLore() == false){
			return;
		}
		List<String> lore = IM.getLore();
		Entity target = e.getEntity();
		EffectCause cause;
		boolean breaked = false;
		for(EffectItem EI : ConfigUtil.getLoaded()){
			for(String str : lore){

				if(str.contains(EI.getLore())){	
					cause = EI.getAttackEntity();
					if(target instanceof Player){
						cause = EI.getAttackPlayer();
						cause.getEntity().run((LivingEntity) target);

					}else if(target instanceof LivingEntity){

						cause.getEntity().run((LivingEntity) target);
					}
					cause.getOwner().run(p);
					if(cause.isBreak() && breaked == false){
						breaked = true;
						if(Item.getAmount() == 1){
							p.setItemInHand(null);
						}else{
							Item.setAmount(Item.getAmount() -1);
							p.setItemInHand(Item);
						}
					}
					
					break; 
				}
			}
		}
		
	}
	
	@EventHandler
	public void pie(PlayerInteractEntityEvent e){
		Player p = (Player)e.getPlayer();
		ItemStack Item = p.getItemInHand();
		if(Item == null || Item.getType() == Material.AIR || Item.hasItemMeta() == false){
			return;
		}
		
		ItemMeta IM = Item.getItemMeta();
		if(IM.hasLore() == false){
			return;
		}
		List<String> lore = IM.getLore();
		Entity target = e.getRightClicked();
		
		EffectCause cause;
		boolean breaked = false;
		for(EffectItem EI : ConfigUtil.getLoaded()){
			for(String str : lore){
				if(str.contains(EI.getLore())){	
					cause = EI.getClickEntity();
					if(target instanceof Player){
						cause = EI.getClickPlayer();
						cause.getEntity().run((LivingEntity) target);
					}else if(target instanceof LivingEntity){
						cause.getEntity().run((LivingEntity) target);
					}
					
					cause.getOwner().run(p);
					if(cause.isBreak() && breaked == false){
						breaked = true;
						if(Item.getAmount() == 1){
							p.setItemInHand(null);
						}else{
							Item.setAmount(Item.getAmount() -1);
							p.setItemInHand(Item);
						}
					}
					
					break; 
				}
			}
		}
		
	}
	
	@EventHandler
	public void pi(PlayerInteractEvent e){
		Player p = (Player)e.getPlayer();
		ItemStack Item = p.getItemInHand();
		if(Item == null || Item.getType() == Material.AIR || Item.hasItemMeta() == false){
			return;
		}
		
		ItemMeta IM = Item.getItemMeta();
		if(IM.hasLore() == false){
			return;
		}
		List<String> lore = IM.getLore();
		
		EffectCause cause;
		boolean breaked = false;
		for(EffectItem EI : ConfigUtil.getLoaded()){
			for(String str : lore){
				if(str.contains(EI.getLore())){	
					cause = EI.getClickBlock();
					
					cause.getOwner().run(p);
					if(cause.isBreak() && breaked == false){
						breaked = true;
						if(Item.getAmount() == 1){
							p.setItemInHand(null);
						}else{
							Item.setAmount(Item.getAmount() -1);
							p.setItemInHand(Item);
						}
					}
					
					break; 
				}
			}
		}
		
	}
}
