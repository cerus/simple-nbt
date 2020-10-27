package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagList extends Tag<List<Tag<?>>> {

    private int tagId;

    TagList(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagList(final String name, final List<Tag<?>> value, final int tagId) {
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
            final Tag<?> tag = TagReader.readNextTagExceptionally(inputStream, false);
            if (tag.getId() != this.tagId) {
                throw new IllegalStateException("Id of list item is " + tag.getId() + " but it should be " + this.tagId);
            }

            list.add(tag);
        }
        this.value = list;
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName) throws IOException {
        super.write(outputStream, withName);

        outputStream.write(this.tagId);
        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(this.value.size()).array());
        for (final Tag<?> tag : this.value) {
            tag.write(outputStream, false);
        }
    }

    @Override
    public String stringify() {
        return "[" + this.getValue().stream()
                .map(Tag::stringify)
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    public int getId() {
        return 9;
    }

    public int getTagId() {
        return this.tagId;
    }

}
