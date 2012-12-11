package mtl.network.packets;

import mtl.core.MTLInit;
import mtl.tileentities.TileEntityMTLever;
import eurysmods.network.packets.core.PacketTileEntityMT;

public class PacketUpdateMTLever extends PacketTileEntityMT {
	public PacketUpdateMTLever() {
		super(MTLInit.MTL.getModChannel());
	}

	public PacketUpdateMTLever(TileEntityMTLever tileentitymtlever) {
		super(MTLInit.MTL.getModChannel(), tileentitymtlever);
	}
}
