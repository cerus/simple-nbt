package de.cerus.simplenbt.tag;

public class TagDouble extends Tag<Double> {

    protected TagDouble(final String name, final double value) {
        super(name, value);
    }

    @Override
    public int getId() {
        return 6;
    }

}
