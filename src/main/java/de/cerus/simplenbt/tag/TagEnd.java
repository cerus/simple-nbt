package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;

/**
 * TAG_End has no name and no value.
 */
public class TagEnd extends Tag<Void> {

    TagEnd(final InputStream inputStream) throws IOException {
        super(inputStream);
    }

    protected TagEnd(final String name, final Void value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream) throws IOException {
        if (!this.checkId(inputStream)) {
            // Id does not match
            // TODO
        }
    }

    @Override
    public int getId() {
        return 0;
    }

}
