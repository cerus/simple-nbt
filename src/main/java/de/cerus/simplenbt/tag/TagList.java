package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TagList extends Tag<List<Tag<?>>> {

    TagList(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagList(final String name, final List<Tag<?>> value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // TODO
        // Pseudo-code:
        // InputStream in = ...
        // byte listTagId = in.read();
        // int size = int(in);
        // List<Tag<?>> list = ...
        // for(size) {
        //     list.add(TagReader.read(listTagId, in))
        // }
    }

    @Override
    public int getId() {
        return 9;
    }

}
