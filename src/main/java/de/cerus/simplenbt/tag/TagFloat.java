package de.cerus.simplenbt.tag;

public class TagFloat extends Tag<Float> {

    protected TagFloat(final String name, final float value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 5;
    }

}
