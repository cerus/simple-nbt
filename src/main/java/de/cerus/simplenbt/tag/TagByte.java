package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TagByte extends Tag<Byte> {

    TagByte(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagByte(final String name, final byte value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // Get value
        this.value = (byte) inputStream.read();
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        super.write(outputStream, withName, writeId);
        outputStream.write(this.value);
    }

    @Override
    public String stringify() {
        return this.getValue() + "b";
    }

    public boolean asBoolean() {
        return this.getValue() == (byte) 1;
    }

    @Override
    public int getId() {
        return 1;
    }

}
