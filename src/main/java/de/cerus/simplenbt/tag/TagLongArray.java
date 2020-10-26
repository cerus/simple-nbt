package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagLongArray extends Tag<long[]> {

    TagLongArray(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagLongArray(final String name, final long[] value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // Get array size
        final byte[] sizeArr = new byte[4];
        inputStream.read(sizeArr, 0, 4);
        final int size = ByteBuffer.wrap(sizeArr).order(ByteOrder.BIG_ENDIAN).getInt();

        // Get array
        final long[] longs = new long[size];
        for (int i = 0; i < size; i++) {
            final byte[] arr = new byte[8];
            inputStream.read(arr, 0, 8);
            longs[i] = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getLong();
        }
        this.value = longs;
    }

    @Override
    public int getId() {
        return 3;
    }

}
