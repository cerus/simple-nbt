package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagShort extends Tag<Short> {

    TagShort(final InputStream inputStream) throws IOException {
        super(inputStream);
    }

    protected TagShort(final String name, final short value) {
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

        // Get value
        final byte[] arr = new byte[2];
        inputStream.read(arr, 0, 2);
        this.value = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getShort();
    }

    @Override
    public int getId() {
        return 2;
    }

}
