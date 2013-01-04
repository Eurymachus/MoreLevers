package eurymachus.mtl.tileentities;

import net.minecraft.network.packet.Packet;
import eurymachus.mtl.network.packets.PacketUpdateMTLever;
import eurysmods.tileentities.TileEntityMT;

public class TileEntityMTLever extends TileEntityMT {

	@Override
	public Packet getUpdatePacket() {
		return new PacketUpdateMTLever(this).getPacket();
	}
}
