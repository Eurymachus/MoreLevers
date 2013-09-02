package eurymachus.mtl.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import slimevoidlib.IPacketHandling;
import cpw.mods.fml.client.registry.RenderingRegistry;
import eurymachus.mtl.client.network.ClientPacketHandler;
import eurymachus.mtl.client.render.RenderMTLever;
import eurymachus.mtl.core.MTLCore;
import eurymachus.mtl.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public String getMinecraftDir() {
		return Minecraft.getMinecraft().mcDataDir.getPath();
	}

	@Override
	public void registerRenderInformation() {
		//MinecraftForgeClient.preloadTexture(MTLInit.MTL.getBlockSheet());
		MTLCore.mtLeverBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(
				MTLCore.mtLeverBlockRenderID,
				new RenderMTLever());
	}

	@Override
	public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
	}

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
