package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * Represents a NBT tag wrapper for other classes.
 *
 * @param <T> The type that the implementation wraps around
 */
public abstract class Tag<T> {

    protected String name;
    protected T value;

    /**
     * This constructor has to be implemented, or else the tag reader will not be able to create any instances
     * of this implementation.
     *
     * @param inputStream The input stream that holds the data.
     * @param parseName   Whether the implementation should parse the name of the tag or not. If false the
     *                    implementation should not read the two length bytes and the string bytes.
     *
     * @throws IOException When the implementation fails to read the payload
     */
    public Tag(final InputStream inputStream, final boolean parseName) throws IOException {
        this.read(inputStream, parseName);
    }

    public Tag(final String name, final T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * This method is called by the constructor that gets called by the TagReader. Implementations
     * should read the payload from the provided InputStream.
     *
     * @param inputStream The input stream that holds the data.
     * @param parseName   Whether the implementation should parse the name of the tag or not. If false the
     *                    implementation should not read the two length bytes and the string bytes.
     *
     * @throws IOException When the implementation fails to read the payload
     */
    protected abstract void read(InputStream inputStream, boolean parseName) throws IOException;

    /**
     * Implementations should write the tags payload to the provided OutputStream.
     * Implementations are encouraged to overwrite this method and to still call it with super.write(...).
     *
     * @param outputStream The output stream that will hold the data.
     * @param withName     Whether the implementation should write the name of the tag or not. If false the
     *                     implementation should not weite the two length bytes and the string bytes.
     * @param writeId      Whether the implementation should write the id of this tag or not. This should
     *                     only be false in lists.
     *
     * @throws IOException When the implementation fails to write the payload
     */
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        if (writeId) {
            outputStream.write(this.getId());
        }
        if (withName) {
            this.writeName(outputStream);
        }
    }

    /**
     * Reads the tags name
     *
     * @param inputStream The InputStream that holds the data.
     *
     * @return This tags name.
     *
     * @throws IOException When we can't read the name.
     */
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

    /**
     * Writes the tags name
     *
     * @param outputStream The OutputStream that will hold the data.
     *
     * @throws IOException When we can't write the name.
     */
    protected void writeName(final OutputStream outputStream) throws IOException {
        outputStream.write(ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort((short) this.name.length()).array());
        outputStream.write(this.name.getBytes(StandardCharsets.UTF_8));
    }

    public String stringify() {
        return "";
    }

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public abstract int getId();

}
