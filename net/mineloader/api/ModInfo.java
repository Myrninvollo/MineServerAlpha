package net.mineloader.api;

import net.mineloader.main.MineLoader;

public final class ModInfo {
	public String Author;
	public String mod_loc_name;
	public String mod_unloc_name;
	public MinecraftVersion MC_version;
	public String mod_version;
	public Class redirect;
	public String path;
	public ModInfo(Mod m , String auth , String version , String Path){
		MC_version = MinecraftVersion.current;
		mod_version = version;
		mod_unloc_name = m.UnlocalizedName;
		mod_loc_name = m.LocalizedName;
		Author = auth;
		redirect = m.getClass();
		path = Path;
	}
}
