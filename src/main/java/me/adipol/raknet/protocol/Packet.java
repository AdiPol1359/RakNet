package me.adipol.raknet.protocol;

import lombok.AllArgsConstructor;
import me.adipol.raknet.util.BinaryStream;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;

@AllArgsConstructor
public abstract class Packet extends BinaryStream {

    private final int id;

    public void decode() {
        this.readUnsignedByte();
        this.decodePayload();
    }

    public void encode() {
        this.writeUnsignedByte(id);
        this.encodePayload();
    }

    public String readString() {
        short length = this.readShort();
        return new String(this.read(length));
    }

    public void writeString(String v) {
        byte[] data = v.getBytes();

        this.writeShort((short) data.length);
        this.write(data);
    }

    public InetSocketAddress readAddress() {
        byte version = this.readByte();

        if(version == 4) {
            String address = ((~this.readByte()) & 0xff) + "." + ((~this.readByte()) & 0xff) + "." + ((~this.readByte()) & 0xff) + "." + ((~this.readByte()) & 0xff);
            short port = this.readShort();

            return new InetSocketAddress(address, port);
        }
        else {
            //TODO: ADD SUPPORT FOR IPV6
            return null;
        }
    }

    public void writeAddress(String address, short port, byte version) {
        this.writeByte(version);

        if(version == 4) {
            Arrays.stream(address.split("\\.")).forEach(n -> {
                this.writeByte((byte) ((~Integer.parseInt(n)) & 0xff));
            });

            this.writeShort(port);
        }
        else {
            //todo: ADD SUPPORT FOR IPV6
        }
    }

    public abstract void decodePayload();
    public abstract void encodePayload();
}
