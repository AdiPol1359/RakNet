package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;
import me.adipol.raknet.util.ProtocolInfo;

public class PacketOpenConnectionReply1 extends OfflinePacket {

    public long serverGuid;
    public boolean useSecurity;
    public short mtuSize;

    public PacketOpenConnectionReply1() {
        super(ProtocolInfo.OPEN_CONNECTION_REPLY_1);
    }

    @Override
    public void decodePayload() {

    }

    @Override
    public void encodePayload() {
        this.writeMagic();
        this.writeLong(serverGuid);
        this.writeBoolean(useSecurity);
        this.writeShort(mtuSize);
    }
}