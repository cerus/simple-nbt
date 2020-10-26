package de.cerus.simplenbt.tag;

public class TagLong extends Tag<Long> {

    protected TagLong(final String name, final long value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 4;
    }

}
