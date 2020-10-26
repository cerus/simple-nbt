package de.cerus.simplenbt.tag;

import de.cerus.simplenbt.util.GzipUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NbtUtil {

    private NbtUtil() {
    }

    public static TagCompound readCompressedCompound(final InputStream inputStream) throws IOException {
        return readCompound(new ByteArrayInputStream(GzipUtil.decompress(inputStream)));
    }

    public static TagCompound readCompressedFile(final File file) throws IOException {
        return readCompressedCompound(new FileInputStream(file));
    }

    public static TagCompound readCompound(final InputStream inputStream) throws IOException {
        final int id = inputStream.read();
        if (id != 10) {
            throw new IllegalStateException();
        }

        return new TagCompound(inputStream, true);
    }

}
