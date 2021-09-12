package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

public class PacketUnconnectedPing extends OfflinePacket {

    public long timestamp;
    public long clientGuid;

    public PacketUnconnectedPing() {
        super(ProtocolInfo.UnconnectedPing);
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