package eurymachus.mtl.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import slimevoid.lib.ICommonProxy;
import slimevoid.lib.ICore;
import slimevoid.lib.util.BlockRemover;
import slimevoid.lib.core.Core;
import slimevoid.lib.util.RecipeRemover;
import slimevoid.lib.core.SlimevoidCore;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class MTLInit {
	public static ICore MTL;
	private static boolean initialized = false;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTL = new Core(proxy);
		MTL.setModName("MultiTexturedLevers");
		MTL.setModChannel("MTL");
		MTLCore.configFile = new File(
				MTLInit.MTL.getProxy().getMinecraftDir(),
					"config/MultiTexturedLevers.cfg");
		MTLCore.configuration = new Configuration(MTLCore.configFile);
		load();
	}

	public static void load() {
		MTLCore.configurationProperties();
		SlimevoidCore.console(MTL.getModName(), "Removing Recipies...");
		RecipeRemover.registerItemRecipeToRemove(Block.lever);
		RecipeRemover.removeCrafting();
		SlimevoidCore.console(MTL.getModName(), "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.lever);
		SlimevoidCore.console(MTL.getModName(), "Registering items...");
		MTLCore.addItems();
		SlimevoidCore.console(MTL.getModName(), "Registering blocks...");
		MTLCore.registerBlocks();
		MTL.getProxy().registerRenderInformation();
		SlimevoidCore.console(MTL.getModName(), "Naming items...");
		MTLCore.addItemNames();
		SlimevoidCore.console(MTL.getModName(), "Registering recipes...");
		MTLCore.addRecipes();
	}

	public static int getDamageValue(IBlockAccess blockaccess, int x, int y, int z) {
		TileEntity tileentity = blockaccess.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityMTLever) {
			TileEntityMTLever tileentitymtlever = (TileEntityMTLever) tileentity;
			return tileentitymtlever.getTextureValue();
		}
		return 0;
	}
}
