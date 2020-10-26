package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TagFloat extends Tag<Float> {

    TagFloat(final InputStream inputStream) throws IOException {
        super(inputStream);
    }

    protected TagFloat(final String name, final float value) {
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
        final byte[] arr = new byte[4];
        inputStream.read(arr, 0, 4);
        this.value = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

    @Override
    public int getId() {
        return 5;
    }

}