package eurymachus.mtl.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.StepSound;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import slimevoidlib.IContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eurymachus.mtl.core.MTLBlocks;
import eurymachus.mtl.core.MTLCore;
import eurymachus.mtl.core.MTLInit;
import eurymachus.mtl.core.MTLItemLevers;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class BlockMTLever extends BlockLever implements ITileEntityProvider {
	
	private Icon[] iconList;
	Class mtLeverEntityClass;

	public BlockMTLever(int blockId, Class leverClass, float hardness, StepSound sound, boolean disableStats, boolean requiresSelfNotify, String blockName) {
		super(blockId);
		this.setUnlocalizedName(blockName);
		this.isBlockContainer = true;
		mtLeverEntityClass = leverClass;
		setHardness(hardness);
		setStepSound(sound);
		if (disableStats) {
			disableStats();
		}
		if (requiresSelfNotify) {
			//setRequiresSelfNotify();
		}
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("lever");
		this.iconList = new Icon[MTLItemLevers.values().length];
		for (int i = 0; i < iconList.length; i++) {
			this.iconList[i] = iconRegister.registerIcon(MTLItemLevers.getTexture(i));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return this.iconList[MTLInit.getDamageValue(
				par1IBlockAccess,
				par2,
				par3,
				par4)];
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.setBlockTileEntity(
				x,
				y,
				z,
				this.createTileEntity(world, world.getBlockMetadata(x, y, z)));
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (side == 1000) return this.blockIcon;
		return this.iconList[meta];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int l, float a, float b, float c) {

		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityMTLever) {
			int metadata = world.getBlockMetadata(x, y, z);
			int side = metadata & 7;
			int state = 8 - (metadata & 8);
			world.setBlockMetadataWithNotify(x, y, z, side + state, 0x3);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect(
					x + 0.5D,
					y + 0.5D,
					z + 0.5D,
					"random.click",
					0.3F,
					state > 0 ? 0.6F : 0.5F);
			world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);

			if (side == 1) {
				world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
			} else if (side == 2) {
				world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
			} else if (side == 3) {
				world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
			} else if (side == 4) {
				world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
			} else if (side != 5 && side != 6) {
				if (side == 0 || side == 7) {
					world.notifyBlocksOfNeighborChange(
							x,
							y + 1,
							z,
							this.blockID);
				}
			} else {
				world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
			}

			return true;
		}
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return MTLCore.mtLeverBlockRenderID;
	}

	/**
	 * Called whenever the block is removed.
	 */
	@Override
	public void breakBlock(World world, int x, int y, int z, int a, int b) {
		if (world.getWorldInfo().getGameType() != EnumGameType.CREATIVE) {
			ItemStack itemstack = new ItemStack(
					MTLBlocks.mtLever.me,
						1,
						MTLInit.getDamageValue(world, x, y, z));
			EntityItem entityitem = new EntityItem(
					world,
						x,
						y,
						z,
						new ItemStack(
								itemstack.itemID,
									1,
									itemstack.getItemDamage()));
			world.spawnEntityInWorld(entityitem);
		}
		super.breakBlock(world, x, y, z, a, b);
		world.removeBlockTileEntity(x, y, z);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		try {
			return (TileEntity) mtLeverEntityClass.newInstance();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return createNewTileEntity(world);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return MTLItemLevers
				.getHardness(MTLInit.getDamageValue(world, x, y, z));
	}

	@SideOnly(Side.CLIENT)
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@Override
	public void getSubBlocks(int blockId, CreativeTabs creativeTabs, List blockList) {
		for (MTLItemLevers lever : MTLItemLevers.values()) {
			if (lever.stackID >= 0) {
				blockList.add(new ItemStack(blockId, 1, lever.stackID));
			}
		}
	}
	
    @SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    	return true;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    	return true;
    }
}
