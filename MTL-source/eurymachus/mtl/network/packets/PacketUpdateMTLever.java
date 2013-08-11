package eurymachus.mtl.network.packets;

import slimevoidlib.network.PacketTileEntityMT;
import eurymachus.mtl.core.lib.CoreLib;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class PacketUpdateMTLever extends PacketTileEntityMT {
	public PacketUpdateMTLever() {
		super(CoreLib.MOD_CHANNEL);
	}

	public PacketUpdateMTLever(TileEntityMTLever tileentitymtlever) {
		super(CoreLib.MOD_CHANNEL, tileentitymtlever);
	}
}
