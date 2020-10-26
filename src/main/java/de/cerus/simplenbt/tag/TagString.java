package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class TagString extends Tag<String> {

    TagString(final InputStream inputStream) throws IOException {
        super(inputStream);
    }

    protected TagString(final String name, final String value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream) throws IOException {
        if (!this.checkId(inputStream)) {
            // Id does not match
            // TODO
        }

        // Get name
        this.name = this.readName(inputStream);

        // Get length of string
        final byte[] stringLenArr = new byte[2];
        inputStream.read(stringLenArr, 0, 2);
        final short stringLen = ByteBuffer.wrap(stringLenArr).order(ByteOrder.BIG_ENDIAN).getShort();

        // Get string
        final byte[] stringArr = new byte[stringLen];
        inputStream.read(stringArr, 0, stringLen);
        this.value = new String(stringArr, StandardCharsets.UTF_8);
    }

    @Override
    public int getId() {
        return 8;
    }

}
