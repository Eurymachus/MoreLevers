package eurymachus.mtl.network.packets;

import eurymachus.mtl.core.MTLInit;
import eurymachus.mtl.tileentities.TileEntityMTLever;
import eurysmods.network.packets.core.PacketTileEntityMT;

public class PacketUpdateMTLever extends PacketTileEntityMT {
	public PacketUpdateMTLever() {
		super(MTLInit.MTL.getModChannel());
	}

	public PacketUpdateMTLever(TileEntityMTLever tileentitymtlever) {
		super(MTLInit.MTL.getModChannel(), tileentitymtlever);
	}
}
