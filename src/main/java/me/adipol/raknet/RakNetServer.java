package me.adipol.raknet;

import me.adipol.raknet.protocol.PacketUnconnectedPing;
import me.adipol.raknet.protocol.PacketUnconnectedPong;
import me.adipol.raknet.util.ProtocolInfo;
import me.adipol.raknet.util.ServerName;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
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
                    case ProtocolInfo.PacketUnconnectedPing:
                        PacketUnconnectedPing packetUnconnectedPing = new PacketUnconnectedPing();

                        packetUnconnectedPing.setBuffer(datagramPacket.getData());
                        packetUnconnectedPing.decode();

                        PacketUnconnectedPong packetUnconnectedPong = new PacketUnconnectedPong();

                        packetUnconnectedPong.time = packetUnconnectedPing.time;
                        packetUnconnectedPong.serverGuid = 1243455;
                        packetUnconnectedPong.serverName = new ServerName("RakNet", "390", "1.17.11", "0", "10", "19132").toString();

                        packetUnconnectedPong.encode();

                        sendBuffer(packetUnconnectedPong.getBuffer(), address, port);
                        break;
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