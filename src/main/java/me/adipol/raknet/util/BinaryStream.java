package me.adipol.raknet.util;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class BinaryStream {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final DataOutputStream dataOutputStream;

    private int offset = 0;

    public BinaryStream() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    @SneakyThrows
    public byte[] read(int length) {
        byte[] data = new byte[length];

        DataInputStream dataInputStream = getInputStream(length);

        for(int i = 0; i<length; i++) {
            data[i] = dataInputStream.readByte();
        }

        return data;
    }

    @SneakyThrows
    public void write(byte[] bytes) {
        for(byte b : bytes) {
            dataOutputStream.writeByte(b);
        }
    }

    public DataInputStream getInputStream(int length) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        byteArrayInputStream.skip(addOffset(length));

        return new DataInputStream(byteArrayInputStream);
    }

    @SneakyThrows
    public byte readByte() {
        DataInputStream dataInputStream = getInputStream(1);
        return dataInputStream.readByte();
    }

    @SneakyThrows
    public void writeByte(byte v) {
        dataOutputStream.writeByte(v);
    }

    @SneakyThrows
    public int readUnsignedByte() {
        DataInputStream dataInputStream = getInputStream(1);
        return dataInputStream.readByte() & 0xff;
    }

    @SneakyThrows
    public void writeUnsignedByte(int v) {
        dataOutputStream.writeByte((byte)v & 0xff);
    }

    @SneakyThrows
    public short readShort() {
        DataInputStream dataInputStream = getInputStream(2);
        return dataInputStream.readShort();
    }

    @SneakyThrows
    public void writeShort(short v) {
        dataOutputStream.writeShort(v);
    }

    @SneakyThrows
    public long readLong() {
        DataInputStream dataInputStream = getInputStream(8);
        return dataInputStream.readLong();
    }

    @SneakyThrows
    public void writeLong(long v) {
        dataOutputStream.writeLong(v);
    }

    public int addOffset(int length) {
        return (this.offset += length) - length;
    }

    public void skip(int offset) {
        this.offset += offset;
    }

    public byte[] getBuffer() {
        return byteArrayOutputStream.toByteArray();
    }

    @SneakyThrows
    public void setBuffer(byte[] v) {
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(v);
    }
}
