package eurymachus.mtl.tileentities;

import slimevoid.lib.tileentity.TileEntityMT;
import net.minecraft.network.packet.Packet;
import eurymachus.mtl.network.packets.PacketUpdateMTLever;

public class TileEntityMTLever extends TileEntityMT {

	@Override
	public Packet getUpdatePacket() {
		return new PacketUpdateMTLever(this).getPacket();
	}
}
