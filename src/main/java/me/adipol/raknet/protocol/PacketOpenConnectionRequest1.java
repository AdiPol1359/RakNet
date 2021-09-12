package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

public class PacketOpenConnectionRequest1 extends OfflinePacket {

    public byte protocolVersion;
    public short mtuSize;

    public PacketOpenConnectionRequest1() {
        super(ProtocolInfo.OpenConnectionRequest1);
    }

    @Override
    public void decodePayload() {
        this.writeMagic();
        this.protocolVersion = this.readByte();
        this.mtuSize = (short) this.getBuffer().length;
    }

    @Override
    public void encodePayload() {

    }
}
