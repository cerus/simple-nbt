package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TagCompound extends Tag<List<Tag<?>>> {

    TagCompound(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    public TagCompound(final String name, final List<Tag<?>> value) {
        super(name, value);
    }

    public static TagCompound createRootTag() {
        return createTag("");
    }

    public static TagCompound createTag(final String name) {
        return new TagCompound(name, new ArrayList<>());
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
        while (!((tag = TagReader.readNextTagExceptionally(inputStream, true)) instanceof TagEnd)) {
            list.add(tag);
        }
        this.value = list;
    }

    @Override
    protected void write(final OutputStream outputStream, final boolean withName, final boolean writeId) throws IOException {
        super.write(outputStream, withName, writeId);

        for (final Tag<?> tag : this.value) {
            tag.write(outputStream, true, true);
        }
        new TagEnd().write(outputStream, false, true);
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

    public Set<String> nameSet() {
        return this.value.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }

    public boolean contains(final String key) {
        return this.get(key) != null;
    }

    @Override
    public String stringify() {
        return "{" + this.getValue().stream()
                .map(tag -> tag.getName() + ":" + tag.stringify())
                .collect(Collectors.joining(",")) + "}";
    }

    @Override
    public int getId() {
        return 10;
    }

}
