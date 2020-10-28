package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagLong extends Tag<Long> {

    TagLong(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagLong(final String name, final long value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // Get value
        final byte[] arr = new byte[8];
        inputStream.read(arr, 0, 8);
        this.value = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getLong();
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        super.write(outputStream, withName, writeId);

        outputStream.write(ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(this.value).array());
    }

    @Override
    public String stringify() {
        return this.getValue() + "l";
    }

    @Override
    public int getId() {
        return 4;
    }

}
