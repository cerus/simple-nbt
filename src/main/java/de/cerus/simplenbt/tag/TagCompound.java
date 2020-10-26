package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TagCompound extends Tag<List<Tag<?>>> {

    TagCompound(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagCompound(final String name, final List<Tag<?>> value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        // Problem is that we don't know the size of the compound beforehand, at least the
        // Minecraft wiki entry on the NBT format doesn't say so.
        // That's why we will need to loop through the items until we reach an end tag (TAG_End, ID 0).

        if (parseName) {
            this.name = this.readName(inputStream);
        }

        final List<Tag<?>> list = new ArrayList<>();
        Tag<?> tag;
        while (!((tag = TagReader.readNextTag(inputStream, true).orElse(null)) instanceof TagEnd)) {
            list.add(tag);
        }
        this.value = list;
    }

    public <T extends Tag<?>> T get(final String key) {
        return this.value.stream()
                .filter(tag -> key.equals(tag.name))
                .map(tag -> (T) tag)
                .findAny()
                .orElse(null);
    }

    public boolean contains(final String key) {
        return this.get(key) != null;
    }

    @Override
    public int getId() {
        return 10;
    }

}
