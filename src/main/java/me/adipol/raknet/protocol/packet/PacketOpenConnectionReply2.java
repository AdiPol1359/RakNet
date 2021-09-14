package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;

public class PacketOpenConnectionReply2 extends OfflinePacket {

    public long serverGuid;
    public String address;
    public short port;
    public byte version;
    public short mtuSize;

    public PacketOpenConnectionReply2() {
        super(0x08);
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
        this.writeByte((byte) 0);
    }
}
