package eurymachus.mtl.tileentities;

import net.minecraft.network.packet.Packet;
import slimevoid.lib.tileentity.TileEntityMT;
import eurymachus.mtl.network.packets.PacketUpdateMTLever;

public class TileEntityMTLever extends TileEntityMT {

	@Override
	public Packet getUpdatePacket() {
		return new PacketUpdateMTLever(this).getPacket();
	}
}
