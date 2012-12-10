package mtl.network.packets;

import eurysmods.network.packets.core.PacketTileEntityMT;
import mtl.core.MTLInit;
import mtl.tileentities.TileEntityMTLever;
import net.minecraft.src.TileEntity;

public class PacketUpdateMTLever extends PacketTileEntityMT {
	public PacketUpdateMTLever() {
		super(MTLInit.MTL.getModChannel());
	}

	public PacketUpdateMTLever(TileEntityMTLever tileentitymtlever) {
		super(MTLInit.MTL.getModChannel(), tileentitymtlever);
	}
}
