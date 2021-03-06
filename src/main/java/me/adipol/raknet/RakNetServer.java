package me.adipol.raknet;

import me.adipol.raknet.protocol.packet.*;
import me.adipol.raknet.util.ProtocolInfo;
import me.adipol.raknet.util.ServerName;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class RakNetServer extends Thread {

    private final byte[] bytes = new byte[256];
    private final Map<String, Session> sessions = new HashMap<>();

    private DatagramSocket socket;

    public RakNetServer() {
        try {
            socket = new DatagramSocket(19132);
        }
        catch(Exception ex) {}//TODO: HANDLE EXCEPTION
    }

    @Override
    public void run() {
        while(true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                socket.receive(datagramPacket);

                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

                int pid = dataInputStream.readByte() & 0xff;

                System.out.println(pid);

                switch(pid) {
                    case ProtocolInfo.UNCONNECTED_PING: {
                        PacketUnconnectedPing decodedPacket = new PacketUnconnectedPing();

                        decodedPacket.setBuffer(datagramPacket.getData());
                        decodedPacket.decode();

                        PacketUnconnectedPong packetUnconnectedPong = new PacketUnconnectedPong();

                        packetUnconnectedPong.timestamp = decodedPacket.timestamp;
                        packetUnconnectedPong.serverGuid = 1243455;//TODO: GENERATE NUMBER
                        packetUnconnectedPong.serverName = new ServerName("RakNet", "390", "1.17.11", "0", "10", "19132").toString();

                        packetUnconnectedPong.encode();

                        sendBuffer(packetUnconnectedPong.getBuffer(), address, port);
                        break;
                    }
                    case ProtocolInfo.OPEN_CONNECTION_REQUEST_1: {
                        PacketOpenConnectionRequest1 decodedPacket = new PacketOpenConnectionRequest1();

                        decodedPacket.setBuffer(datagramPacket.getData());
                        decodedPacket.decode();

                        if(decodedPacket.protocolVersion != ProtocolInfo.CURRENT_PROTOCOL) {
                            PacketIncompatibleProtocol packetIncompatibleProtocol = new PacketIncompatibleProtocol();

                            packetIncompatibleProtocol.protocolVersion = decodedPacket.protocolVersion;
                            packetIncompatibleProtocol.serverGuid = 1243455;
                            packetIncompatibleProtocol.encode();

                            sendBuffer(packetIncompatibleProtocol.getBuffer(), address, port);

                            continue;
                        }

                        PacketOpenConnectionReply1 packetOpenConnectionReply1 = new PacketOpenConnectionReply1();

                        packetOpenConnectionReply1.serverGuid = 1243455;
                        packetOpenConnectionReply1.useSecurity = false;
                        packetOpenConnectionReply1.mtuSize = decodedPacket.mtuSize;

                        packetOpenConnectionReply1.encode();

                        sendBuffer(packetOpenConnectionReply1.getBuffer(), address, port);
                        break;
                    }
                    case ProtocolInfo.OPEN_CONNECTION_REQUEST_2: {
                        PacketOpenConnectionRequest2 decodedPacket = new PacketOpenConnectionRequest2();

                        decodedPacket.setBuffer(datagramPacket.getData());
                        decodedPacket.decode();

                        PacketOpenConnectionReply2 packetOpenConnectionReply2 = new PacketOpenConnectionReply2();

                        packetOpenConnectionReply2.serverGuid = 1243455;
                        packetOpenConnectionReply2.address = decodedPacket.serverAddress.getHostName();
                        packetOpenConnectionReply2.port = (short) decodedPacket.serverAddress.getPort();
                        packetOpenConnectionReply2.version = 4;
                        packetOpenConnectionReply2.mtuSize = decodedPacket.mtuSize;
                        packetOpenConnectionReply2.enableEcryption = false;

                        packetOpenConnectionReply2.encode();

                        //TODO: IMPLEMENTS SESSION

                        sendBuffer(packetOpenConnectionReply2.getBuffer(), address, port);
                    }
                    default: {
                        Session session = sessions.get(address.getHostAddress() + ":" + port);

                        if(session == null) {
                            session = new Session();
                            sessions.put(address.getHostAddress() + ":" + port, session);
                        }

                        session.handleDataPacket(datagramPacket.getData());
                    }
                }
            }
            catch(Exception ex) {} //TODO: HANDLE EXCEPTION
        }
    }

    private void sendBuffer(byte[] buffer, InetAddress address, int port) {
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);

        try {
            socket.send(datagramPacket);
        }
        catch (Exception ignored) {}//TODO: HANDLE EXCEPTION
    }
}