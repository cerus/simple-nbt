package de.cerus.simplenbt.tag;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TAG_End has no name and no value.
 */
public class TagEnd extends Tag<Void> {

    TagEnd() throws IOException {
        super(new ByteArrayInputStream(new byte[0]), false);
    }

    protected TagEnd(final String name, final Void value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName) throws IOException {
        super.write(outputStream, withName);
    }

    @Override
    public int getId() {
        return 0;
    }

}
