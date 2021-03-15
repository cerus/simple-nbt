package de.cerus.simplenbt.tag;

import de.cerus.simplenbt.util.GzipUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleNbtUtil {

    private SimpleNbtUtil() {
    }

    public static TagCompound readCompressedCompound(final InputStream inputStream) throws IOException {
        return readCompound(new ByteArrayInputStream(GzipUtil.decompress(inputStream)));
    }

    public static TagCompound readFile(final File file) throws IOException {
        return readCompound(new FileInputStream(file));
    }

    public static TagCompound readCompressedFile(final File file) throws IOException {
        return readCompressedCompound(new FileInputStream(file));
    }

    public static TagCompound readCompound(final InputStream inputStream) throws IOException {
        final int id = inputStream.read();
        if (id != 10) {
            throw new IllegalStateException("Invalid id " + id);
        }

        return new TagCompound(inputStream, true);
    }

    public static void writeAndCompressTag(final Tag<?> tag, final OutputStream outputStream, final boolean name) throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writeTag(tag, stream, name);
        outputStream.write(GzipUtil.compress(stream.toByteArray()));
        stream.close();
    }

    public static void writeAndCompressTag(final Tag<?> tag, final File file, final boolean name) throws IOException {
        writeAndCompressTag(tag, new FileOutputStream(file), name);
    }

    public static void writeTag(final Tag<?> tag, final OutputStream outputStream, final boolean name) throws IOException {
        tag.write(outputStream, name, true);
    }

    public static void writeTag(final Tag<?> tag, final File file, final boolean name) throws IOException {
        writeTag(tag, new FileOutputStream(file), name);
    }

}
