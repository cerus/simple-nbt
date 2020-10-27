package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TagIntArray extends Tag<int[]> {

    TagIntArray(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagIntArray(final String name, final int[] value) {
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
        final int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            final byte[] arr = new byte[4];
            inputStream.read(arr, 0, 4);
            ints[i] = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getInt();
        }
        this.value = ints;
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName) throws IOException {
        super.write(outputStream, withName);

        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(this.value.length).array());
        final ByteBuffer byteBuffer = ByteBuffer.allocate(this.value.length * 4).order(ByteOrder.BIG_ENDIAN);
        for (final int i : this.value) {
            byteBuffer.putInt(i);
        }
        outputStream.write(byteBuffer.array());
    }

    @Override
    public String stringify() {
        return "[I;" + Arrays.stream(this.getValue())
                .mapToObj(val -> val + "")
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    public int getId() {
        return 11;
    }

}
