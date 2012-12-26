package mtl.render;

import mtl.core.MTLCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMTLever implements ISimpleBlockRenderingHandler {

	public static boolean renderLeverBlock(Block block, int x, int y, int z, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
		int var5 = iblockaccess.getBlockMetadata(x, y, z);
		int var6 = var5 & 7;
		boolean var7 = (var5 & 8) > 0;
		Tessellator var8 = Tessellator.instance;
		boolean var9 = renderblocks.overrideBlockTexture >= 0;
		if (!var9) {
			renderblocks.overrideBlockTexture = block.getBlockTexture(
					iblockaccess,
					x,
					y,
					z,
					0);
		}

		float var10 = 0.25F;
		float var11 = 0.1875F;
		float var12 = 0.1875F;

		if (var6 == 5) {
			renderblocks.setRenderMinMax(
					0.5F - var11,
					0.0D,
					0.5F - var10,
					0.5F + var11,
					var12,
					0.5F + var10);
		} else if (var6 == 6) {
			renderblocks.setRenderMinMax(
					0.5F - var10,
					0.0D,
					0.5F - var11,
					0.5F + var10,
					var12,
					0.5F + var11);
		} else if (var6 == 4) {
			renderblocks.setRenderMinMax(
					0.5F - var11,
					0.5F - var10,
					1.0F - var12,
					0.5F + var11,
					0.5F + var10,
					1.0D);
		} else if (var6 == 3) {
			renderblocks.setRenderMinMax(
					0.5F - var11,
					0.5F - var10,
					0.0D,
					0.5F + var11,
					0.5F + var10,
					var12);
		} else if (var6 == 2) {
			renderblocks.setRenderMinMax(
					1.0F - var12,
					0.5F - var10,
					0.5F - var11,
					1.0D,
					0.5F + var10,
					0.5F + var11);
		} else if (var6 == 1) {
			renderblocks.setRenderMinMax(
					0.0D,
					0.5F - var10,
					0.5F - var11,
					var12,
					0.5F + var10,
					0.5F + var11);
		} else if (var6 == 0) {
			renderblocks.setRenderMinMax(
					0.5F - var10,
					1.0F - var12,
					0.5F - var11,
					0.5F + var10,
					1.0D,
					0.5F + var11);
		} else if (var6 == 7) {
			renderblocks.setRenderMinMax(
					0.5F - var11,
					1.0F - var12,
					0.5F - var10,
					0.5F + var11,
					1.0D,
					0.5F + var10);
		}

		renderblocks.renderStandardBlock(block, x, y, z);

		if (!var9) {
			renderblocks.overrideBlockTexture = -1;
		}

		var8.setBrightness(block.getMixedBrightnessForBlock(
				iblockaccess,
				x,
				y,
				z));
		float var13 = 1.0F;

		if (Block.lightValue[block.blockID] > 0) {
			var13 = 1.0F;
		}

		var8.setColorOpaque_F(var13, var13, var13);
		int var14 = block.getBlockTextureFromSide(0);

		if (renderblocks.overrideBlockTexture >= 0) {
			var14 = renderblocks.overrideBlockTexture;
		}

		int var15 = (var14 & 15) << 4;
		int var16 = var14 & 240;
		float var17 = var15 / 256.0F;
		float var18 = (var15 + 15.99F) / 256.0F;
		float var19 = var16 / 256.0F;
		float var20 = (var16 + 15.99F) / 256.0F;
		Vec3[] var21 = new Vec3[8];
		float var22 = 0.0625F;
		float var23 = 0.0625F;
		float var24 = 0.625F;
		var21[0] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				(-var22),
				0.0D,
				(-var23));
		var21[1] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				var22,
				0.0D,
				(-var23));
		var21[2] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				var22,
				0.0D,
				var23);
		var21[3] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				(-var22),
				0.0D,
				var23);
		var21[4] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				(-var22),
				var24,
				(-var23));
		var21[5] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				var22,
				var24,
				(-var23));
		var21[6] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				var22,
				var24,
				var23);
		var21[7] = iblockaccess.getWorldVec3Pool().getVecFromPool(
				(-var22),
				var24,
				var23);

		for (int var25 = 0; var25 < 8; ++var25) {
			if (var7) {
				var21[var25].zCoord -= 0.0625D;
				var21[var25].rotateAroundX(((float) Math.PI * 2F / 9F));
			} else {
				var21[var25].zCoord += 0.0625D;
				var21[var25].rotateAroundX(-((float) Math.PI * 2F / 9F));
			}

			if (var6 == 0 || var6 == 7) {
				var21[var25].rotateAroundZ((float) Math.PI);
			}

			if (var6 == 6 || var6 == 0) {
				var21[var25].rotateAroundY(((float) Math.PI / 2F));
			}

			if (var6 > 0 && var6 < 5) {
				var21[var25].yCoord -= 0.375D;
				var21[var25].rotateAroundX(((float) Math.PI / 2F));

				if (var6 == 4) {
					var21[var25].rotateAroundY(0.0F);
				}

				if (var6 == 3) {
					var21[var25].rotateAroundY((float) Math.PI);
				}

				if (var6 == 2) {
					var21[var25].rotateAroundY(((float) Math.PI / 2F));
				}

				if (var6 == 1) {
					var21[var25].rotateAroundY(-((float) Math.PI / 2F));
				}

				var21[var25].xCoord += x + 0.5D;
				var21[var25].yCoord += y + 0.5F;
				var21[var25].zCoord += z + 0.5D;
			} else if (var6 != 0 && var6 != 7) {
				var21[var25].xCoord += x + 0.5D;
				var21[var25].yCoord += y + 0.125F;
				var21[var25].zCoord += z + 0.5D;
			} else {
				var21[var25].xCoord += x + 0.5D;
				var21[var25].yCoord += y + 0.875F;
				var21[var25].zCoord += z + 0.5D;
			}
		}

		Vec3 var30 = null;
		Vec3 var26 = null;
		Vec3 var27 = null;
		Vec3 var28 = null;

		for (int var29 = 0; var29 < 6; ++var29) {
			if (var29 == 0) {
				var17 = (var15 + 7) / 256.0F;
				var18 = (var15 + 9 - 0.01F) / 256.0F;
				var19 = (var16 + 6) / 256.0F;
				var20 = (var16 + 8 - 0.01F) / 256.0F;
			} else if (var29 == 2) {
				var17 = (var15 + 7) / 256.0F;
				var18 = (var15 + 9 - 0.01F) / 256.0F;
				var19 = (var16 + 6) / 256.0F;
				var20 = (var16 + 16 - 0.01F) / 256.0F;
			}

			if (var29 == 0) {
				var30 = var21[0];
				var26 = var21[1];
				var27 = var21[2];
				var28 = var21[3];
			} else if (var29 == 1) {
				var30 = var21[7];
				var26 = var21[6];
				var27 = var21[5];
				var28 = var21[4];
			} else if (var29 == 2) {
				var30 = var21[1];
				var26 = var21[0];
				var27 = var21[4];
				var28 = var21[5];
			} else if (var29 == 3) {
				var30 = var21[2];
				var26 = var21[1];
				var27 = var21[5];
				var28 = var21[6];
			} else if (var29 == 4) {
				var30 = var21[3];
				var26 = var21[2];
				var27 = var21[6];
				var28 = var21[7];
			} else if (var29 == 5) {
				var30 = var21[0];
				var26 = var21[3];
				var27 = var21[7];
				var28 = var21[4];
			}

			var8.addVertexWithUV(
					var30.xCoord,
					var30.yCoord,
					var30.zCoord,
					var17,
					var20);
			var8.addVertexWithUV(
					var26.xCoord,
					var26.yCoord,
					var26.zCoord,
					var18,
					var20);
			var8.addVertexWithUV(
					var27.xCoord,
					var27.yCoord,
					var27.zCoord,
					var18,
					var19);
			var8.addVertexWithUV(
					var28.xCoord,
					var28.yCoord,
					var28.zCoord,
					var17,
					var19);
		}

		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == MTLCore.mtLeverBlockRenderID) {
			return renderLeverBlock(block, x, y, z, renderer, world);
		} else
			return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return MTLCore.mtLeverBlockRenderID;
	}
}
