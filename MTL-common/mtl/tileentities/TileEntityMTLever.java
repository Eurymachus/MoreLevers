package mtl.tileentities;

import mtl.network.packets.PacketUpdateMTLever;
import net.minecraft.network.packet.Packet;
import eurysmods.tileentities.TileEntityMT;

public class TileEntityMTLever extends TileEntityMT {

	@Override
	public Packet getUpdatePacket() {
		return new PacketUpdateMTLever(this).getPacket();
	}
}
