package dev.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TagByteArray extends Tag<byte[]> {

    TagByteArray(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagByteArray(final String name, final byte[] value) {
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
        final byte[] bytes = new byte[size];
        inputStream.read(bytes, 0, size);
        this.value = bytes;
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        super.write(outputStream, withName, writeId);
        outputStream.write(ByteBuffer.allocate(4).putInt(this.value.length).array());
        outputStream.write(this.value);
    }

    @Override
    public String stringify() {
        return "[B;" + IntStream.range(0, this.getValue().length)
                .map(i -> this.getValue()[i])
                .mapToObj(val -> val + "")
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    public int getId() {
        return 7;
    }

}
