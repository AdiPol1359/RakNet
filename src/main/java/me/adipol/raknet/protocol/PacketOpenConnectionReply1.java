package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

public class PacketOpenConnectionReply1 extends OfflinePacket {

    public long serverGuid;
   // public boolean useSecurity;
    public short mtuSize;

    public PacketOpenConnectionReply1() {
        super(ProtocolInfo.OpenConnectionReply1);
    }

    @Override
    public void decodePayload() {

    }

    @Override
    public void encodePayload() {
        this.writeMagic();
        this.writeLong(serverGuid);
        this.writeByte((byte) 0);
        this.writeShort(mtuSize);
    }
}