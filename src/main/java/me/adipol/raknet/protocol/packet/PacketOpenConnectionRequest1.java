package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;
import me.adipol.raknet.util.ProtocolInfo;

public class PacketOpenConnectionRequest1 extends OfflinePacket {

    public byte protocolVersion;
    public short mtuSize;

    public PacketOpenConnectionRequest1() {
        super(ProtocolInfo.OPEN_CONNECTION_REQUEST_1);
    }

    @Override
    public void decodePayload() {
        this.readMagic();
        this.protocolVersion = this.readByte();
        this.mtuSize = (short) this.getBuffer().length;
    }

    @Override
    public void encodePayload() {

    }
}
