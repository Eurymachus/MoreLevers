package eurymachus.mtl.proxy;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoidlib.ICommonProxy;
import slimevoidlib.IPacketHandling;
import slimevoidlib.network.PacketIds;
import cpw.mods.fml.common.network.Player;
import eurymachus.mtl.core.MTLInit;
import eurymachus.mtl.network.ServerPacketHandler;
import eurymachus.mtl.network.packets.PacketUpdateMTLever;

public class CommonProxy implements ICommonProxy {

	@Override
	public void registerRenderInformation() {
	}

	@Override
	public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {

	}

	@Override
	public String getMinecraftDir() {
		return "./";
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		try {
			EntityPlayer entityplayer = (EntityPlayer) player;
			World world = entityplayer.worldObj;
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.TILE:
				PacketUpdateMTLever packetLever = new PacketUpdateMTLever();
				packetLever.readData(data);
				this.getPacketHandler().handleTileEntityPacket(
						packetLever,
						entityplayer,
						world);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public IPacketHandling getPacketHandler() {
		return new ServerPacketHandler();
	}
	
	@Override
	public void registerTickHandler() {
	}

	@Override
	public void preInit() {
	}

	@Override
	public void registerConfigurationProperties(File configFile) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public boolean isClient(World world) {
		// TODO :: Auto-generated method stub
		return false;
	}

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
		// TODO :: Auto-generated method stub
		
	}
	
}
