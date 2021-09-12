package me.adipol.raknet;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import me.adipol.raknet.protocol.*;
import me.adipol.raknet.util.ProtocolInfo;
import me.adipol.raknet.util.ServerName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RakNetServer extends Thread {

   private final byte[] bytes = new byte[256];

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

                byte pid = dataInputStream.readByte();

                switch(pid) {
                    case ProtocolInfo.UnconnectedPing: {
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
                    case ProtocolInfo.OpenConnectionRequest1: {
                        PacketOpenConnectionRequest1 decodedPacket = new PacketOpenConnectionRequest1();

                        decodedPacket.setBuffer(datagramPacket.getData());
                        decodedPacket.decode();

                        PacketOpenConnectionReply1 packetOpenConnectionReply1 = new PacketOpenConnectionReply1();

                        packetOpenConnectionReply1.serverGuid = 1243455;
                        packetOpenConnectionReply1.mtuSize = decodedPacket.mtuSize;

                        packetOpenConnectionReply1.encode();

                        sendBuffer(packetOpenConnectionReply1.getBuffer(), address, port);
                        break;
                    }
                    case ProtocolInfo.OpenConnectionRequest2: {
                        OpenConnectionRequest2 decodedPacket = new OpenConnectionRequest2();

                        decodedPacket.setBuffer(datagramPacket.getData());
                        decodedPacket.decode();

                        System.out.println("mtuSize: " + decodedPacket.mtuSize);

                        //System.out.println(decodedPacket.serverAddress.getPort());
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