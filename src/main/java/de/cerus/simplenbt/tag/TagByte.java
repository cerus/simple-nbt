package de.cerus.simplenbt.tag;

public class TagByte extends Tag<Byte> {

    protected TagByte(final String name, final byte value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 1;
    }

}
