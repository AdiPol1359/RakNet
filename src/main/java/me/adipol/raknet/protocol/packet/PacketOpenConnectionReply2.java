package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;
import me.adipol.raknet.util.ProtocolInfo;

public class PacketOpenConnectionReply2 extends OfflinePacket {

    public long serverGuid;
    public String address;
    public short port;
    public byte version;
    public short mtuSize;
    public boolean enableEcryption;

    public PacketOpenConnectionReply2() {
        super(ProtocolInfo.OPEN_CONNECTION_REPLY_2);
    }

    @Override
    public void decodePayload() {

    }

    @Override
    public void encodePayload() {
        this.writeMagic();
        this.writeLong(serverGuid);
        this.writeAddress(address, port, version);
        this.writeShort(mtuSize);
        this.writeBoolean(enableEcryption);
    }
}
