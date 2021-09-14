package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;
import me.adipol.raknet.util.ProtocolInfo;

import java.net.InetSocketAddress;

public class PacketOpenConnectionRequest2 extends OfflinePacket {

    public InetSocketAddress serverAddress;
    public short mtuSize;
    public long clientGUID;

    public PacketOpenConnectionRequest2() {
        super(ProtocolInfo.OPEN_CONNECTION_REQUEST_2);
    }

    @Override
    public void decodePayload() {
        this.readMagic();
        this.serverAddress = this.readAddress();
        this.mtuSize = this.readShort();
        this.clientGUID = this.readLong();
    }

    @Override
    public void encodePayload() {

    }
}
