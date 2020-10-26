package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagInt extends Tag<Integer> {

    TagInt(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagInt(final String name, final int value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // Get value
        final byte[] arr = new byte[4];
        inputStream.read(arr, 0, 4);
        this.value = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    @Override
    public int getId() {
        return 3;
    }

}
