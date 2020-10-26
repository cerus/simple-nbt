package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public abstract class Tag<T> {

    protected String name;
    protected T value;

    Tag(final InputStream inputStream) throws IOException {
        this.read(inputStream);
    }

    protected Tag(final String name, final T value) {
        this.name = name;
        this.value = value;
    }

    protected abstract void read(InputStream inputStream) throws IOException;

    protected boolean checkId(final InputStream inputStream) throws IOException {
        return inputStream.read() == this.getId();
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

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public abstract int getId();

}
