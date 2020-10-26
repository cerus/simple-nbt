package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagByteArray extends Tag<byte[]> {

    TagByteArray(final InputStream inputStream) throws IOException {
        super(inputStream);
    }

    protected TagByteArray(final String name, final byte[] value) {
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

        // Get array size
        final byte[] sizeArr = new byte[4];
        inputStream.read(sizeArr, 0, 4);
        final int size = ByteBuffer.wrap(sizeArr).order(ByteOrder.BIG_ENDIAN).getInt();

        // Get array
        final byte[] bytes = new byte[size];
        inputStream.read(bytes, 0, size);
        this.value = bytes;
    }

    @Override
    public int getId() {
        return 7;
    }

}
