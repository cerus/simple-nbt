package dev.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TAG_End has no name and no value.
 */
public class TagEnd extends Tag<Void> {

    TagEnd(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagEnd() {
        this(null, null);
    }

    TagEnd(final String name, final Void value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        outputStream.write(this.getId());
    }

    @Override
    public int getId() {
        return 0;
    }

}
