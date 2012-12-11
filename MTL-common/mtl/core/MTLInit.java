package mtl.core;

import java.io.File;

import mtl.tileentities.TileEntityMTLever;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.Configuration;
import eurysmods.api.ICommonProxy;
import eurysmods.api.ICore;
import eurysmods.core.BlockRemover;
import eurysmods.core.Core;
import eurysmods.core.EurysCore;
import eurysmods.core.RecipeRemover;

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
		EurysCore.console(MTL.getModName(), "Removing Recipies...");
		RecipeRemover.registerItemRecipeToRemove(Block.lever);
		RecipeRemover.removeCrafting();
		EurysCore.console(MTL.getModName(), "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.lever);
		EurysCore.console(MTL.getModName(), "Registering items...");
		MTLCore.addItems();
		EurysCore.console(MTL.getModName(), "Registering blocks...");
		MTLCore.registerBlocks();
		MTL.getProxy().registerRenderInformation();
		EurysCore.console(MTL.getModName(), "Naming items...");
		MTLCore.addItemNames();
		EurysCore.console(MTL.getModName(), "Registering recipes...");
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
