package de.cerus.simplenbt.tag;

public class TagCompound extends Tag<Tag<?>[]> {

    protected TagCompound(final String name, final Tag<?>[] value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 10;
    }

}
