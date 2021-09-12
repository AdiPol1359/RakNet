package me.adipol.raknet.protocol;

import lombok.AllArgsConstructor;
import me.adipol.raknet.util.BinaryStream;


@AllArgsConstructor
public abstract class Packet extends BinaryStream {

    public final int id;

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

    public abstract void decodePayload();
    public abstract void encodePayload();
}
