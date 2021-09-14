package me.adipol.raknet;

public class Session {

    public void handleDataPacket(byte[] data) {
        System.out.println(data[0] & 0xff);
    }
}
