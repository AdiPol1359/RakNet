package me.adipol.raknet.util;

public interface ProtocolInfo {
    int CURRENT_PROTOCOL = 10;

    int UNCONNECTED_PING = 0x01;
    int UNCONNECTED_PONG = 0x1c;

    int OPEN_CONNECTION_REQUEST_1 = 0x05;
    int OPEN_CONNECTION_REPLY_1 = 0x06;

    int OPEN_CONNECTION_REQUEST_2 = 0x07;
    int OPEN_CONNECTION_REPLY_2 = 0x08;

    int INCOMPATIBLE_PROTOCOL = 0x19;
}
