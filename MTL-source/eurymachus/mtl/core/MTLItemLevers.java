package eurymachus.mtl.core;

import net.minecraft.item.ItemStack;

public enum MTLItemLevers {
	iron,
	gold,
	diamond,
	oakPlank,
	sprucePlank,
	birchPlank,
	junglePlank,
	polishedStone,
	cobbleStone;

	public ItemStack me;
	public int stackID;
	public String name;
	private String iconLocation;
	private float hardness;

	public static ItemStack getStack(int itemDamage) {
		for (MTLItemLevers itemstack : MTLItemLevers.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static String getTexture(int itemDamage) {
		for (MTLItemLevers itemstack : MTLItemLevers.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.iconLocation;
			}
		}
		return null;
	}

	public static float getHardness(int itemDamage) {
		for (MTLItemLevers itemstack : MTLItemLevers.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.hardness;
			}
		}
		return 0.5F;
	}

	public void setTextureIndex(String texture) {
		this.iconLocation = texture;
	}

	public void setBlockHardness(float hardness) {
		this.hardness = hardness;
	}

	public static String[] getLeverNames() {
		String[] names = new String[MTLItemLevers.values().length];
		int i = 0;
		for (MTLItemLevers itemstack : MTLItemLevers.values()) {
			if (itemstack != null && itemstack.name != null && !itemstack.name
					.isEmpty()) {
				names[i] = itemstack.name;
			} else {
				names[i] = "";
			}
			i++;
		}
		return names;
	}
}
