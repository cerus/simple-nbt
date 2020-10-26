package de.cerus.simplenbt.tag;

public abstract class Tag<T> {

    private final String name;
    private final T value;

    protected Tag(final String name, final T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public abstract int getId();

}
