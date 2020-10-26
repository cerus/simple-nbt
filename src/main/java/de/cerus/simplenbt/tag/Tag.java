package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public abstract class Tag<T> {

    protected String name;
    protected T value;

    Tag(final InputStream inputStream, final boolean parseName) throws IOException {
        this.read(inputStream, parseName);
    }

    protected Tag(final String name, final T value) {
        this.name = name;
        this.value = value;
    }

    protected abstract void read(InputStream inputStream, boolean parseName) throws IOException;

    protected void write(final OutputStream outputStream, final boolean withName) throws IOException {
        outputStream.write(this.getId());
        if (withName) {
            this.writeName(outputStream);
        }
    }

    protected String readName(final InputStream inputStream) throws IOException {
        // Get length of name
        final byte[] nameLenArr = new byte[2];
        inputStream.read(nameLenArr, 0, 2);
        final short nameLen = ByteBuffer.wrap(nameLenArr).order(ByteOrder.BIG_ENDIAN).getShort();

        // Get name
        final byte[] nameArr = new byte[nameLen];
        inputStream.read(nameArr, 0, nameLen);
        return new String(nameArr, StandardCharsets.UTF_8);
    }

    protected void writeName(final OutputStream outputStream) throws IOException {
        outputStream.write(ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort((short) this.name.length()).array());
        outputStream.write(this.name.getBytes(StandardCharsets.UTF_8));
    }

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public abstract int getId();

}
