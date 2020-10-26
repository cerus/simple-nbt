package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;

/**
 * TAG_End has no name and no value.
 */
public class TagEnd extends Tag<Void> {

    TagEnd(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagEnd(final String name, final Void value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
    }

    @Override
    public int getId() {
        return 0;
    }

}
