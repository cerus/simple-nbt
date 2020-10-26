package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;

public class TagByte extends Tag<Byte> {

    TagByte(final InputStream inputStream) throws IOException {
        super(inputStream);
    }

    protected TagByte(final String name, final byte value) {
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
        this.value = (byte) inputStream.read();
    }

    public boolean asBoolean() {
        return this.getValue() == (byte) 1;
    }

    @Override
    public int getId() {
        return 1;
    }

}
