package mtl.network;

import mtl.tileentities.TileEntityMTLever;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import eurysmods.api.IPacketHandling;
import eurysmods.network.packets.core.PacketTileEntity;
import eurysmods.network.packets.core.PacketUpdate;

public class ServerPacketHandler implements IPacketHandling {
	@Override
	public void handleTileEntityPacket(PacketTileEntity packet, EntityPlayer entityplayer, World world) {
		if (packet != null && packet.targetExists(world)) {
			TileEntity tileentity = packet.getTileEntity(world);
			if ((tileentity != null) && (tileentity instanceof TileEntityMTLever)) {
				TileEntityMTLever tileentitymtlever = (TileEntityMTLever) tileentity;
				tileentitymtlever.handleUpdatePacket(world, packet);
			}
		}
	}

	@Override
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
	}

	@Override
	public void handlePacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
		// TODO Auto-generated method stub

	}
}
