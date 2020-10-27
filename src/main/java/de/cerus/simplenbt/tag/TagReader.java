package de.cerus.simplenbt.tag;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class TagReader {

    public static Tag<?> readNextTagExceptionally(final InputStream inputStream, final boolean parseName) throws IOException {
        return readNextTagExceptionally(inputStream, parseName, inputStream.read());
    }

    public static Tag<?> readNextTagExceptionally(final InputStream inputStream, final boolean parseName, final int tagId) {
        final Optional<? extends Tag<?>> optional = readNextTag(inputStream, parseName, tagId);

        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Unknown tag with id " + tagId);
        }
        return optional.get();
    }

    public static Optional<? extends Tag<?>> readNextTag(final InputStream inputStream, final boolean parseName) throws IOException {
        final int tagId = inputStream.read();
        return readNextTag(inputStream, parseName, tagId);
    }

    public static Optional<? extends Tag<?>> readNextTag(final InputStream inputStream, final boolean parseName, final int tagId) {
        return TagRegistry.findClass(tagId).map(aClass -> {
            try {
                final Constructor<? extends Tag<?>> constructor = aClass.getDeclaredConstructor(InputStream.class, boolean.class);
                constructor.setAccessible(true);
                return constructor.newInstance(inputStream, parseName);
            } catch (final InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}
