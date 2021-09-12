package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

public class PacketUnconnectedPing extends OfflinePacket {

    public long time;
    public long clientGuid;

    public PacketUnconnectedPing() {
        super(ProtocolInfo.PacketUnconnectedPing);
    }

    @Override
    public void decodePayload() {
        this.time = this.readLong();
        this.readMagic();
        this.clientGuid = this.readLong();
    }

    @Override
    public void encodePayload() {
        //EMPTY
    }
}