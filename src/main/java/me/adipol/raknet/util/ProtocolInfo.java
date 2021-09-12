package me.adipol.raknet.util;

public interface ProtocolInfo {
    int UnconnectedPing = 0x01;
    int UnconnectedPong = 0x1c;

    int OpenConnectionRequest1 = 0x05;
    int OpenConnectionReply1 = 0x06;

    int OpenConnectionRequest2 = 0x07;
    int OpenConnectionReply2 = 0x08;
}
