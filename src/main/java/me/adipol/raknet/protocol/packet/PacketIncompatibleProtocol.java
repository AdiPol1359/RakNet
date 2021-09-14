package me.adipol.raknet.protocol.packet;

import me.adipol.raknet.protocol.OfflinePacket;
import me.adipol.raknet.util.ProtocolInfo;

public class PacketIncompatibleProtocol extends OfflinePacket {

    public byte protocolVersion;
    public long serverGuid;

    public PacketIncompatibleProtocol() {
        super(ProtocolInfo.INCOMPATIBLE_PROTOCOL);
    }

    @Override
    public void decodePayload() {

    }

    @Override
    public void encodePayload() {
        this.writeByte(protocolVersion);
        this.writeMagic();
        this.writeLong(serverGuid);
    }
}
