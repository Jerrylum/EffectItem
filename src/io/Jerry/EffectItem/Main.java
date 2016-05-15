package io.Jerry.EffectItem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.Jerry.EffectItem.Listeners.ClickListener;
import io.Jerry.EffectItem.Util.ConfigUtil;

public class Main extends JavaPlugin {
	public static Plugin Plugin;
	public static FileConfiguration MainConfig;
	
	public void onEnable(){
		Plugin = this;
		MainConfig = getConfig();
		MainConfig.options().copyDefaults(true);
		saveConfig();
		
		ConfigUtil.setup(MainConfig);
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ClickListener(), Plugin);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender.isOp() == false){
			sender.sendMessage("��3Effect> ��f�A�S���v��");
			return true;
		}
		
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
			reloadConfig();
			ConfigUtil.setup(MainConfig);
			sender.sendMessage("��3Effect> ��f�w���s���J�ɮ�");
		}else{
			sender.sendMessage("��3Effect> ��f�ϥΡ�a/effectitem reload ��f���s���J�ɮ�");
		}
		return true;
	}
}
