package de.cerus.simplenbt.tag;

public class TagInt extends Tag<Integer> {

    protected TagInt(final String name, final int value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 3;
    }

}
