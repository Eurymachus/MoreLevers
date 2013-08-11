package eurymachus.mtl.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import eurymachus.mtl.core.MTLBlocks;
import eurymachus.mtl.core.MTLItemLevers;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class ItemMTLever extends ItemBlock {
	
	private Icon[] iconList;
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
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getUnlocalizedName())
					.append(".")
					.append(leverNames[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return this.blockRef.getIcon(0, damage);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
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

		if (itemstack.stackSize == 0 || !entityplayer.canPlayerEdit(
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
				entityplayer,
				itemstack)) {
			int meta = this.getMetadata(itemstack.getItemDamage());
			int data = lever.onBlockPlaced(world, i, j, k, l, a, b, c, meta); /*onBlockPlaced*/
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
					lever.onBlockPlacedBy(world, i, j, k, entityplayer, itemstack);
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
}