package de.cerus.simplenbt.tag;

public class TagByte extends Tag<Byte> {

    protected TagByte(final String name, final byte value) {
        super(name, value);
    }

    public boolean asBoolean() {
        return this.getValue() == (byte) 1;
    }

    @Override
    public int getId() {
        return 1;
    }

}
