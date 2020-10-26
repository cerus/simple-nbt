package de.cerus.simplenbt.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TagRegistry {

    private static final Map<Integer, Class<? extends Tag<?>>> TAG_MAP = new HashMap<Integer, Class<? extends Tag<?>>>() {
        {
            this.put(0, TagEnd.class);
            this.put(1, TagByte.class);
            this.put(2, TagShort.class);
            this.put(3, TagInt.class);
            this.put(4, TagLong.class);
            this.put(5, TagFloat.class);
            this.put(6, TagDouble.class);
            this.put(7, TagByteArray.class);
            this.put(8, TagString.class);
            this.put(9, TagList.class);
            this.put(10, TagCompound.class);
            this.put(11, TagIntArray.class);
            this.put(12, TagLongArray.class);
        }
    };

    private TagRegistry() {
    }

    public static Optional<Class<? extends Tag<?>>> findClass(final int id) {
        return Optional.ofNullable(TAG_MAP.get(id));
    }

}
