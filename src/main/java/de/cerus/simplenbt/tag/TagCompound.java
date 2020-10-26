package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;

public class TagCompound extends Tag<Tag<?>[]> {

    TagCompound(final InputStream inputStream, final boolean parseName) throws IOException {
        super(inputStream, parseName);
    }

    protected TagCompound(final String name, final Tag<?>[] value) {
        super(name, value);
    }

    @Override
    protected void read(final InputStream inputStream, final boolean parseName) throws IOException {
        // TODO
        //
        // Problem is that we don't know the size of the compound beforehand, at least the
        // Minecraft wiki entry on the NBT format doesn't say so.
        // That's why we will need to loop through the items until we reach an end tag (TAG_End, ID 0).
        //
        //
        // Pseudo-Code:
        //
        // List<Tag<?>> list = ...
        // Tag<?> tag;
        // while(!((tag = TagReader.readNext(inputStream)) instanceof TagEnd)) {
        //     list.add(tag);
        // }
        // Tag<?>[] arr = list.toArray(new Tag<?>[0]);
    }

    @Override
    public int getId() {
        return 10;
    }

}
