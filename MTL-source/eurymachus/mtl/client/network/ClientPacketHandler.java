package eurymachus.mtl.client.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoidlib.IPacketHandling;
import slimevoidlib.network.PacketTileEntity;
import slimevoidlib.network.PacketUpdate;
import eurymachus.mtl.tileentities.TileEntityMTLever;

public class ClientPacketHandler implements IPacketHandling {
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
	}
}
