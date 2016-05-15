package io.Jerry.EffectItem.Util;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.Lists;

import io.Jerry.EffectItem.Main;
import io.Jerry.EffectItem.Class.EffectItem;

public class ConfigUtil {
	public static FileConfiguration c;
	public static List<EffectItem> list;
	
	public static void setup(FileConfiguration c){
		ConfigUtil.c = c;
		list = Lists.newArrayList();
		if(c.isConfigurationSection("Lore")){
			for(String lore : c.getConfigurationSection("Lore").getKeys(false)){
				try{
					list.add(new EffectItem(lore,c.getConfigurationSection("Lore." + lore) ));
					Main.Plugin.getLogger().info("Loaded " + lore);
				}catch(Exception ex){
					Main.Plugin.getLogger().info("Failed to load " + lore);
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static List<EffectItem> getLoaded(){
		return list;
	}
}
