package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

public class PacketUnconnectedPong extends OfflinePacket{

    public long timestamp;
    public long serverGuid;
    public String serverName;

    public PacketUnconnectedPong() {
        super(ProtocolInfo.UnconnectedPong);
    }

    @Override
    public void decodePayload() {
        //TODO
    }

    @Override
    public void encodePayload() {
        this.writeLong(timestamp);
        this.writeLong(serverGuid);
        this.writeMagic();
        this.writeString(serverName);
    }
}