package me.adipol.raknet.protocol;

import me.adipol.raknet.util.ProtocolInfo;

import java.net.InetSocketAddress;

public class OpenConnectionRequest2 extends OfflinePacket {

    public InetSocketAddress serverAddress;
    public short mtuSize;
    public long clientGUID;

    public OpenConnectionRequest2() {
        super(ProtocolInfo.OpenConnectionRequest2);
    }

    @Override
    public void decodePayload() {
        this.readMagic();
        this.serverAddress = this.readAddress();
        this.mtuSize = this.readShort();
        this.clientGUID = this.readLong();
    }

    @Override
    public void encodePayload() {

    }
}
