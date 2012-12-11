package mtl.items;

import mtl.core.MTLBlocks;
import mtl.core.MTLInit;
import mtl.core.MTLItemLevers;
import mtl.tileentities.TileEntityMTLever;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class ItemMTLever extends ItemBlock {
	private String[] leverNames = MTLItemLevers.getLeverNames();

	private final Block blockRef;

	public ItemMTLever(int i) {
		super(i);
		this.blockRef = MTLBlocks.mtLever.me;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setNoRepair();
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getItemName())
					.append(".")
					.append(leverNames[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	@Override
	public int getIconFromDamage(int damage) {
		// return this.blockRef.getBlockTextureFromSideAndMetadata(1000,
		// damage);
		return damage;
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
		int var11 = world.getBlockId(i, j, k);

		Block lever = this.blockRef;

		if (var11 == Block.snow.blockID) {
			l = 1;
		} else if (var11 != Block.vine.blockID && var11 != Block.tallGrass.blockID && var11 != Block.deadBush.blockID && (Block.blocksList[var11] == null || !Block.blocksList[var11]
				.isBlockReplaceable(world, i, j, k))) {
			if (l == 0) {
				--j;
			}

			if (l == 1) {
				++j;
			}

			if (l == 2) {
				--k;
			}

			if (l == 3) {
				++k;
			}

			if (l == 4) {
				--i;
			}

			if (l == 5) {
				++i;
			}
		}

		if (itemstack.stackSize == 0 || !entityplayer.func_82247_a(
				i,
				j,
				k,
				l,
				itemstack) || (j == 255 && lever.blockMaterial.isSolid()))
			return false;

		if (world.canPlaceEntityOnSide(
				lever.blockID,
				i,
				j,
				k,
				false,
				l,
				entityplayer)) {
			int meta = this.getMetadata(itemstack.getItemDamage());
			int data = lever.func_85104_a(world, i, j, k, l, a, b, c, meta);
			if (this.placeBlockAt(
					itemstack,
					entityplayer,
					world,
					i,
					j,
					k,
					l,
					a,
					b,
					c,
					data)) {
				if (world.getBlockId(i, j, k) == lever.blockID) {
					lever.onBlockPlacedBy(world, i, j, k, entityplayer);
					TileEntity tileentity = world.getBlockTileEntity(i, j, k);

					if (tileentity != null && tileentity instanceof TileEntityMTLever) {
						TileEntityMTLever tileentitymtbutton = (TileEntityMTLever) tileentity;
						tileentitymtbutton.setTextureValue(itemstack
								.getItemDamage());
						tileentitymtbutton.onInventoryChanged();
					}
				}

				world.playSoundEffect(
						(i + 0.5F),
						(j + 0.5F),
						(k + 0.5F),
						lever.stepSound.getStepSound(),
						(lever.stepSound.getVolume() + 1.0F) / 2.0F,
						lever.stepSound.getPitch() * 0.8F);
				--itemstack.stackSize;

				return true;
			}
		}
		return false;
	}

	@Override
	public String getTextureFile() {
		return MTLInit.MTL.getItemSheet();
	}
}