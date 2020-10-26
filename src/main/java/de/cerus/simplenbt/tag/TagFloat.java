package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagFloat extends Tag<Float> {

    TagFloat(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagFloat(final String name, final float value) {
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
        this.value = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName) throws IOException {
        super.write(outputStream, withName);

        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(this.value).array());
    }

    @Override
    public int getId() {
        return 5;
    }

}
