package eurymachus.mtl.network.packets;

import slimevoid.lib.network.PacketTileEntityMT;
import eurymachus.mtl.core.MTLInit;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class PacketUpdateMTLever extends PacketTileEntityMT {
	public PacketUpdateMTLever() {
		super(MTLInit.MTL.getModChannel());
	}

	public PacketUpdateMTLever(TileEntityMTLever tileentitymtlever) {
		super(MTLInit.MTL.getModChannel(), tileentitymtlever);
	}
}
