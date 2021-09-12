package me.adipol.raknet.protocol;

public abstract class OfflinePacket extends Packet {

    private final byte[] magic = new byte[]{ (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78};

    public OfflinePacket(int id) {
        super(id);
    }

    public void readMagic() {
        this.skip(16);
    }

    public void writeMagic() {
        this.write(magic);
    }
}