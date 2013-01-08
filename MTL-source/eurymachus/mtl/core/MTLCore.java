package eurymachus.mtl.core;

import java.io.File;

import slimevoid.lib.ICommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import eurymachus.mtl.blocks.BlockMTLever;
import eurymachus.mtl.items.ItemMTLever;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class MTLCore {
	public static File configFile;
	public static Configuration configuration;
	public static boolean initialized = false;
	public static int mtLeverBlockRenderID;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTLInit.initialize(proxy);
	}

	public static void addItems() {
		MTLBlocks.mtLever.me = new BlockMTLever(
				MTLBlocks.mtLever.id,
					TileEntityMTLever.class,
					0.5F,
					Block.soundStoneFootstep,
					true,
					true,
					"mtLever");
		GameRegistry.registerTileEntity(TileEntityMTLever.class, "mtLever");
		for (MTLItemLevers lever : MTLItemLevers.values()) {
			lever.me = new ItemStack(MTLBlocks.mtLever.me, 1, lever.stackID);
		}
	}

	public static void registerBlocks() {
		for (MTLBlocks block : MTLBlocks.values()) {
			if (block != null && block.me != null) {
				GameRegistry.registerBlock(block.me, ItemMTLever.class, block.name);
			}
		}
	}

	public static void addItemNames() {
		for (MTLItemLevers lever : MTLItemLevers.values()) {
			if (lever != null && lever.me != null && lever.name != null && !lever.name
					.isEmpty()) {
				ModLoader.addName(lever.me, lever.name);
			}
		}
	}

	public static void addRecipes() {

		GameRegistry.addRecipe(MTLItemLevers.oakPlank.me, new Object[] {
				"I",
				"X",
				Character.valueOf('I'),
				new ItemStack(Item.stick, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 0) });

		GameRegistry.addRecipe(MTLItemLevers.sprucePlank.me, new Object[] {
				"I",
				"X",
				Character.valueOf('I'),
				new ItemStack(Item.stick, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 1) });

		GameRegistry.addRecipe(MTLItemLevers.birchPlank.me, new Object[] {
				"I",
				"X",
				Character.valueOf('I'),
				new ItemStack(Item.stick, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 2) });

		GameRegistry.addRecipe(MTLItemLevers.junglePlank.me, new Object[] {
				"I",
				"X",
				Character.valueOf('I'),
				new ItemStack(Item.stick, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Block.planks, 1, 3) });

		GameRegistry.addRecipe(MTLItemLevers.polishedStone.me, new Object[] {
				"I",
				"X",
				Character.valueOf('I'),
				new ItemStack(Item.stick, 1, 0),
				Character.valueOf('X'),
				Block.stoneSingleSlab });

		GameRegistry.addRecipe(MTLItemLevers.cobbleStone.me, new Object[] {
				"I",
				"X",
				Character.valueOf('I'),
				new ItemStack(Item.stick, 1, 0),
				Character.valueOf('X'),
				Block.cobblestone });

		GameRegistry.addRecipe(MTLItemLevers.iron.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.ingotIron,
				Character.valueOf('Y'),
				MTLItemLevers.cobbleStone.me });

		GameRegistry.addRecipe(MTLItemLevers.gold.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.ingotGold,
				Character.valueOf('Y'),
				MTLItemLevers.iron.me });

		GameRegistry.addRecipe(MTLItemLevers.diamond.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.diamond,
				Character.valueOf('Y'),
				MTLItemLevers.gold.me });

		FurnaceRecipes.smelting().addSmelting(
				MTLBlocks.mtLever.id,
				MTLItemLevers.iron.stackID,
				new ItemStack(Item.ingotIron, 2),
				1);
		FurnaceRecipes.smelting().addSmelting(
				MTLBlocks.mtLever.id,
				MTLItemLevers.gold.stackID,
				new ItemStack(Item.ingotGold, 2),
				2);
		FurnaceRecipes.smelting().addSmelting(
				MTLBlocks.mtLever.id,
				MTLItemLevers.diamond.stackID,
				new ItemStack(Item.diamond, 2),
				3);
	}

	public static int configurationProperties() {
		configuration.load();
		MTLBlocks.mtLever.id = Integer.parseInt(configuration.get(
				Configuration.CATEGORY_BLOCK,
				"mtLever",
				Block.lever.blockID).value);
		MTLBlocks.mtLever.name = "Multi-Textured Lever";
		MTLItemLevers.iron.name = "Iron-Clad Lever";
		MTLItemLevers.iron.stackID = 0;
		MTLItemLevers.iron.setTextureIndex(22);
		MTLItemLevers.iron.setBlockHardness(2.5F);
		MTLItemLevers.gold.name = "Gold-Plated Lever";
		MTLItemLevers.gold.stackID = 1;
		MTLItemLevers.gold.setTextureIndex(23);
		MTLItemLevers.gold.setBlockHardness(1.5F);
		MTLItemLevers.diamond.name = "Diamond-Encrusted Lever";
		MTLItemLevers.diamond.stackID = 2;
		MTLItemLevers.diamond.setTextureIndex(24);
		MTLItemLevers.diamond.setBlockHardness(2.5F);
		MTLItemLevers.oakPlank.name = "Oak Wood Lever";
		MTLItemLevers.oakPlank.stackID = 3;
		MTLItemLevers.oakPlank.setTextureIndex(4);
		MTLItemLevers.oakPlank.setBlockHardness(0.5F);
		MTLItemLevers.sprucePlank.name = "Spruce Wood Lever";
		MTLItemLevers.sprucePlank.stackID = 4;
		MTLItemLevers.sprucePlank.setTextureIndex(198);
		MTLItemLevers.sprucePlank.setBlockHardness(0.5F);
		MTLItemLevers.birchPlank.name = "Birch Wood Lever";
		MTLItemLevers.birchPlank.stackID = 5;
		MTLItemLevers.birchPlank.setTextureIndex(214);
		MTLItemLevers.birchPlank.setBlockHardness(0.5F);
		MTLItemLevers.junglePlank.name = "Jungle Wood Lever";
		MTLItemLevers.junglePlank.stackID = 6;
		MTLItemLevers.junglePlank.setTextureIndex(199);
		MTLItemLevers.junglePlank.setBlockHardness(0.5F);
		MTLItemLevers.polishedStone.name = "Polished Stone Lever";
		MTLItemLevers.polishedStone.stackID = 7;
		MTLItemLevers.polishedStone.setTextureIndex(6);
		MTLItemLevers.polishedStone.setBlockHardness(1.0F);
		MTLItemLevers.cobbleStone.name = "Cobblestone Lever";
		MTLItemLevers.cobbleStone.stackID = 8;
		MTLItemLevers.cobbleStone.setTextureIndex(16);
		MTLItemLevers.cobbleStone.setBlockHardness(1.0F);
		configuration.save();
		return MTLBlocks.mtLever.id;
	}
}
