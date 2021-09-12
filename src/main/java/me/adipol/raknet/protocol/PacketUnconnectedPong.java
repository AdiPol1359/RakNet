package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

public class PacketUnconnectedPong extends OfflinePacket{

    public long time;
    public long serverGuid;
    public String serverName;

    public PacketUnconnectedPong() {
        super(ProtocolInfo.PacketUnconnectedPong);
    }

    @Override
    public void decodePayload() {
        //TODO
    }

    @Override
    public void encodePayload() {
        this.writeLong(time);
        this.writeLong(serverGuid);
        this.writeMagic();
        this.writeString(serverName);
    }
}