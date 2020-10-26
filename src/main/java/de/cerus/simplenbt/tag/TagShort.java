package de.cerus.simplenbt.tag;

public class TagShort extends Tag<Short> {

    protected TagShort(final String name, final short value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 2;
    }

}
