package dev.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class TagString extends Tag<String> {

    private byte[] bytes;

    TagString(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagString(final String name, final String value) {
        super(name, value);
        this.bytes = value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // Get length of string
        final byte[] stringLenArr = new byte[2];
        inputStream.read(stringLenArr, 0, 2);
        final short stringLen = ByteBuffer.wrap(stringLenArr).order(ByteOrder.BIG_ENDIAN).getShort();

        // Get string
        final byte[] stringArr = new byte[stringLen];
        inputStream.read(stringArr, 0, stringLen);
        this.value = new String(stringArr, StandardCharsets.UTF_8);
        this.bytes = stringArr;
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        super.write(outputStream, withName, writeId);

        outputStream.write(ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort((short) this.bytes.length).array());
        outputStream.write(this.bytes);
    }

    @Override
    public String stringify() {
        return "\"" + this.getValue().replace("\"", "\\\"") + "\"";
    }

    @Override
    public int getId() {
        return 8;
    }

}
