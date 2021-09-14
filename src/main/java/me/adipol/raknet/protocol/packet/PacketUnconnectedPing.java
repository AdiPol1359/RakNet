package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;
import me.adipol.raknet.util.ProtocolInfo;

public class PacketUnconnectedPing extends OfflinePacket {

    public long timestamp;
    public long clientGuid;

    public PacketUnconnectedPing() {
        super(ProtocolInfo.UNCONNECTED_PING);
    }

    @Override
    public void decodePayload() {
        this.timestamp = this.readLong();
        this.readMagic();
        this.clientGuid = this.readLong();
    }

    @Override
    public void encodePayload() {
        //EMPTY
    }
}