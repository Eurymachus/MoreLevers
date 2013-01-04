package eurymachus.mtl.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mtl.network.MTLConnection;
import eurysmods.api.ICommonProxy;

@Mod(
		modid = "MultiTexturedLevers",
		name = "Multi-Textured Levers",
		dependencies = "after:EurysCore",
		version = "2.0.0.0")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { "MTL" },
		packetHandler = MTLConnection.class,
		connectionHandler = MTLConnection.class)
public class MultiTexturedLevers {
	@SidedProxy(
			clientSide = "eurymachus.mtl.client.proxy.ClientProxy",
			serverSide = "eurymachus.mtl.proxy.CommonProxy")
	public static ICommonProxy proxy;

	@Init
	public void MultiTexturedLeversInit(FMLInitializationEvent event) {

	}

	@PreInit
	public void MultiTexturedLeversPreInit(FMLPreInitializationEvent event) {

	}

	@PostInit
	public void MultiTexturedLeversPostInit(FMLPostInitializationEvent event) {
		MTLInit.initialize(proxy);
	}
}