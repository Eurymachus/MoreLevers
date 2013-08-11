package eurymachus.mtl.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import slimevoidlib.core.SlimevoidCore;
import slimevoidlib.core.SlimevoidLib;
import slimevoidlib.util.BlockRemover;
import slimevoidlib.util.RecipeRemover;
import eurymachus.mtl.core.lib.CoreLib;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class MTLInit {
	private static boolean initialized = false;

	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		MTLCore.configFile = new File(
				SlimevoidLib.proxy.getMinecraftDir(),
					"config/MultiTexturedLevers.cfg");
		MTLCore.configuration = new Configuration(MTLCore.configFile);
		load();
	}

	public static void load() {
		MTLCore.configurationProperties();
		SlimevoidCore.console(CoreLib.MOD_ID, "Removing Recipies...");
		RecipeRemover.registerItemRecipeToRemove(Block.lever);
		RecipeRemover.removeCrafting();
		SlimevoidCore.console(CoreLib.MOD_ID, "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.lever);
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering items...");
		MTLCore.addItems();
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering blocks...");
		MTLCore.registerBlocks();
		MultiTexturedLevers.proxy.registerRenderInformation();
		SlimevoidCore.console(CoreLib.MOD_ID, "Naming items...");
		MTLCore.addItemNames();
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering recipes...");
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
