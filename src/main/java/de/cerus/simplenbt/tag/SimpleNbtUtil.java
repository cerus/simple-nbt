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

    public static TagCompound readCompressedFile(final File file) throws IOException {
        return readCompressedCompound(new FileInputStream(file));
    }

    public static TagCompound readCompound(final InputStream inputStream) throws IOException {
        final int id = inputStream.read();
        if (id != 10) {
            throw new IllegalStateException("Invalid id");
        }

        return new TagCompound(inputStream, true);
    }

    public static void writeAndCompressTag(final Tag<?> tag, final OutputStream outputStream) throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        writeTag(tag, stream);
        outputStream.write(GzipUtil.compress(stream.toByteArray()));
    }

    public static void writeAndCompressTag(final Tag<?> tag, final File file) throws IOException {
        writeAndCompressTag(tag, new FileOutputStream(file));
    }

    public static void writeTag(final Tag<?> tag, final OutputStream outputStream) throws IOException {
        tag.write(outputStream, !(tag instanceof TagCompound));
    }

    public static void writeTag(final Tag<?> tag, final File file) throws IOException {
        writeTag(tag, new FileOutputStream(file));
    }

}
