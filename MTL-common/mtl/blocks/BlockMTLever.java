package mtl.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import eurysmods.api.IContainer;

import mtl.core.MTLItemLevers;
import mtl.core.MTLBlocks;
import mtl.core.MTLCore;
import mtl.core.MTLInit;
import mtl.tileentities.TileEntityMTLever;
import net.minecraft.src.BlockLever;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StepSound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockMTLever extends BlockLever implements IContainer {
	Class mtLeverEntityClass;

	public BlockMTLever(
			int blockId,
			Class leverClass,
			float hardness,
			StepSound sound,
			boolean disableStats,
			boolean requiresSelfNotify,
			String blockName) {
		super(blockId, 96);
		this.setBlockName(blockName);
		this.isBlockContainer = true;
		mtLeverEntityClass = leverClass;
		setHardness(hardness);
		setStepSound(sound);
		if (disableStats) {
			disableStats();
		}
		if (requiresSelfNotify) {
			setRequiresSelfNotify();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		int itemdamage = MTLInit.getDamageValue(
				par1IBlockAccess,
				par2,
				par3,
				par4);
		int texture = MTLItemLevers.getTexture(itemdamage);
		if (texture >= 0) {
			return texture;
		}
		return 22;
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
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		return MTLInit.MTL.getProxy().getBlockTextureFromSideAndMetadata(
				side,
				meta);
	}
	
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
            if (tileentity != null && tileentity instanceof TileEntityMTLever) {
	            int var10 = par1World.getBlockMetadata(par2, par3, par4);
	            int var11 = var10 & 7;
	            int var12 = 8 - (var10 & 8);
	            par1World.setBlockMetadataWithNotify(par2, par3, par4, var11 + var12);
	            par1World.markBlocksDirty(par2, par3, par4, par2, par3, par4);
	            par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, var12 > 0 ? 0.6F : 0.5F);
	            par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
	
	            if (var11 == 1)
	            {
	                par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
	            }
	            else if (var11 == 2)
	            {
	                par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
	            }
	            else if (var11 == 3)
	            {
	                par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
	            }
	            else if (var11 == 4)
	            {
	                par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
	            }
	            else if (var11 != 5 && var11 != 6)
	            {
	                if (var11 == 0 || var11 == 7)
	                {
	                    par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
	                }
	            }
	            else
	            {
	                par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
	            }
	
	            return true;
            }
            return false;
        }
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
			ItemStack itemstack = new ItemStack(MTLBlocks.mtLever.me, 1,
					MTLInit.getDamageValue(world, x, y, z));
			EntityItem entityitem = new EntityItem(world, x, y, z,
					new ItemStack(itemstack.itemID, 1, itemstack.getItemDamage()));
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
        return MTLItemLevers.getHardness(MTLInit.getDamageValue(world, x, y, z));
    }
	
    @SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubBlocks(int blockId, CreativeTabs creativeTabs, List blockList){
    	for (MTLItemLevers lever : MTLItemLevers.values()) {
    		if (lever.stackID > 1) {
    			blockList.add(new ItemStack(blockId, 1, lever.stackID));
    		}
    	}
    }
}
