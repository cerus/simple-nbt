package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TagCompound extends Tag<List<Tag<?>>> {

    TagCompound(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagCompound(final String name, final List<Tag<?>> value) {
        super(name, value);
    }

    public static TagCompound createRootTag() {
        return new TagCompound("", new ArrayList<>());
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
        while (!((tag = TagReader.readNextTag(inputStream, true).orElse(null)) instanceof TagEnd) && tag != null) {
            list.add(tag);
        }
        this.value = list;
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName) throws IOException {
        super.write(outputStream, withName);

        for (final Tag<?> tag : this.value) {
            tag.write(outputStream, true);
            System.out.println(tag.name + " was written");
        }
        new TagEnd().write(outputStream, false);
    }

    public void set(final Tag<?> tag) {
        if (this.contains(tag.name)) {
            this.value.remove(this.get(tag.name));
        }
        this.value.add(tag);
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
