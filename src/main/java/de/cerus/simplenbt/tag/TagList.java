package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagList extends Tag<List<Tag<?>>> {

    private int tagId;

    TagList(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagList(final String name, final List<Tag<?>> value, final int tagId) {
        super(name, value);
        this.tagId = tagId;
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        if (parseName) {
            // Get name
            this.name = this.readName(inputStream);
        }

        // Read tag id
        this.tagId = inputStream.read();

        // Read size
        final byte[] arr = new byte[4];
        inputStream.read(arr, 0, 4);
        final int size = ByteBuffer.wrap(arr).order(ByteOrder.BIG_ENDIAN).getInt();

        // Read tags
        final List<Tag<?>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Optional<? extends Tag<?>> optional = TagReader.readNextTag(inputStream, false, this.tagId);
            optional.ifPresent(list::add);
        }
        this.value = list;
    }

    @Override
    public int getId() {
        return 9;
    }

    public int getTagId() {
        return this.tagId;
    }
}
