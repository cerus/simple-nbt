package de.cerus.simplenbt.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    public static byte[] decompress(final byte[] bytes) throws IOException {
        return decompress(new ByteArrayInputStream(bytes));
    }

    public static byte[] decompress(final InputStream in) throws IOException {
        final GZIPInputStream inputStream = new GZIPInputStream(in);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        final byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        return outputStream.toByteArray();
    }

    public static byte[] compress(final byte[] bytes) throws IOException {
        return compress(new ByteArrayInputStream(bytes));
    }

    public static byte[] compress(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        final GZIPOutputStream outputStream = new GZIPOutputStream(bOut);

        final byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        return bOut.toByteArray();
    }

}
