package io.Jerry.EffectItem.Class;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Lists;

public class Target {
	private List<String> sound = Lists.newArrayList();
	private List<PotionEffect> Potion = Lists.newArrayList();
	
	public List<String> getSound() {
		return sound;
	}
	
	public void setSound(List<String> Sound) {
		if(Sound != null){
			sound = Sound;
		}
	}
	
	public List<PotionEffect> getPotion() {
		return Potion;
	}
	
	public void setPotion(List<String> potion) {
		if(potion != null){
			String[] split;
			for(String str : potion){
				split = str.split(",");
				Potion.add(new PotionEffect(
						PotionEffectType.getByName(split[0]),
						Integer.parseInt(split[2]) *20,
						Integer.parseInt(split[1]) 
					));
			}
		}
	}
	
	public void run(LivingEntity e){
		for(PotionEffect pe : Potion){
			e.addPotionEffect(pe);
		}
		if(e instanceof Player){
			Player p = (Player)e;
			String[] split;
			for(String str : sound){
				split = str.split(",");
				p.playSound(
						p.getLocation(), 
						Sound.valueOf(split[0]), 
						Integer.parseInt(split[1]), 
						Integer.parseInt(split[2])
					);
			}
		}
	}
}
