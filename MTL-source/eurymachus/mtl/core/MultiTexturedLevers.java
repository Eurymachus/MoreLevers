package eurymachus.mtl.core;

import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mtl.core.lib.CoreLib;
import eurymachus.mtl.proxy.CommonProxy;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		dependencies = CoreLib.MOD_DEPENDENCIES,
		version = CoreLib.MOD_VERSION)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { CoreLib.MOD_CHANNEL },
		packetHandler = CommonProxy.class,
		connectionHandler = CommonProxy.class)
public class MultiTexturedLevers {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
	public static ICommonProxy	proxy;

	@PreInit
	public void MultiTexturedLeversPreInit(FMLPreInitializationEvent event) {
	}

	@Init
	public void MultiTexturedLeversInit(FMLInitializationEvent event) {
	}

	@PostInit
	public void MultiTexturedLeversPostInit(FMLPostInitializationEvent event) {
		MTLInit.initialize();
	}
}