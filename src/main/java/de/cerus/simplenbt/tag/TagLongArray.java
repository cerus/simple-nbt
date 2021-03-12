package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TagLongArray extends Tag<long[]> {

    TagLongArray(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagLongArray(final String name, final long[] value) {
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
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        super.write(outputStream, withName, writeId);

        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(this.value.length).array());
        final ByteBuffer byteBuffer = ByteBuffer.allocate(this.value.length * 8).order(ByteOrder.BIG_ENDIAN);
        for (final long l : this.value) {
            byteBuffer.putLong(l);
        }
        outputStream.write(byteBuffer.array());
    }

    @Override
    public String stringify() {
        return "[L;" + Arrays.stream(this.getValue())
                .mapToObj(val -> val + "")
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    public int getId() {
        return 12;
    }

}
