package io.Jerry.EffectItem.Class;

import org.bukkit.configuration.ConfigurationSection;

import io.Jerry.EffectItem.Class.EffectItem.EffectCause.EffectType;

public class EffectItem {
	private String lore;
	private EffectCause AttackEntity;
	private EffectCause AttackPlayer;
	private EffectCause ClickEntity;
	private EffectCause ClickPlayer;
	private EffectCause ClickBlock;
	
	public EffectItem(String lore, ConfigurationSection c) {
		this.lore = lore.replace('&', '¡±');
		if(c.isConfigurationSection("AttackEntity")){
			AttackEntity = new EffectCause(EffectType.Entity,c.getConfigurationSection("AttackEntity"));
		}
		if(c.isConfigurationSection("AttackPlayer")){
			AttackPlayer = new EffectCause(EffectType.Player,c.getConfigurationSection("AttackPlayer"));
		}
		if(c.isConfigurationSection("ClickEntity")){
			ClickEntity = new EffectCause(EffectType.Entity,c.getConfigurationSection("ClickEntity"));
		}
		if(c.isConfigurationSection("ClickPlayer")){
			ClickPlayer = new EffectCause(EffectType.Player,c.getConfigurationSection("ClickPlayer"));
		}
		if(c.isConfigurationSection("ClickBlock")){
			ClickBlock = new EffectCause(EffectType.Block,c.getConfigurationSection("ClickBlock"));
		}
	}
	
	public String getLore() {
		return lore;
	}

	public EffectCause getAttackEntity() {
		return AttackEntity;
	}

	public EffectCause getAttackPlayer() {
		return AttackPlayer;
	}
	
	public EffectCause getClickEntity() {
		return ClickEntity;
	}
	
	public EffectCause getClickPlayer() {
		return ClickPlayer;
	}

	public EffectCause getClickBlock() {
		return ClickBlock;
	}
	
	public static class EffectCause{
		public static enum EffectType{
			Block,Entity,Player
		}
		
		private boolean Break;
		private int Colddown;
		private Target Owner;
		private Target Entity;
		
		public EffectCause(EffectType type, ConfigurationSection c){
			Break = c.getBoolean("Break");
			Colddown = (int)(c.getDouble("Colddown") *20d);
			Owner = new Target();
			Owner.setSound(c.getStringList("Owner.Sound"));
			Owner.setPotion(c.getStringList("Owner.Potion"));
			
			Entity = new Target();
			if(type == EffectType.Entity){
				Entity.setPotion(c.getStringList("Entity.Potion"));
			}else if(type == EffectType.Player){
				Entity.setSound(c.getStringList("Player.Sound"));
				Entity.setPotion(c.getStringList("Player.Potion"));
			}
		}

		public boolean isBreak() {
			return Break;
		}

		public int getColddown() {
			return Colddown;
		}

		public Target getOwner() {
			return Owner;
		}
		
		public Target getEntity() {
			return Entity;
		}
	}
}
